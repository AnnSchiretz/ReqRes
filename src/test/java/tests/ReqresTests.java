package tests;

import adapters.UsersAdapter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.JobUser;
import models.UsersList;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.when;
import static org.testng.Assert.assertEquals;

public class ReqresTests extends BaseTest {

    @Test
    public void testList() throws FileNotFoundException {
        UsersList expectedList;
        expectedList = gson.fromJson(new FileReader("src/test/java/resources/expectedList.json"), UsersList.class);

        UsersList list = new UsersAdapter().get(1);
        assertEquals(list, expectedList);
    }
    
    @Test
    public void createUser() {
        JobUser user = new UsersAdapter().post(new JobUser("name", "job", "", ""));
        System.out.println(user);
    }
    @Test
    public void singleUser(){
        Response response = when()
                .get("https://reqres.in/api/users/2")
        .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();
        assertEquals("", response.asString().trim());

    }
}
