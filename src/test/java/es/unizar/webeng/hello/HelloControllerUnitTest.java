package es.unizar.webeng.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)			//JUnit will invoke the SpringRunner class to run the tests in that class instead of the runner built into JUnit.
@WebMvcTest(HelloController.class)		// Using this annotation will disable full auto-configuration and instead apply only configuration relevant to MVC tests.

public class HelloControllerUnitTest {

    @Value("${app.message:Hello World}")		// Annotation that indicates a default value expression for the affected argument.
    private String message;

    @Autowired									// Annotation to auto wire bean on the field.
    private HelloController controller;

    /**
     * This test method checks wether the controller was creted successfully and its contents
     * are correct.
     * 
     * @throws Exception if the controller is not as expected and does not contain a correct 'message' value.
     */
    @Test
    public void testMessage() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.welcome(map);
        assertThat(view, is("welcome"));
        assertThat(map.containsKey("message"), is(true));
        assertThat(map.get("message"), is(message));
    }
}
