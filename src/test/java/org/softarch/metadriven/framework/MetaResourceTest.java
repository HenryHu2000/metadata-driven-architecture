package org.softarch.metadriven.framework;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class MetaResourceTest {

    @Test
    public void testMetadataEndpoint() {
        given()
          .when().get("/metadata")
          .then()
             .statusCode(200)
             .body(notNullValue());
    }

}