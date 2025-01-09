package com.example.employeematrix;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class dashboardController implements Initializable {

    @FXML
    private Button addEmployee_addBtn;

    @FXML
    private Button addEmployee_btn;

    @FXML
    private Button close;
    @FXML
    private Button minimize;

    @FXML
    private Button addEmployee_clearBtn;

    @FXML
    private Button training_btn;

    @FXML
    private Button matrix_btn;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_date;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_employeeID;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_firstName;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_lastName;
    @FXML
    private TableColumn<employeeData, String> addEmployee_col_gender;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_phoneNum;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_position;

    @FXML
    private Button addEmployee_deleteBtn;

    @FXML
    private TextField addEmployee_employeeID;

    @FXML
    private TextField addEmployee_firstName;

    @FXML
    private AnchorPane addEmployee_form;

    @FXML
    private ComboBox<?> addEmployee_gender;

    @FXML
    private ImageView addEmployee_image;

    @FXML
    private Button addEmployee_importBtn;

    @FXML
    private TextField addEmployee_lastName;

    @FXML
    private TextField addEmployee_phoneNum;

    @FXML
    private ComboBox<?> addEmployee_position;

    @FXML
    private TextField addEmployee_search;

    @FXML
    private TableView<employeeData> addEmployee_tableView;

    @FXML
    private Button addEmployee_updateBtn;

    @FXML
    private Button home_btn;

    @FXML
    private BarChart<?, ?> home_chart;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEmployees;

    @FXML
    private Label home_totalInactiveEmp;

    @FXML
    private Label home_totalPresent;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button salary_btn;

    @FXML
    private Button salary_clearBtn;

    @FXML
    private TableColumn<employeeData, String> salary_col_employeeID;

    @FXML
    private TableColumn<employeeData, String> salary_col_firstName;

    @FXML
    private TableColumn<employeeData, String> salary_col_lastName;

    @FXML
    private TableColumn<employeeData, String> salary_col_position;

    @FXML
    private TableColumn<employeeData, String> salary_col_salary;

    @FXML
    private TableView<employeeData> salary_tableView;

    @FXML
    private TableView<employeeData> matrix_tableview;

    @FXML
    private TableColumn<employeeData, Integer> codingTextField;

    @FXML
    private TableColumn<employeeData, Integer> connectionsTextField;

    @FXML
    private TableColumn<employeeData, Integer> designBrandingTextField;

    @FXML
    private TableColumn<employeeData, Integer> financeTextField;

    @FXML
    private TableColumn<employeeData, Integer> managementTextField;

    @FXML
    private TableColumn<employeeData, Integer> productKnowledgeTextField;

    @FXML
    private TableColumn<employeeData, Integer> researchTextField;

    @FXML
    private TableColumn<employeeData, Integer> matrix_empID;

    @FXML
    private TableColumn<employeeData, String> matrix_firstname;

    @FXML
    private TextField salary_employeeID;

    @FXML
    private Label salary_firstName;

    @FXML
    private AnchorPane salary_form;

    @FXML
    private Label salary_lastName;

    @FXML
    private Label salary_position;

    @FXML
    private TextField salary_salary;

    @FXML
    private Button salary_updateBtn;

    @FXML
    private AnchorPane training_form;

    @FXML
    private AnchorPane skill_form;

    @FXML
    private Label username;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    private Image image;

    public dashboardController() {
    }

    public void homeTotalEmployees() {

        String sql = "SELECT COUNT(id) FROM employee";

        connect = database.connectDb();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            home_totalEmployees.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeEmployeeTotalPresent() {

        String sql = "SELECT COUNT(id) FROM employee_info";

        connect = database.connectDb();
        int countData = 0;
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }
            home_totalPresent.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeTotalInactive() {

        String sql = "SELECT COUNT(id) FROM employee_info WHERE salary = '0.0'";

        connect = database.connectDb();
        int countData = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }
            home_totalInactiveEmp.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeChart() {

        home_chart.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM employee GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 7";

        connect = database.connectDb();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_chart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void addEmployeeUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "UPDATE employee SET firstName = '"
                + addEmployee_firstName.getText() + "', lastName = '"
                + addEmployee_lastName.getText() + "', gender = '"
                + addEmployee_gender.getSelectionModel().getSelectedItem() + "', phoneNum = '"
                + addEmployee_phoneNum.getText() + "', position = '"
                + addEmployee_position.getSelectionModel().getSelectedItem() + "', image = '"
                + uri + "', date = '" + sqlDate + "' WHERE employee_id ='"
                + addEmployee_employeeID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;
            if (addEmployee_employeeID.getText().isEmpty()
                    || addEmployee_firstName.getText().isEmpty()
                    || addEmployee_lastName.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_position.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Employee ID: " + addEmployee_employeeID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    double salary = 0;

                    String checkData = "SELECT * FROM employee_info WHERE employee_id = '"
                            + addEmployee_employeeID.getText() + "'";

                    prepare = connect.prepareStatement(checkData);
                    result = prepare.executeQuery();

                    while (result.next()) {
                        salary = result.getDouble("salary");
                    }

                    String updateInfo = "UPDATE employee_info SET firstName = '"
                            + addEmployee_firstName.getText() + "', lastName = '"
                            + addEmployee_lastName.getText() + "', position = '"
                            + addEmployee_position.getSelectionModel().getSelectedItem()
                            + "' WHERE employee_id = '"
                            + addEmployee_employeeID.getText() + "'";

                    prepare = connect.prepareStatement(updateInfo);
                    prepare.executeUpdate();

                    String matrixInfo = "UPDATE matrix SET id = '"
                            + addEmployee_employeeID.getText() + "', firstName = '"
                            + addEmployee_firstName.getText();

                    prepare = connect.prepareStatement(matrixInfo);
                    prepare.executeUpdate();


                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addEmployeeDelete() {

        String sql = "DELETE FROM employee WHERE employee_id = '"
                + addEmployee_employeeID.getText() + "'";

        connect = database.connectDb();

        try {

            Alert alert;
            if (addEmployee_employeeID.getText().isEmpty()
                    || addEmployee_firstName.getText().isEmpty()
                    || addEmployee_lastName.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_position.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Employee ID: " + addEmployee_employeeID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    String deleteInfo = "DELETE FROM employee_info WHERE employee_id = '"
                            + addEmployee_employeeID.getText() + "'";

                    prepare = connect.prepareStatement(deleteInfo);
                    prepare.executeUpdate();

                    String matrixSql = "DELETE FROM matrix WHERE employee_id = '"
                            + addEmployee_employeeID.getText() + "'";

                    prepare = connect.prepareStatement(matrixSql);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void addEmployeeAdd() {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO employee"
                + "(employee_id,firstName,lastName,gender,phoneNum,position,image,date) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;
            if (addEmployee_employeeID.getText().isEmpty()
                    || addEmployee_firstName.getText().isEmpty()
                    || addEmployee_lastName.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_position.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String check = "SELECT employee_id FROM employee WHERE employee_id = '"
                        + addEmployee_employeeID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee ID: " + addEmployee_employeeID.getText() + " was already exist!");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addEmployee_employeeID.getText());
                    prepare.setString(2, addEmployee_firstName.getText());
                    prepare.setString(3, addEmployee_lastName.getText());
                    prepare.setString(4, (String) addEmployee_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, addEmployee_phoneNum.getText());
                    prepare.setString(6, (String) addEmployee_position.getSelectionModel().getSelectedItem());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    prepare.setString(7, uri);
                    prepare.setString(8, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    String insertInfo = "INSERT INTO employee_info "
                            + "(employee_id,firstName,lastName,position,salary,date) "
                            + "VALUES(?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertInfo);
                    prepare.setString(1, addEmployee_employeeID.getText());
                    prepare.setString(2, addEmployee_firstName.getText());
                    prepare.setString(3, addEmployee_lastName.getText());
                    prepare.setString(4, (String) addEmployee_position.getSelectionModel().getSelectedItem());
                    prepare.setString(5, "0.0");
                    prepare.setString(6, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    // Insert into the "matrix" table
                    String matrixSql = "INSERT INTO matrix"
                            + "(employee_id, firstName, productKnowledge, coding, management, designBranding, " +
                            "research, finance, connections) "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    prepare = connect.prepareStatement(matrixSql);
                    prepare.setString(1, addEmployee_employeeID.getText());
                    prepare.setString(2, addEmployee_firstName.getText());

//                    // Retrieve the skills values from the UI components
//                    int productKnowledge = Integer.parseInt(productKnowledgeTextField.getText());
//                    int coding = Integer.parseInt(codingTextField.getText());
//                    int management = Integer.parseInt(managementTextField.getText());
//                    int designBranding = Integer.parseInt(designBrandingTextField.getText());
//                    int research = Integer.parseInt(researchTextField.getText());
//                    int finance = Integer.parseInt(financeTextField.getText());
//                    int connections = Integer.parseInt(connectionsTextField.getText());
//
//                    // Set the retrieved values in the prepared statement
//                    prepare.setInt(3, productKnowledge);
//                    prepare.setInt(4, coding);
//                    prepare.setInt(5, management);
//                    prepare.setInt(6, designBranding);
//                    prepare.setInt(7, research);
//                    prepare.setInt(8, finance);
//                    prepare.setInt(9, connections);

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String[] positionList = {"Marketer Coordinator", "Web Developer (Back End)", 
    "Web Developer (Front End)", "App Developer"};

    public void addEmployeePositionList() {
        List<String> listP = new ArrayList<>();

        for (String data : positionList) {
            listP.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listP);
        addEmployee_position.setItems(listData);
    }

    private String[] listGender = {"Male", "Female", "Others"};

    public void addEmployeeGenderList() {
        List<String> listG = new ArrayList<>();

        for (String data : listGender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        addEmployee_gender.setItems(listData);
    }

    public void addEmployeeReset() {
        addEmployee_employeeID.setText("");
        addEmployee_firstName.setText("");
        addEmployee_lastName.setText("");
        addEmployee_gender.getSelectionModel().clearSelection();
        addEmployee_position.getSelectionModel().clearSelection();
        addEmployee_phoneNum.setText("");
        addEmployee_image.setImage(null);
        getData.path = "";
    }

    public void addEmployeeInsertImage() {

        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 101, 
            127, false, true);
            addEmployee_image.setImage(image);
        }
    }

    public void addEmployeeSearch() {

        FilteredList<employeeData> filter = new FilteredList<>(addEmployeeList, e -> true);

        addEmployee_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(EmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (EmployeeData.getEmployeeId().toString().contains(searchKey)) {
                    return true;
                } else if (EmployeeData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (EmployeeData.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (EmployeeData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (EmployeeData.getPhoneNum().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (EmployeeData.getPosition().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (EmployeeData.getDate().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<employeeData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addEmployee_tableView.comparatorProperty());
        addEmployee_tableView.setItems(sortList);
    }

    public ObservableList<employeeData> salaryListData() {

        ObservableList<employeeData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM employee_info";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            employeeData employeeD;

            while (result.next()) {
                employeeD = new employeeData(result.getInt("employee_id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("position"),
                        result.getDouble("salary"));

                listData.add(employeeD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public void salaryUpdate() {

        String sql = "UPDATE employee_info SET salary = '" + salary_salary.getText()
                + "' WHERE employee_id = '" + salary_employeeID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (salary_employeeID.getText().isEmpty()
                    || salary_firstName.getText().isEmpty()
                    || salary_lastName.getText().isEmpty()
                    || salary_position.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select item first");
                alert.showAndWait();
            } else {
                statement = connect.createStatement();
                statement.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                salaryShowListData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salaryReset() {
        salary_employeeID.setText("");
        salary_firstName.setText("");
        salary_lastName.setText("");
        salary_position.setText("");
        salary_salary.setText("");
    }

    public void salarySelect() {

        employeeData employeeD = salary_tableView.getSelectionModel().getSelectedItem();
        int num = salary_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        salary_employeeID.setText(String.valueOf(employeeD.getEmployeeId()));
        salary_firstName.setText(employeeD.getFirstName());
        salary_lastName.setText(employeeD.getLastName());
        salary_position.setText(employeeD.getPosition());
        salary_salary.setText(String.valueOf(employeeD.getSalary()));

    }

    private ObservableList<employeeData> salaryList;

    public void salaryShowListData() {
        salaryList = salaryListData();

        salary_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        salary_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        salary_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        salary_col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        salary_col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        salary_tableView.setItems(salaryList);

    }


    public ObservableList<employeeData> addEmployeeListData() {

        ObservableList<employeeData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            employeeData employeeD;

            while (result.next()) {
                employeeD = new employeeData(result.getInt("employee_id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getString("phoneNum"),
                        result.getString("position"),
                        result.getString("image"),
                        result.getDate("date"));
                listData.add(employeeD);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<employeeData> addEmployeeList;

    public void addEmployeeShowListData() {
        addEmployeeList = addEmployeeListData();

        addEmployee_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        addEmployee_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addEmployee_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addEmployee_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addEmployee_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        addEmployee_col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        addEmployee_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        addEmployee_tableView.setItems(addEmployeeList);

    }

    public void addEmployeeSelect() {
        employeeData employeeD = addEmployee_tableView.getSelectionModel().getSelectedItem();
        int num = addEmployee_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addEmployee_employeeID.setText(String.valueOf(employeeD.getEmployeeId()));
        addEmployee_firstName.setText(employeeD.getFirstName());
        addEmployee_lastName.setText(employeeD.getLastName());
        addEmployee_phoneNum.setText(employeeD.getPhoneNum());

        getData.path = employeeD.getImage();

        String uri = "file:" + employeeD.getImage();

        image = new Image(uri, 101, 127, false, true);
        addEmployee_image.setImage(image);
    }

    public ObservableList<employeeData> skillsMatrixListData() {
        ObservableList<employeeData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM matrix";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            employeeData employeeMatrix;

            while (result.next()) {
                employeeMatrix = new employeeData(
                        result.getInt("employee_id"),
                        result.getString("firstName"),
                        result.getInt("productKnowledge"),
                        result.getInt("coding"),
                        result.getInt("management"),
                        result.getInt("designBranding"),
                        result.getInt("research"),
                        result.getInt("finance"),
                        result.getInt("connections")
                );
                listData.add(employeeMatrix);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<employeeData> skillsMatrixList;

//    public void skillsMatrixShowListData() {
//        skillsMatrixList = skillsMatrixListData();
//
//        matrix_empID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
//        matrix_firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//        productKnowledgeTextField.setCellValueFactory(new PropertyValueFactory<>("productKnowledge"));
//        codingTextField.setCellValueFactory(new PropertyValueFactory<>("coding"));
//        managementTextField.setCellValueFactory(new PropertyValueFactory<>("management"));
//        designBrandingTextField.setCellValueFactory(new PropertyValueFactory<>("designBranding"));
//        researchTextField.setCellValueFactory(new PropertyValueFactory<>("research"));
//        financeTextField.setCellValueFactory(new PropertyValueFactory<>("finance"));
//        connectionsTextField.setCellValueFactory(new PropertyValueFactory<>("connections"));
//
//        matrix_tableview.setItems(skillsMatrixList);
//    }

// ...

//    public void skillsMatrixShowListData() {
//        skillsMatrixList = skillsMatrixListData();
//
//        matrix_empID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
//        matrix_firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//
//        // Make skill columns editable with TextFieldTableCell
//        makeEditableColumn(productKnowledgeTextField);
//        makeEditableColumn(codingTextField);
//        makeEditableColumn(managementTextField);
//        makeEditableColumn(designBrandingTextField);
//        makeEditableColumn(researchTextField);
//        makeEditableColumn(financeTextField);
//        makeEditableColumn(connectionsTextField);
//
//        matrix_tableview.setItems(skillsMatrixList);
//    }
//
//    private void makeEditableColumn(TableColumn<employeeData, Integer> column) {
//        column.setCellValueFactory(new PropertyValueFactory<>("columnName")); // Replace "columnName" with the actual property name
//        column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//
//        // Handle edit commit event
//        column.setOnEditCommit(event -> {
//            int rowIndex = event.getTablePosition().getRow();
//            employeeData rowData = event.getTableView().getItems().get(rowIndex);
//
//            // Update the corresponding property in your data model
//            if (column == productKnowledgeTextField) {
//                rowData.setProductKnowledgeTextField(event.getNewValue());
//            } else if (column == codingTextField) {
//                rowData.setCodingTextField(event.getNewValue());
//            } else if (column == managementTextField) {
//                rowData.setManagementTextField(event.getNewValue());
//            } else if (column == financeTextField) {
//                rowData.setFinanceTextField(event.getNewValue());
//            } else if (column == researchTextField) {
//                rowData.setResearchTextField(event.getNewValue());
//            } else if (column == connectionsTextField) {
//                rowData.setConnectionsTextField(event.getNewValue());
//            } else if (column == designBrandingTextField) {
//                rowData.setDesignBrandingTextField(event.getNewValue());
//            }
//
//            // Repeat the above for other columns...
//
//            // You might also want to update the database here if needed
//
//            matrix_tableview.refresh(); // Refresh the table to reflect the changes
//        });
//    }

    public void skillsMatrixUpdate() {
       // TableView<employeeData> table = new TableView<employeeData>();
        matrix_tableview.setEditable(true);

        matrix_empID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        matrix_empID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        matrix_firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        matrix_firstname.setCellFactory(TextFieldTableCell.forTableColumn());

        productKnowledgeTextField.setCellValueFactory(new PropertyValueFactory<>("productKnowledge"));
        productKnowledgeTextField.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        productKnowledgeTextField.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<employeeData, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<employeeData, Integer> event) {
                employeeData data = event.getRowValue();
                data.setCodingTextField(event.getNewValue());
            }
        });

        codingTextField.setCellValueFactory(new PropertyValueFactory<>("coding"));
        codingTextField.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codingTextField.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<employeeData, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<employeeData, Integer> event) {
                employeeData data = event.getRowValue();
                data.setCodingTextField(event.getNewValue());
            }
        });

        managementTextField.setCellValueFactory(new PropertyValueFactory<>("management"));
        managementTextField.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        managementTextField.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<employeeData, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<employeeData, Integer> event) {
                employeeData data = event.getRowValue();
                data.setCodingTextField(event.getNewValue());
            }
        });

        designBrandingTextField.setCellValueFactory(new PropertyValueFactory<>("designBranding"));
        designBrandingTextField.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        designBrandingTextField.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<employeeData, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<employeeData, Integer> event) {
                employeeData data = event.getRowValue();
                data.setCodingTextField(event.getNewValue());
            }
        });

        researchTextField.setCellValueFactory(new PropertyValueFactory<>("research"));
        researchTextField.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        researchTextField.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<employeeData, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<employeeData, Integer> event) {
                employeeData data = event.getRowValue();
                data.setCodingTextField(event.getNewValue());
            }
        });

        financeTextField.setCellValueFactory(new PropertyValueFactory<>("finance"));
        financeTextField.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        financeTextField.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<employeeData, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<employeeData, Integer> event) {
                employeeData data = event.getRowValue();
                data.setCodingTextField(event.getNewValue());
            }
        });

        connectionsTextField.setCellValueFactory(new PropertyValueFactory<>("connections"));
        connectionsTextField.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        connectionsTextField.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<employeeData, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<employeeData, Integer> event) {
                employeeData data = event.getRowValue();
                data.setCodingTextField(event.getNewValue());
            }
        });
    }


    public void defaultNav() {
        home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #9b59b6, #3498db);");
    }

    public void displayUsername() {
        username.setText(getData.username);
    }


    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(false);
            skill_form.setVisible(false);
            training_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #9b59b6, #3498db);");
            addEmployee_btn.setStyle("-fx-background-color:transparent");
            salary_btn.setStyle("-fx-background-color:transparent");
            matrix_btn.setStyle("-fx-background-color:transparent");
            training_btn.setStyle("-fx-background-color:transparent");

            homeTotalInactive();
            homeTotalEmployees();
            homeEmployeeTotalPresent();
            homeChart();

        } else if (event.getSource() == addEmployee_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(true);
            salary_form.setVisible(false);
            skill_form.setVisible(false);
            training_form.setVisible(false);

            addEmployee_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #9b59b6, #3498db);");
            home_btn.setStyle("-fx-background-color:transparent");
            salary_btn.setStyle("-fx-background-color:transparent");
            matrix_btn.setStyle("-fx-background-color:transparent");
            training_btn.setStyle("-fx-background-color:transparent");

            addEmployeeGenderList();
            addEmployeePositionList();
            addEmployeeSearch();

        } else if (event.getSource() == salary_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(true);
            skill_form.setVisible(false);
            training_form.setVisible(false);

            salary_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #9b59b6, #3498db);");
            addEmployee_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            matrix_btn.setStyle("-fx-background-color:transparent");
            training_btn.setStyle("-fx-background-color:transparent");


            salaryShowListData();
        } else if (event.getSource() == matrix_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(false);
            skill_form.setVisible(true);
            training_form.setVisible(false);

            salary_btn.setStyle("-fx-background-color:transparent");
            addEmployee_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            matrix_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #9b59b6, #3498db);");
            training_btn.setStyle("-fx-background-color:transparent);");

           // skillsMatrixShowListData();
            skillsMatrixUpdate();

        } else if (event.getSource() == training_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(false);
            skill_form.setVisible(false);
            training_form.setVisible(true);

            salary_btn.setStyle("-fx-background-color:transparent");
            addEmployee_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            matrix_btn.setStyle("-fx-background-color:transparent");
            training_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #9b59b6, #3498db);");

        }

    }

    private double x = 0;
    private double y = 0;

    public void logout() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        displayUsername();
        defaultNav();

        addEmployeeShowListData();
        addEmployeeGenderList();
        addEmployeePositionList();

        homeTotalInactive();
        homeTotalEmployees();
        homeEmployeeTotalPresent();
        homeChart();

        salaryShowListData();
       // skillsMatrixShowListData();
        skillsMatrixUpdate();
    }
}
