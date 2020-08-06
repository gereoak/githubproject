/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.F_office;



import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Office {

    @Id
    private String roomnumber;
    private String building;
    @OneToMany(mappedBy = "office")
    private List<Employee> employees = new ArrayList<Employee>();

    public Office() {
    }

    public Office(String roomnumber, String building) {
        this.roomnumber = roomnumber;
        this.building = building;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee e) {
        this.employees.add(e);
        e.setOffice(this);
    }

    public void removeEmployee(Employee e) {
        this.employees.remove(e);
        e.setOffice(null);
    }
}
