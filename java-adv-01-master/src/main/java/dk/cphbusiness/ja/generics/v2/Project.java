/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.ja.generics.v2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author RolfMoikjær
 */
public class Project {

    private final int id;
    private String title;
    private List<Employee> employees = new ArrayList();

    public Project(int id, String title, List<Employee> employees) {
        this.id = id;
        this.title = title;
        this.employees = employees;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Collection<Department> getDepartments(List<Employee> employees) {
        List<Department> departments = new ArrayList();
        for (Employee employee : employees) {

            if (departments.contains(employee.getDepartment())) {
                continue;
            }
            departments.add(employee.getDepartment());
        }
        return departments;
    }

}
