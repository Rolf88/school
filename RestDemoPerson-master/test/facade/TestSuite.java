package facade;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author plaul1
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({facade.PersonFacadeTest.class, facade.JSONConvertTest.class})
public class TestSuite {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }
  
}
