import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
public class APITests {
    @BeforeClass
    public void setUp() {
        baseURI = "https://www.24mx.ie/INTERSHOP/rest/WFS/Pierce-24mx-Site/24mx-ie/";
    }

    @Test
    public void testResponseStatus() {
        given()
                .get()
                .then()
                .statusCode(200);
    }

    @Test
    public void testNumberOfElements() {
        given()
                .get()
                .then()
                .body("elements.size()", greaterThanOrEqualTo(25));
    }

    @Test
    public void testElementTypesAndURIs() {
        given()
                .get()
                .then()
                .body("elements.type", everyItem(notNullValue()))
                .body("elements.uri", everyItem(notNullValue()));
    }

    @Test
    public void testElementTitles() {
        given()
                .get()
                .then()
                .body("elements.title", everyItem(not(emptyOrNullString())));
    }

    @Test
    public void testSpecificElement() {
        given()
                .get()
                .then()
                .body("elements.find { it.title == 'cmspages' }.uri", equalTo("Pierce-24mx-Site/24mx-ie/cmspages"));
    }
}
