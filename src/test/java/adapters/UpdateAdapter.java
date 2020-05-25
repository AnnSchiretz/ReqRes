package adapters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.UpdateUser;
import org.apache.http.protocol.HTTP;

import static io.restassured.RestAssured.given;

public class UpdateAdapter extends MainAdapter {

    public UpdateUser put(UpdateUser user, String path) {
        Response response =
                given()
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .body(gson.toJson(user))
                        .when()
                        .put(path)
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON).extract().response();
        return gson.fromJson(response.asString().trim(), UpdateUser.class);
    }

    public UpdateUser patch(UpdateUser user, String path) {
        Response response =
                given()
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .body(gson.toJson(user))
                        .when()
                        .patch(path)
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON).extract().response();
        return gson.fromJson(response.asString().trim(), UpdateUser.class);

    }
}
