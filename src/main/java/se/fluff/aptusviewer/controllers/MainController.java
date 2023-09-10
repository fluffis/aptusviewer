package se.fluff.aptusviewer.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import se.fluff.aptusviewer.AptusViewerApplication;
import se.fluff.aptusviewer.callbacks.RowFactoryCallback;
import se.fluff.aptusviewer.db.DatabaseRepository;
import se.fluff.aptusviewer.listeners.SearchboxChangeListener;
import se.fluff.aptusviewer.models.db.Authority;
import se.fluff.aptusviewer.models.db.User;
import se.fluff.aptusviewer.models.gui.AptusRow;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    private final ObservableList<AptusRow> tableRows = FXCollections.observableArrayList();

    private DatabaseRepository db;

    @FXML
    private TableView<AptusRow> maintable;

    @FXML
    private TableColumn<AptusRow, String> customer;

    @FXML
    private TableColumn<AptusRow, String> objectname;

    @FXML
    private TableColumn<AptusRow, Integer> floor;

    @FXML
    private TableColumn<AptusRow, String> address;

    @FXML
    private TableColumn<AptusRow, String> firstname;

    @FXML
    private TableColumn<AptusRow, String> surname;

    @FXML
    private TableColumn<AptusRow, String> authorities;

    @FXML
    private TextField searchbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        objectname.setCellValueFactory(new PropertyValueFactory<>("objectName"));
        floor.setCellValueFactory(new PropertyValueFactory<>("floor"));
        address.setCellValueFactory(new PropertyValueFactory<>("phoneAddress"));
        firstname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        authorities.setCellValueFactory(new PropertyValueFactory<>("objectAuthorities"));

        try {

            this.db = new DatabaseRepository(
                    AptusViewerApplication.settings.getProperty("hostname", ""),
                    AptusViewerApplication.settings.getProperty("dbname", ""),
                    AptusViewerApplication.settings.getProperty("username", ""),
                    AptusViewerApplication.settings.getProperty("password", "")
            );
            refreshRows();
        }
        catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

        FilteredList<AptusRow> filteredList = new FilteredList<>(tableRows, p -> true);
        searchbox.textProperty().addListener(new SearchboxChangeListener(filteredList));

        SortedList<AptusRow> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(maintable.comparatorProperty());

        maintable.setItems(sortedList);
        maintable.setRowFactory(new RowFactoryCallback());

    }

    @FXML
    private void handleOnKeyPressed(KeyEvent event) {
        if(event.isControlDown() && event.getCode() == KeyCode.F) {
            searchbox.requestFocus();
        }
        else if(event.isControlDown() && event.getCode() == KeyCode.R) {
            refreshRows();
        }
        else if(event.getCode() == KeyCode.ESCAPE) {
            searchbox.setText("");
            searchbox.requestFocus();

        }
    }

    private void refreshRows() {

        System.out.println("refreshRows");
        tableRows.clear();
        db.getVirtualAptusRows().forEach(ao -> {

            List<Authority> authorities = db.getAuthoritiesForObjectId(ao.getObjectId());
            List<User> users = db.getUsersForCustomerId(ao.getCustomerId());
            users.forEach(u -> {
                u.setUserAuthorities(db.getAuthoritiesForUserId(u.getId()));
            });
            ao.setUserList(users);
            ao.setObjectAuthorities(authorities.stream().map(Authority::getShortName).collect(Collectors.joining(", ")));
            tableRows.add(ao);
        });

    }

}