import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main window of the Customer Manager application.
 * Displays customers in a table and lets the user add and delete them.
 */
public class CustomerManagerApp extends Application {

    private static final String DELETE_BUTTON_STYLE =
            "-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;";

    /** The ten provinces of Zambia, shown in the drop-down list. */
    private static final String[] PROVINCES = {
            "Central", "Copperbelt", "Eastern", "Luapula", "Lusaka",
            "Muchinga", "Northern", "North-Western", "Southern", "Western"
    };

    /**
     * Builds the user interface and shows the main window.
     *
     * @param stage the primary stage provided by JavaFX
     */
    @Override
    public void start(Stage stage) {
        // Input controls
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        ComboBox<String> provinceBox = new ComboBox<>(
                FXCollections.observableArrayList(PROVINCES));
        provinceBox.setPromptText("Select Province");

        // Data
        ObservableList<Customer> data = FXCollections.observableArrayList(
                new Customer("Chipo Banda", "Lusaka"),
                new Customer("John Smith", "Copperbelt"),
                new Customer("Aisha Zulu", "Southern"),
                new Customer("Theresa Gondwe", "Copperbelt")
        );

        // Table
        TableView<Customer> table = new TableView<>();
        TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Customer, String> provinceCol = new TableColumn<>("Province");
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));
        table.getColumns().addAll(nameCol, provinceCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(data);

        // Buttons
        Button addBtn = new Button("Add Customer");
        Button deleteBtn = new Button("Delete Selected");
        deleteBtn.setStyle(DELETE_BUTTON_STYLE);
        deleteBtn.disableProperty().bind(
                table.getSelectionModel().selectedItemProperty().isNull());

        // Actions
        addBtn.setOnAction(e -> CustomerAdd.addCustomer(nameField, provinceBox, data));
        deleteBtn.setOnAction(e -> CustomerDelete.deleteCustomer(table, data));

        // Layout
        HBox inputBox = new HBox(10, nameField, provinceBox, addBtn);
        VBox root = new VBox(15, table, inputBox, deleteBtn);
        root.setPadding(new Insets(15));

        stage.setScene(new Scene(root, 600, 450));
        stage.setTitle("Customer Manager - Lusaka");
        stage.show();
    }

    /**
     * Application entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        launch();
    }
}