package com.sriram.tablefilterfielddemo;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.TextFields;


public class MainApp extends Application {
    
    private TableView<Employee> table;
    private TextField txtField;
    private ObservableList<Employee> data;

    @Override
    public void start(Stage stage) throws Exception {
        Label lbl = new Label("Enter text below to filter: ");
        initFilter();
        initTable();
        VBox container = new VBox();
        container.getChildren().addAll(lbl, txtField, table);
        StackPane root = new StackPane();
        root.getChildren().add(container);
        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setResizable(false);
        stage.setTitle("Table Filter Demo");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void initTable() {
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Employee, String> empIdCol = new TableColumn<>("Employee ID");
        empIdCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employee, String> p) {
                return p.getValue().empIdProperty();
            }
        });
        
        TableColumn<Employee, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employee, String> p) {
                return p.getValue().nameProperty();
            }
        });
        
        TableColumn<Employee, Number> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Employee, Number> p) {
                return p.getValue().ageProperty();
            }
        });
        
        TableColumn<Employee, String> cityCol = new TableColumn<>("City");
        cityCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employee, String> p) {
                return p.getValue().cityProperty();
            }
        });
        
        table.getColumns().setAll(empIdCol, nameCol, ageCol, cityCol);
        data = getTableData();
        table.setItems(FXCollections.observableArrayList(data));
    }
    
    private void initFilter() {
        txtField = TextFields.createSearchField();
        txtField.setPromptText("Filter");
        txtField.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                if(txtField.textProperty().get().isEmpty()) {
                    table.setItems(data);
                    return;
                }
                ObservableList<Employee> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<Employee, ?>> cols = table.getColumns();
                for(int i=0; i<data.size(); i++) {
                    
                    for(int j=0; j<cols.size(); j++) {
                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(data.get(i)).toString();
                        cellValue = cellValue.toLowerCase();
                        if(cellValue.contains(txtField.textProperty().get().toLowerCase())) {
                            tableItems.add(data.get(i));
                            break;
                        }                        
                    }

                }
                table.setItems(tableItems);
            }
        });
    }
    
    private ObservableList<Employee> getTableData() {
        ObservableList<Employee> list = FXCollections.observableArrayList();
        String[] name = {"Sriram", "Pete", "Eric", "Dawson", "John"};
        String[] city = {"New York", "Chicago", "Little Rock", "Los Angeles", "Oakland"};
        for(int i = 0; i < 5; i++) {
            Employee emp = new Employee();
            emp.setName(name[i]);
            emp.setAge((int)(Math.random() * 100));
            emp.setCity(city[i]);
            emp.setEmpId(String.valueOf(i + 1000));
            list.add(emp);
        }
        return list;
    }

}
