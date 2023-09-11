package se.fluff.aptusviewer.callbacks;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;
import se.fluff.aptusviewer.AptusViewerApplication;
import se.fluff.aptusviewer.controllers.UsersDialogViewController;
import se.fluff.aptusviewer.models.gui.AptusRow;



public class RowFactoryCallback implements Callback<TableView<AptusRow>, TableRow<AptusRow>> {
    @Override
    public TableRow<AptusRow> call(TableView<AptusRow> tableView) {

        TableRow<AptusRow> tr = new TableRow<>();
        tr.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                Dialog<UsersDialogViewController> dialog = new Dialog<>();
                FXMLLoader fxmlLoader = new FXMLLoader(AptusViewerApplication.class.getResource("users-dialog-view.fxml"));
                try {
                    dialog.setTitle("Customer " + tr.getItem().getCustomer() + " / " + tr.getItem().getSurname() + " (Id: " + tr.getItem().getCustomerId() + ")");
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
    }
}
