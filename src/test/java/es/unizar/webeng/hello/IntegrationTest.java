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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext
public class IntegrationTest {

    @LocalServerPort
    private int port = 0;

    /**
     * This test method checks wether the 'Home' page returns a correct response status and body or not.
     * If the body returned is not the expected or the connection fails, the test will not pass.
     * @throws Exception
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
     * This test method checks wether the website's CSS returns a correct response status and body or not,
     * and also if the sheet returned is the expected.
     * If the connection fails, the body is not valid or the sheet is other than specified, the test
     * will not pass.
     * @throws Exception
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
