package es.unizar.webeng.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
*	Annotations used in the code:<br>
*	<em>@SpringBootTest(webEnvironment = RANDOM_PORT)</em>: @SpringBootTest is 
*	an annotation which is used as an alternative to the standard spring-test 
*	<a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/context/ContextConfiguration.html">@ContextConfiguration</a> 
*	annotation (which defines class-level metadata that is used to determine 
*	how to load and configure an ApplicationContext for integration tests). <br>
*	The attribute webEnvironment refine how the tests will run, and in this case, 
*	the option RAMDOM_PORT is chosen to loads an <a href="https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/context/embedded/EmbeddedWebApplicationContext.html">EmbeddedWebApplicationContext</a>
*	and provides a real servlet environment. Embedded servlet containers are 
*	started and listening on a random port.
*	<p>
*	<em>@DirtiesContext</em>: Test annotation which indicates that the 
*	ApplicationContext associated with a test is dirty and should therefore 
*	be closed and removed from the context cache. When an ApplicationContext 
*	is marked dirty, it is removed from the testing framework's cache and closed.
*	<p>
*	<em>@LocalServerPort</em>: Injects the HTTP port that got allocated at runtime
*	into the variable.
*
*
*	@see <a href="https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/context/SpringBootTest.WebEnvironment.html">spring.io SpringBootTest.WebEnvironment</a>
*	@see <a href="https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/test/annotation/DirtiesContext.html">spring.io DirtiesContext</a>
*	@see <a href="https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/context/embedded/LocalServerPort.html">spring.io LocalServerPort</a>
*/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext
/**
 * This class check if the messages that are sent to the server contains what is expected.
 */
public class IntegrationTest {

	@LocalServerPort
	private int port = 0;

	/**
	 * This test method checks whether the 'Home' page returns a correct response status and body or not.
	 * 
	 * @throws Exception if the body returned is not the expected or the connection fails.
	 */
	@Test
	public void testHome() throws Exception {
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
				"http://localhost:" + this.port, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertTrue("Wrong body (title doesn't match):\n" + entity.getBody(), entity
				.getBody().contains("<title>Hello"));
	}

	/**
	 * This test method checks whether the website's CSS returns a correct response status and body or not,
	 * and also if the sheet returned is the expected.
	 * 
	 * @throws Exception if the connection fails, the body is not valid or the sheet is other than specified.
	 */
	@Test
	public void testCss() throws Exception {
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
				"http://localhost:" + this.port
						+ "/webjars/bootstrap/3.3.5/css/bootstrap.min.css", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertTrue("Wrong body:\n" + entity.getBody(), entity.getBody().contains("body"));
		assertEquals("Wrong content type:\n" + entity.getHeaders().getContentType(),
				MediaType.valueOf("text/css"), entity.getHeaders()
						.getContentType());
	}


}
