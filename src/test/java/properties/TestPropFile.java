package properties;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import testclass.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AClass.class})
@TestPropertySource("classpath:/test/test.properties")
public class TestPropFile {
	@Autowired
	private Environment envTest;
	
	@Autowired
	private AClass aClass;

	@Test
	public void test() {
		assertTrue(aClass.getProp1().equals(envTest.getProperty("prop1")));
		assertTrue(aClass.getProp2().equals(envTest.getProperty("prop2")));		
		aClass.printProperties();
		aClass.PrintAllPropertiesFromEnv();
	}
}
