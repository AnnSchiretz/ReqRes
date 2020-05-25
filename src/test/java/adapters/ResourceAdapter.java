package adapters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Resource;
import models.ResourceList;
import org.apache.http.protocol.HTTP;

import static io.restassured.RestAssured.given;

public class ResourceAdapter extends MainAdapter {
    public ResourceList get(String path) {
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

        return gson.fromJson(response.asString().trim(), ResourceList.class);
    }

    public Resource getResource(String path) {
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

        return gson.fromJson(response.asString().trim(), Resource.class);
    }

}
