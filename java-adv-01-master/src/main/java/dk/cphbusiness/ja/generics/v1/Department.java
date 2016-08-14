package dk.cphbusiness.ja.generics.v1;

public class Department {
  private final String code;
  private String name;
  private final EmployeeList employees = new EmployeeList();

  public double totalSalary() {
    double total = 0.0;
    for (int i = 0; i < employees.size(); i++) {
      Employee employee = employees.get(i);
      total = total + employee.getSalary();
      }
    return total;
    }
  
  public EmployeeList getEmployees() { return employees; }
  
  public Department(String code, String name) {
    this.code = code;
    this.name = name;
    }

  public String getCode() { return code; }

  public String getName() { return name; }
  public void setName(String value) { this.name = value; }

  }
