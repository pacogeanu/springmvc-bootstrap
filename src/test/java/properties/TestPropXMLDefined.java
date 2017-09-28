package properties;

import static org.junit.Assert.*;
import testclass.*;

import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// using properties from controller

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test/configServletParams.xml")
public class TestPropXMLDefined {
	private String PROP1 = "value1";
	private String PROP2 = "value2";
	private static Properties PROPERTIES = new Properties();
	
	@Value("#{injectableProperties}")
	private Properties props;

	@Value("${prop1}")
	private String prop1; ;
	
	@Value("${prop2}")
	private String prop2 ;
	
	@BeforeClass
	public static void runOnlyOnce(){
		PROPERTIES.setProperty("prop1", "value1");
		PROPERTIES.setProperty("prop2", "value2");
	}

	@Test
	public void test() {
		assertTrue(PROP1.equals(prop1));
		assertTrue(PROP2.equals(prop2));
		assertTrue(PROPERTIES.getProperty("prop2").equals(props.getProperty("prop2")));
	}

}
