package dk.cphbusiness.ja.generics.v1;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class GenericsAndMoreTest {
  Department adm, hr, it;
  Employee kurt, sonja, ib;

  @Before
  public void setUp() {
    adm = new Department("ADM", "Administration");
    hr = new Department("HR", "Human Resources");
    it = new Department("IT", "IT");
    
    kurt = new Employee(7, "Kurt Hansen", 25000.00, adm);
    sonja = new Employee(9, "Sonja Jensen", 27000.00, hr);
    ib = new Employee(13, "Ib Hansen", 30000.00, it);
    }
  
  @Test
  public void testSetUp() {
    assertThat(adm.totalSalary(), is(25000.00));
    assertThat(hr.totalSalary(), is(27000.00));
    assertThat(it.totalSalary(), is(30000.00));
    } 
  
  }

