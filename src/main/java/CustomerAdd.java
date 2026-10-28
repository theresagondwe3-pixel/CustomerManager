import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Handles the "add customer" operation: validates the input fields
 * and adds a new customer to the data list.
 */
public class CustomerAdd {

    /**
     * Validates the name field and the selected province. If both are filled
     * in, a new customer is added to the list and the inputs are cleared.
     * Otherwise a warning dialog is shown.
     *
     * @param nameField   text field holding the customer's name
     * @param provinceBox drop-down list holding the selected province
     * @param data        the list backing the customer table
     */
    public static void addCustomer(TextField nameField, ComboBox<String> provinceBox,
                                   ObservableList<Customer> data) {
        String name = nameField.getText().trim();
        String province = provinceBox.getValue();

        if (name.isEmpty() || province == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(nameField.getScene().getWindow());
            alert.setTitle("Missing Information");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a name and select a province.");
            alert.showAndWait();
            return;
        }

        data.add(new Customer(name, province));
        nameField.clear();
        provinceBox.setValue(null);
        nameField.requestFocus();
    }
}