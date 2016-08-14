package dk.cphbusiness.ja.generics.v1;

public class Employee {
  private final int id;
  private String name;
  private double salary;
  private Department department;

  public Employee(int id, String name, double salary, Department department) {
    this.id = id;
    this.name = name;
    this.salary = salary;
    setDepartment(department);
    }

  public int getId() { return id; }

  public String getName() { return name; }
  public void setName(String value) { name = value; }

  public double getSalary() { return salary; }
  public void setSalary(double value) { salary = value; }

  public Department getDepartment() { return department; }
  
  public final void setDepartment(Department value) { 
    if (department != null) department.getEmployees().remove(this);
    department = value;
    // Now Department#employees only accepts Employees.
    if (department != null) department.getEmployees().add(this);
    }
  
  }


