package guru.qa.restbackend;

import guru.qa.domain.Users;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.with;

public class BankControllerTests {

    static {
        RestAssured.baseURI = "http://localhost";
    }
    private RequestSpecification spec =
            with()
                    .baseUri("http://localhost:8080")
                    .log().all()
                    .basePath("/");

    @Test
    void BankControllerTest() {
        Users[] users =
        spec.get("user/getAll")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response()
                .as(Users[].class);
    }

}
