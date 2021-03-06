package adapters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.JobUser;
import models.Register;
import models.RegisterInfo;
import models.UsersList;
import org.apache.http.protocol.HTTP;

import static io.restassured.RestAssured.given;

public class UsersAdapter extends MainAdapter {

    public JobUser post(JobUser user, String path) {

        Response response =
        given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .body(gson.toJson(user))
        .when()
                .post(path)
        .then()
                .statusCode(201)
                .contentType(ContentType.JSON).extract().response();

        return gson.fromJson(response.asString().trim(), JobUser.class);
    }

    public UsersList get(String path) {

        Response response =
                given()
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().all()
                        .when()
                        .get(path)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .contentType(ContentType.JSON).extract().response();

        return gson.fromJson(response.asString().trim(), UsersList.class);
    }

    public Response post(Register user, String path) {
        Response response =
                given()
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .body(gson.toJson(user))
                        .when()
                        .post(path)
                        .then()
                        .contentType(ContentType.JSON).extract().response();
        return response;
    }
}
