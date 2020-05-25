package adapters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.JobUser;
import models.User;
import org.apache.http.protocol.HTTP;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class DeleteAdapter extends MainAdapter {
    public Response delete(String path) {
        Response response =
                given()
                        .when()
                        .delete(path)
                        .then()
                        .statusCode(204)
                        .header("server", "cloudflare").and()
                        .extract().response();
        assertEquals(response.getStatusCode(), 204);
        return response;
    }
}
