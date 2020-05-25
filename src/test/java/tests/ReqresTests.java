package tests;

import adapters.DeleteAdapter;
import adapters.ResourceAdapter;
import adapters.UpdateAdapter;
import adapters.UsersAdapter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.*;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ReqresTests extends BaseTest {

    @Test
    public void getUserList() throws FileNotFoundException {
        UsersList expectedList;
        expectedList = gson.fromJson(new FileReader("src/test/java/resources/expectedList.json"), UsersList.class);
        String path = "https://reqres.in/api/users?page=2";
        UsersList list = new UsersAdapter().get(path);
        assertEquals(list, expectedList);
    }

    @Test
    public void createUser() {
        String path = "https://reqres.in/api/users";
        JobUser user = new UsersAdapter().post(new JobUser("morpheus", "leader", "", ""), path);
        System.out.println(user.toString());
    }

    @Test
    public void singleUser() {
        String actual = "{\"data\":{\"id\":2,\"email\":\"janet.weaver@reqres.in\",\"first_name\":\"Janet\",\"last_name\":\"Weaver\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg\"}}";
        Response response = when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();
        assertEquals(actual, response.asString().trim());

    }

    @Test
    public void singleUserNotFound() {
        Response res = when()
                .get("https://reqres.in/api/users/24")
                .then()
                .log().all()
                .statusCode(404)
                .contentType(ContentType.JSON).extract().response();
        assertEquals(404, res.getStatusCode());
    }

    @Test
    public void getListResource() throws FileNotFoundException {
        String path = "https://reqres.in/api/unknown";
        ResourceList resource = new ResourceAdapter().get(path);
        ResourceList expectedList = gson.fromJson(new FileReader("src/test/java/resources/expectedListResource.json"), ResourceList.class);
        assertEquals(resource, expectedList);
    }

    @Test
    public void getSingleResource() throws FileNotFoundException {
        String path = "https://reqres.in/api/unknown/5";
        Resource resourceSingle = new ResourceAdapter().getResource(path);
        Resource expectedResource = gson.fromJson(new FileReader("src/test/java/resources/expectedResource.json"), Resource.class);
        assertEquals(resourceSingle, expectedResource);
    }

    @Test
    public void notFoundResource() {
        Response res = when()
                .get("https://reqres.in/api/unknown/88")
                .then()
                .log().all()
                .statusCode(404)
                .contentType(ContentType.JSON).extract().response();
        assertEquals(404, res.getStatusCode());
    }

    @Test
    public void updateUser() {
        String path = "https://reqres.in/api/users/2";
        UpdateUser user = new UpdateAdapter().put(new UpdateUser("name", "job", ""),
                path);
        System.out.println(user.toString());
    }

    @Test
    public void updateUserByPatch() {
        String path = "https://reqres.in/api/users/2";
        UpdateUser secondUser = new UpdateAdapter().patch(new UpdateUser("name", "job", ""),
                path);
        System.out.println(secondUser.toString());
    }

    @Test
    public void deleteUser() {
        String path = "https://reqres.in/api/users/7";
        Response res = new DeleteAdapter().delete(path);
        System.out.println(res.asString());
    }

    @Test
    public void register() {
        String path = "https://reqres.in/api/register";
        Response info = new UsersAdapter().post(new Register("eve.holt@reqres.in", "pistol"), path);
        System.out.println(info.asString());
        assertEquals(info.getStatusCode(), 200);
    }

    @Test
    public void unsuccessfulRegistration() {
        String path = "https://reqres.in/api/register";
        Response info = new UsersAdapter().post(new Register("sydney@fife"), path);
        assertEquals(info.getStatusCode(), 400);
    }

    @Test
    public void loginUser() {
        String path = "https://reqres.in/api/login";
        Response login = new UsersAdapter().post(new Register("eve.holt@reqres.in", "cityslicka"), path);
        System.out.println(login.asString());
        assertEquals(login.getStatusCode(), 200);
    }

    @Test
    public void unsuccessfulLogin() {
        String path = "https://reqres.in/api/login";
        Response login = new UsersAdapter().post(new Register("peter@klaven"), path);
        System.out.println(login.asString());
        assertEquals(login.getStatusCode(), 400);
        assertEquals(login.asString(), "{\"error\":\"Missing password\"}");
    }

    @Test
    public void delayedResponse() throws FileNotFoundException {
        UsersList expectedList;
        expectedList = gson.fromJson(new FileReader("src/test/java/resources/expectedDelayedList.json"), UsersList.class);
        String path = "https://reqres.in/api/users?delay=3";
        UsersList list = new UsersAdapter().get(path);
        assertEquals(list, expectedList);
    }
}
