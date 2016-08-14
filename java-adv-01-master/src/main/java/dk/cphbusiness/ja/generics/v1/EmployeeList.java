package dk.cphbusiness.ja.generics.v1;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {
  private final List items = new ArrayList();
  
  public void remove(Employee employee) {
    items.remove(employee);
    }
  
  public void add(Employee employee) {
    items.add(employee);
    }
   
  public Employee get(int index) {
    return (Employee)items.get(index);
    }
  
  public int size() {
    return items.size();
    }

  }


