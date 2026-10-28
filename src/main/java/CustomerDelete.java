import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import java.util.Optional;

/**
 * Handles the "delete customer" operation: checks a row is selected,
 * asks the user to confirm, then removes the customer from the list.
 */
public class CustomerDelete {

    /**
     * Deletes the customer currently selected in the table after the user
     * confirms. If nothing is selected, a warning dialog is shown instead.
     *
     * @param table the table showing the customers
     * @param data  the list backing the table
     */
    public static void deleteCustomer(TableView<Customer> table,
                                      ObservableList<Customer> data) {
        Customer selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.initOwner(table.getScene().getWindow());
            warning.setTitle("No Selection");
            warning.setHeaderText(null);
            warning.setContentText("Please select a customer to delete.");
            warning.showAndWait();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.initOwner(table.getScene().getWindow());
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText(null);
        confirm.setContentText("Delete " + selected.getName()
                + " from " + selected.getProvince() + "?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            data.remove(selected);
        }
    }
}