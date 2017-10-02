package es.unizar.webeng.hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
*   Annotations used in the code:<br>
*	{@code @Before}: Advice annotation. Advice is associated with a pointcut expression, and runs,
*	in this case, before method executions matched by the pointcut, but it is also possible to use after or around.
*	The pointcut expression may be either a simple reference to a named pointcut, or a pointcut expression declared in place.
*	<p>
*	{@code @Test}: JUnit annotation that specify that the public void method to which it is attached can be run as a test case.
*	To run the method, JUnit first constructs a fresh instance of the class then invokes the annotated method.
*	Any exceptions thrown by the test will be reported by JUnit as a failure. If no exceptions are thrown, the test is assumed to have succeeded.
*
*	@see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-advice">spring.io Before</a>
*	@see <a href="http://junit.sourceforge.net/javadoc/org/junit/Test.html">sourceforge.net Test</a>
*/

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class StaticContentUnitTest {

    @Autowired
    private WebApplicationContext wac;

    @Value("${app.message:Hello World}")
    private String message;

    private MockMvc mockMvc;

    /**
     * This method sets up a global mock class for the rest of the test methods.
     * Before specifies that it should be executed first automatically, previous to any tests.
     */
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * This test method checks if a 'Get' request to "/" returns a valid variable "message"
     * and if its content is correct.
     * @throws Exception if the request returns an invalid or incorrect message.
     */
    @Test
    public void testMessage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", is(message)));
    }
}
