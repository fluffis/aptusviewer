package se.fluff.aptusviewer.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import se.fluff.aptusviewer.AptusViewerApplication;
import se.fluff.aptusviewer.db.DatabaseRepository;
import se.fluff.aptusviewer.models.db.Authority;
import se.fluff.aptusviewer.models.db.User;
import se.fluff.aptusviewer.models.gui.AptusRow;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    private ObservableList<AptusRow> tableRows;

    private List<AptusRow> allrows = new ArrayList<>();

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
        tableRows = FXCollections.observableArrayList();

        maintable.setItems(tableRows);
        maintable.setRowFactory(tv -> {
            TableRow<AptusRow> tr = new TableRow<>();
            tr.setOnMouseClicked(e -> {
                if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                    Dialog<UsersDialogViewController> dialog = new Dialog<>();
                    FXMLLoader fxmlLoader = new FXMLLoader(AptusViewerApplication.class.getResource("users-dialog-view.fxml"));
                    try {
                        dialog.setTitle("Customer " + tr.getItem().getCustomer() + " (Id: " + tr.getItem().getCustomerId() + ")");
                        dialog.setDialogPane(fxmlLoader.load());
                        UsersDialogViewController controller = fxmlLoader.getController();
                        controller.setUsers(tr.getItem().getUserList());

                        dialog.show();
                    }
                    catch(Exception exception) {
                        System.err.println("Could not load FXML/Dialog: " + exception.getMessage());
                    }
                }
            });

            return tr;
        });


        try {

            DatabaseRepository db = new DatabaseRepository(
                    AptusViewerApplication.settings.getProperty("hostname", ""),
                    AptusViewerApplication.settings.getProperty("dbname", ""),
                    AptusViewerApplication.settings.getProperty("username", ""),
                    AptusViewerApplication.settings.getProperty("password", "")
                    );
            allrows = db.getVirtualAptusRows();
            allrows.forEach(ao -> {

                List<Authority> authorities = db.getAuthoritiesForObjectId(ao.getObjectId());
                List<User> users = db.getUsersForCustomerId(ao.getCustomerId());
                users.forEach(u -> {
                    u.setUserAuthorities(db.getAuthoritiesForUserId(u.getId()));
                });
                ao.setUserList(users);
                ao.setObjectAuthorities(authorities.stream().map(Authority::getShortName).collect(Collectors.joining(", ")));

            });
            filterRows();
        }
        catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

    }

    @FXML
    private void searchboxHandleKeyEvent(KeyEvent event) {
        filterRows();

    }

    @FXML
    private void handleOnKeyPressed(KeyEvent event) {
        if(event.isControlDown() && event.getCode() == KeyCode.F) {
            searchbox.requestFocus();
        }
    }


    private void filterRows() {
        String search = searchbox.getText().toLowerCase();
        tableRows.clear();
        if(search.length() > 0) {
            allrows.forEach(row -> {

                if(row.getCustomer().toLowerCase().contains(search) || row.getObjectAuthorities().toLowerCase().contains(search) || row.getSurname().toLowerCase().contains(search)) {
                    tableRows.add(row);
                }
            });
        }
        else {
            tableRows.addAll(allrows);
        }

    }

}