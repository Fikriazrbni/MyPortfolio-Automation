package Definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class stepDefinitionApi {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PET_ENDPOINTGET = "/pet/";
    private static final String PET_ENDPOINTPOST = "/pet";
    private static final String PET_ENDPOINTORDER = "/store/order";
    private static final String PET_ENDPOINTORDERDETAIL = "/store/order/";
    private static final String PET_ENDPOINTCREATEUSER = "/user";
    private static final String PET_ENDPOINTLOGIN = "/user/login";
    private static final String PET_ENDPOINTLOGOUT = "/user/logout";
    private static final String PET_ENDPOINTDELETEUSER = "/user/";
    private Response response;
    String petId = null;
    String id = null;
    String petName = null;
    JsonPath json;
    JSONObject object = new JSONObject();

    //=============================== Func Method =============================//
    public static boolean verifyResponse(String expected, Response response, String title) {
        String actual = String.valueOf(response.getStatusCode());
        try {
            Assert.assertEquals(actual, expected);
            System.out.println(title+" | AssertTrue >>>>>>>>>> Expected : [" + expected + "]  -  " + "Actual : [" + actual + "]");
            return true;
        } catch (AssertionError ee) {
            System.out.println(title+" | AssertFalse >>>>>>>>> Expected : [" + expected + "]  -  " + "Actual : [" + actual + "]");
            response.getBody().prettyPrint();
            return false;
        }
    }
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        // Create a MessageDigest instance for the SHA-256 algorithm
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Hash the password
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // Convert the hashed password to a hexadecimal string
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedPassword) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
//    @Test
//    public void test() throws NoSuchAlgorithmException {
//        System.out.println(hashPassword("fikri123"));
//    }

    //=============================== Test Method =============================//
    @Given("post to create new pet")
    public void postToCreateNewPet() {
        String requestBody = "{ \"id\": 0, \"category\": { \"id\": 0, \"name\": \"string\" }, \"name\": \"ngehe\", \"photoUrls\": [ \"string\" ], \"tags\": [ { \"id\": 0, \"name\": \"string\" } ], \"status\": \"available\" }";
        response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + PET_ENDPOINTPOST);
        verifyResponse("200", response, "create new pet response");
    }

    @Then("I get a response {int} and the pet ID")
    public void iGetAResponseAndThePetID(int arg0) {
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        petId = json.get("id").toString();
        System.out.println("post : " + petId);
        assertEquals(arg0, response.getStatusCode());
        verifyResponse("200", response, "get pet detail response");
    }

    @And("a get request with pet ID")
    public void a_get_request_with_pet_id() {
        Response responseGet = given().when().get(BASE_URL + PET_ENDPOINTGET + petId);
        JsonPath json = responseGet.jsonPath();
        petName = json.getString("name");
        String status = json.getString("status");
        verifyResponse("200", response, "get new pet id response");
    }

    @Then("I get a response {int} and the pet name")
    public void i_get_a_response_and_the_pet_name(Integer int1) {
        System.out.println(petId);
        assertEquals(response.getStatusCode(), int1);
    }

    @And("post to create an order from store")
    public void postToCreateAnOrderFromStore() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 10);
        requestBody.put("petId", petId);
        requestBody.put("quantity", 1);
        requestBody.put("shipDate", "2023-09-21T07:16:00.450Z");
        requestBody.put("status", "placed");
        requestBody.put("complete", true);

        response = given()
                .contentType("application/json")
                .body(requestBody)
                .post(BASE_URL + PET_ENDPOINTORDER);
        verifyResponse("200", response, "place order response");
        json = response.jsonPath();
        id = json.getString("id");
        System.out.println("order id : "+id);
    }

    @Then("get detail of order")
    public void getDetailOfOrder() {
        response = given().when().get(BASE_URL + PET_ENDPOINTORDERDETAIL+id);
        verifyResponse("200", response, "get order detail response");

    }

    @And("delete an order")
    public void deleteAnOrder() {
        response = given()
                .contentType("application/json")
                .delete(BASE_URL+PET_ENDPOINTORDERDETAIL+id);
        verifyResponse("200", response, "delete order response");
    }

    @And("create a new user")
    public void createANewUser() {
        JSONObject object = new JSONObject();
        object.put("id","0");
        object.put("username","fikriAzrbni");
        object.put("firstName","fikri");
        object.put("lastName","rabbani");
        object.put("email","fikriAzrbni@gmail.com");
        object.put("password","fikri123");
        object.put("phone","0818080");
        object.put("userStatus","0");
        response = given()
                .contentType("application/json")
                .body(object)
                .post(BASE_URL+PET_ENDPOINTCREATEUSER);
        verifyResponse("200", response, "create user response");
    }

    @Then("login with new user")
    public void loginWithNewUser() throws NoSuchAlgorithmException {
        object.clear();
        object.put("username", "fikriAzrbni");
        object.put("password", hashPassword("fikri123"));

        response = given()
                .contentType("application/json")
                .body(object)
                .get(BASE_URL+PET_ENDPOINTLOGIN);
        verifyResponse("200", response, "login response");
    }

    @Then("logout")
    public void logout() {
        response = given()
                .contentType("application/json")
                .get(BASE_URL+PET_ENDPOINTLOGOUT);
        verifyResponse("200", response, "logout response");
    }

    @Then("delete user")
    public void deleteUser() {
        object.clear();
        object.put("username", "fikriAzrbni");

        response = given()
                .body(object)
                .delete(BASE_URL+PET_ENDPOINTDELETEUSER);
        verifyResponse("200", response, "deleted user response");
    }
    @And("login with deleted user")
    public void loginWithDeletedUser() {
        object.clear();
        object.put("username", "fikriAzrbni");
        object.put("password", "fikri123");

        response = given()
                .contentType("application/json")
                .body(object)
                .get(BASE_URL + PET_ENDPOINTLOGIN);
        verifyResponse("405", response, "login with deleted user response");
    }
}
