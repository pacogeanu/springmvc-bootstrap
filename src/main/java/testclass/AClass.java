package testclass;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;

// Spring 3.1 introduces @PropertySource annotation, as a 
// convenient mechanism for adding property sources to the environment.
// @PropertySource is to be used in conjunction with Java based configuration
// and the @Configuration annotation
@Configuration
@PropertySource("classpath:/test/test.properties")
public class AClass {
	
	@Value("${prop1}")
	String prop1;
	
	@Value("${prop2}")
	String prop2;
	
	@Autowired
	private Environment env;
	
	public void printProperties() {
		System.out.println("Prop1: "+ env.getProperty("prop1"));
		System.out.println("Prop2: "+ prop2);
	}
	
	public void PrintAllPropertiesFromEnv() {
		Map<String, Object> map = new HashMap();
        for(Iterator it = ((AbstractEnvironment) env).getPropertySources().iterator(); it.hasNext(); ) {
        org.springframework.core.env.PropertySource propertySource = (org.springframework.core.env.PropertySource) it.next();
            if (propertySource instanceof MapPropertySource) {
                map.putAll(((MapPropertySource) propertySource).getSource());
            }
        }
      
    for (Map.Entry<String, Object> entry : map.entrySet()) {
    System.out.println("Key : " + entry.getKey() + " Value : " + (String)entry.getValue());
    }
	}
	
	// @PropertySource annotation does not automatically register a 
	// PropertySourcesPlaceholderConfigurer with Spring
	// This bean must be explicitly defined in the configuration 
	// to get the property resolution mechanism working
	@Bean
	public static PropertySourcesPlaceholderConfigurer xxxpropertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public String getProp1() {
		return prop1;
	}

	public String getProp2() {
		return prop2;
	}
	
}
