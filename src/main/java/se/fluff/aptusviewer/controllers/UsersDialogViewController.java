package se.fluff.aptusviewer.controllers;

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
import se.fluff.aptusviewer.models.db.User;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

    private final ObservableList<User> userList = FXCollections.observableArrayList();


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
}
