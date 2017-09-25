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

/**
*   @RunWith(SpringRunner.class)  Annotation from Junit, Junit will run using Spring's testing support.
*	 SpringRunner is the new name for SpringJUnit4ClassRunner.
*	{@link http://junit.sourceforge.net/javadoc/org/junit/runner/RunWith.html}
*
*   @WebMvcTest(HelloController.class)  Start the Spring application context without the server, 
*	in that case Spring Boot is only instantiating one controller HelloController.
*	{@link https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/web/servlet/WebMvcTest.html}
*
*   @Value("${app.message:Hello World}")  We can inject the value from a file into 
*	a variable with that syntax  @Value("${value.from.file}"), in case it's not defined 
*	we use double dots @Value("${unknown.param:Hello World}") and the text Hello World will be injected.
*	{@link https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Value.html}

*   @Autowired This annotation will scan on the packpage for annotations like @Controller,
*	 @Component, @Repository or simple bean, to auto-inject all the necesary in the variable.
*	{@link https://docs.spring.io/spring-framework/docs/2.5.x/api/org/springframework/beans/factory/annotation/Autowired.html}
*/
@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerUnitTest {

    @Value("${app.message:Hello World}")
    private String message;

    @Autowired
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
