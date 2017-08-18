package p1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasEntry;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import controllers.HelloController;
import controllers.MyWebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MyWebConfig.class)
public class HelloControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
	    DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
	    this.mockMvc = builder.build();
	}
	
	@Test
    public void testHelloController() throws Exception {
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        ResultMatcher msg = MockMvcResultMatchers.model()
                            .attribute("message", "Hello, Spring MVC Framework!");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/hello");
        this.mockMvc.perform(builder).andExpect(ok).andExpect(msg);
	}
	
	@Test
    public void testHelloControllerViewNameAndMessageAttrVal() throws Exception {
		HelloController controller = this.wac.getBean(HelloController.class);
		ModelMap modelMap = new ModelMap();
		
		String viewName = controller.printHello(modelMap);
		String expectedViewName = "helloview";
		assertEquals(expectedViewName, viewName);
		
		String expectedMessageAttrValue = "Hello, Spring MVC Framework!";	
		assertThat(modelMap, hasEntry("message", (Object) expectedMessageAttrValue));
		assertThat("Bla Bla", modelMap, hasEntry("message", (Object) expectedMessageAttrValue));
	}
}
