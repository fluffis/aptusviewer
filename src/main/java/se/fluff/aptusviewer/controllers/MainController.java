package se.fluff.aptusviewer.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import se.fluff.aptusviewer.AptusViewerApplication;
import se.fluff.aptusviewer.callbacks.RowFactoryCallback;
import se.fluff.aptusviewer.db.DatabaseRepository;
import se.fluff.aptusviewer.listeners.SearchboxChangeListener;
import se.fluff.aptusviewer.models.db.*;
import se.fluff.aptusviewer.models.gui.AptusRow;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    private final ObservableList<AptusRow> tableRows = FXCollections.observableArrayList();

    private DatabaseRepository db;

    private Map<Integer, AptusControl> controls;
    private Map<Integer, AptusSystem> systems;

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
            this.controls = db.getControls();
            this.systems = db.getSystems();

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
        maintable.setRowFactory(new RowFactoryCallback(systems, controls));

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
        else if(event.getCode() == KeyCode.DOWN) {
            maintable.requestFocus();
        }
        else if(event.getCode() == KeyCode.ENTER) {
            AptusRow aptusRow = maintable.getSelectionModel().getSelectedItem();
            if(aptusRow != null) {
                Dialog<UsersDialogViewController> dialog = new Dialog<>();
                FXMLLoader fxmlLoader = new FXMLLoader(AptusViewerApplication.class.getResource("users-dialog-view.fxml"));
                try {
                    dialog.setTitle("Customer " + aptusRow.getCustomer() + " / " + aptusRow.getSurname() + " (Id: " + aptusRow.getCustomerId() + ")");
                    dialog.setDialogPane(fxmlLoader.load());
                    UsersDialogViewController controller = fxmlLoader.getController();
                    controller.setUsers(aptusRow.getUserList());
                    controller.setEvents(aptusRow.getEvents());
                    controller.setLookupMaps(systems, controls);

                    dialog.show();
                }
                catch(Exception exception) {
                    System.err.println("Could not load FXML/Dialog: " + exception.getMessage());
                }
            }
        }
    }

    private void refreshRows() {

        List<AptusRow> rows = new ArrayList<>();

        Thread taskThread = new Thread(() -> {
            db.getVirtualAptusRows().forEach(ao -> {

                List<Authority> authorities = db.getAuthoritiesForObjectId(ao.getObjectId());
                List<User> users = db.getUsersForCustomerId(ao.getCustomerId());
                List<AptusEvent> events = db.getEventsForCustomerId(ao.getCustomerId());
                users.forEach(u -> {
                    u.setUserAuthorities(db.getAuthoritiesForUserId(u.getId())
                            .stream()
                            .sorted(Comparator.comparing(Authority::getShortName))
                            .collect(Collectors.toList())
                    );
                });
                ao.setUserList(users);
                ao.setEvents(events);
                ao.setObjectAuthorities(authorities.stream()
                        .map(Authority::getShortName)
                        .sorted()
                        .collect(Collectors.joining(", ")));

                rows.add(ao);
            });

            tableRows.clear();
            tableRows.addAll(rows);
        });

        taskThread.start();

    }

}