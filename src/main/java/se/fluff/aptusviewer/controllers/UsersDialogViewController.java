package se.fluff.aptusviewer.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import se.fluff.aptusviewer.models.db.AptusControl;
import se.fluff.aptusviewer.models.db.AptusEvent;
import se.fluff.aptusviewer.models.db.AptusSystem;
import se.fluff.aptusviewer.models.db.User;

import java.net.URL;
import java.util.*;

public class UsersDialogViewController implements Initializable {

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> id;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> code;
    @FXML
    private TableColumn<User, String> card;
    @FXML
    private TableColumn<User, String> start;
    @FXML
    private TableColumn<User, String> stop;
    @FXML
    private TableColumn<User, String> blocked;

    @FXML
    private TableColumn<User, String> authorities;

    @FXML
    private Label statuslabel;

    @FXML
    private TableView<AptusEvent> logtable;

    @FXML
    private TableColumn<AptusEvent, String> logid;

    @FXML
    private TableColumn<AptusEvent, Date> logeventtime;

    @FXML
    private TableColumn<AptusEvent, String> logname;

    @FXML
    private TableColumn<AptusEvent, String> logactivatorname;

    @FXML
    private TableColumn<AptusEvent, String> logcontrol;


    private final ObservableList<User> userList = FXCollections.observableArrayList();
    private final ObservableList<AptusEvent> events = FXCollections.observableArrayList();
    private Map<Integer, AptusSystem> systems = new HashMap<>();
    private Map<Integer, AptusControl> controls = new HashMap<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setCellValueFactory(new PropertyValueFactory<>("cardLabel"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        card.setCellValueFactory(new PropertyValueFactory<>("card"));

        card.setCellFactory(factory -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if(empty || getTableRow() == null) {
                    setText(null);
                    setGraphic(null);
                }
                else {
                    setText(item);
                    User user = getTableRow().getItem();
                    if(user != null) {
                        if(user.isBlocked()) {
                            setStyle("-fx-background-color: #ff0000;");
                        }
                        else {
                            setStyle("");
                        }
                    }
                }
            }
        });
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        stop.setCellValueFactory(new PropertyValueFactory<>("stop"));
        blocked.setCellValueFactory(new PropertyValueFactory<>("blocked"));
        authorities.setCellValueFactory(new PropertyValueFactory<>("authorities"));
        table.setItems(userList);

        logid.setCellValueFactory(new PropertyValueFactory<>("id"));
        logeventtime.setCellValueFactory(new PropertyValueFactory<>("eventTime"));
        logname.setCellValueFactory(new PropertyValueFactory<>("shortName"));
        logactivatorname.setCellValueFactory(new PropertyValueFactory<>("activatorName"));
        logcontrol.setCellValueFactory(param -> new SimpleStringProperty(controls.get(param.getValue().getControlId()).getShortName()));
        logtable.setItems(events);

    }

    @FXML
    private void handleOnKeyPressed(KeyEvent event) {

        if(event.getCode() == KeyCode.ESCAPE) {
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
        else if(event.isControlDown() && event.getCode() == KeyCode.C) {
            User user = table.getSelectionModel().getSelectedItem();
            if(user != null) {
                statuslabel.setText("Copied card: " + user.getCard());
                final Clipboard clipboard = Clipboard.getSystemClipboard();
                final ClipboardContent content = new ClipboardContent();

                content.putString(user.getCard());
                clipboard.setContent(content);
            }
        }
    }

    public void setUsers(List<User> users) {
        userList.clear();
        userList.addAll(users);
    }

    public void setEvents(List<AptusEvent> eventlist) {
        events.clear();
        events.addAll(eventlist);
    }

    public void setLookupMaps(Map<Integer, AptusSystem> systems, Map<Integer, AptusControl> controls) {
        this.systems = systems;
        this.controls = controls;
    }
}
