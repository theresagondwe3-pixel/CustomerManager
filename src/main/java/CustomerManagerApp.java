import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class CustomerManagerApp extends Application {

    public static class Customer {
        private String name;
        private String province;
        public Customer(String n, String p){ name=n; province=p; }
        public String getName(){ return name; }
        public String getProvince(){ return province; }
    }

    @Override
    public void start(Stage stage) {
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField provinceField = new TextField();
        provinceField.setPromptText("Province");

        TableView<Customer> table = new TableView<>();
        ObservableList<Customer> data = FXCollections.observableArrayList(
                new Customer("Chipo Banda","Lusaka"),
                new Customer("John Smith","Copperbelt"),
                new Customer("Aisha Zulu","Southern"),
                new Customer("Theresa gondwe","Kitwe")
        );

        TableColumn<Customer,String> c1 = new TableColumn<>("Name");
        c1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Customer,String> c2 = new TableColumn<>("Province");
        c2.setCellValueFactory(new PropertyValueFactory<>("province"));
        table.getColumns().addAll(c1,c2);
        table.setItems(data);

        Button addBtn = new Button("Add Customer");
        Button deleteBtn = new Button("DELETE SELECTED");
        deleteBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;");

        addBtn.setOnAction(e -> {
            if(!nameField.getText().isEmpty()){
                data.add(new Customer(nameField.getText(), provinceField.getText()));
                nameField.clear(); provinceField.clear();
            }
        });

        deleteBtn.setOnAction(e -> CustomerDelete.deleteCustomer(table, data));

        HBox inputBox = new HBox(10, nameField, provinceField, addBtn);
        VBox root = new VBox(15, table, inputBox, deleteBtn);
        root.setPadding(new Insets(15));

        stage.setScene(new Scene(root, 600, 450));
        stage.setTitle("Customer Manager - Lusaka");
        stage.show();
    }
    public static void main(String[] args){ launch(); }
}