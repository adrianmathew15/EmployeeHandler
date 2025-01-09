package com.example.employeematrix;

import java.sql.Date;

public class employeeData {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNum;
    private String position;
    private String image;
    private Date date;
    private Double salary;
    private int codingTextField;
    private int connectionsTextField;
    private int designBrandingTextField;
    private int financeTextField;
    private int managementTextField;
    private int productKnowledgeTextField;
    private int researchTextField;

    public employeeData(Integer employeeId, String firstName, String lastName, 
    String gender, String phoneNum, String position, String image, Date date){
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.position = position;
        this.image = image;
        this.date = date;
    }
    public employeeData(Integer employeeId, String firstName, String lastName,
      String position, Double salary){
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
    }

    public employeeData(Integer employeeId, String firstName, int codingTextField, int connectionsTextField,
                        int designBrandingTextField,
                        int financeTextField, int managementTextField,
                        int productKnowledgeTextField, int researchTextField) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.codingTextField = codingTextField;
        this.connectionsTextField = connectionsTextField;
        this.designBrandingTextField = designBrandingTextField;
        this.financeTextField = financeTextField;
        this.managementTextField = managementTextField;
        this.productKnowledgeTextField = productKnowledgeTextField;
        this.researchTextField = researchTextField;
    }

    public Integer getEmployeeId(){
        return employeeId;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getGender(){
        return gender;
    }
    public String getPhoneNum(){
        return phoneNum;
    }
    public String getPosition(){
        return position;
    }
    public String getImage(){
        return image;
    }
    public Date getDate(){
        return date;
    }
    public Double getSalary(){
        return salary;
    }

    public int getCodingTextField() {
        return codingTextField;
    }

    public int getConnectionsTextField() {
        return connectionsTextField;
    }

    public int getDesignBrandingTextField() {
        return designBrandingTextField;
    }

    public int getFinanceTextField() {
        return financeTextField;
    }

    public int getManagementTextField() {
        return managementTextField;
    }

    public int getProductKnowledgeTextField() {
        return productKnowledgeTextField;
    }

    public int getResearchTextField() {
        return researchTextField;
    }

    public void setCodingTextField(int codingTextField) {
        this.codingTextField = codingTextField;
    }

    public void setConnectionsTextField(int connectionsTextField) {
        this.connectionsTextField = connectionsTextField;
    }

    public void setDesignBrandingTextField(int designBrandingTextField) {
        this.designBrandingTextField = designBrandingTextField;
    }

    public void setFinanceTextField(int financeTextField) {
        this.financeTextField = financeTextField;
    }

    public void setManagementTextField(int managementTextField) {
        this.managementTextField = managementTextField;
    }

    public void setProductKnowledgeTextField(int productKnowledgeTextField) {
        this.productKnowledgeTextField = productKnowledgeTextField;
    }

    public void setResearchTextField(int researchTextField) {
        this.researchTextField = researchTextField;
    }
}

