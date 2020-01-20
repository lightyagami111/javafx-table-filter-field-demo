package com.sriram.tablefilterfielddemo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author sriram
 */
public class Employee {
    
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleIntegerProperty age = new SimpleIntegerProperty();
    private SimpleStringProperty city = new SimpleStringProperty();
    private SimpleStringProperty empId = new SimpleStringProperty();
    
    public SimpleStringProperty nameProperty() {
        return name;
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getName() {
        return name.get();
    }
    
    public SimpleIntegerProperty ageProperty() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age.set(age);
    }
    
    public Integer getAge() {
        return age.get();
    }
    
    public SimpleStringProperty cityProperty() {
        return city;
    }
    
    public String getCity() {
        return city.get();
    }
    
    public void setCity(String city) {
        this.city.set(city);
    }
    
    public SimpleStringProperty empIdProperty() {
        return empId;
    }
    
    public void setEmpId(String empId) {
        this.empId.set(empId);
    }
    
    public String getEmpId() {
        return empId.get();
    }
}
