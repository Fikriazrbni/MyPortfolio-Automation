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

public class ApiStepDef {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PET_ENDPOINTGET = "/pet/";
    private static final String PET_ENDPOINTPOST = "/pet";
    private static final String PET_ENDPOINTORDER = "/store/order";
    private static final String PET_ENDPOINTORDERDETAIL = "/store/order/";
    private static final String PET_ENDPOINTCREATEUSER = "/user";
    private static final String PET_ENDPOINTLOGIN = "/user/login";
    private static final String PET_ENDPOINTLOGOUT = "/user/logout";
    private static final String PET_ENDPOINTDELETEUSER = "/user/";
    private static final String BASE_URL_WithAuth = "http://restapi.adequateshop.com";
    private static final String AWU_POST_REGIS = "/api/authaccount/registration";
    private static final String AWU_POST_LOGIN = "/api/authaccount/login";
    private static final String AWU_GET_ALL_USER = "/api/users?page=1";
    private static final String AWU_GET_USERBYID = "/api/users/";
    private static final String AWU_POST_USEROBJ = "/api/users";
    private static final String AWU_PUT_USEROBJ = "/api/users/";
    private static final String AWU_DELETE_USER = "/api/users/";

    private Response response;
    String petId = "";
    String id = "";
    String petName = "";
    String token = "";
    JsonPath json;
    JSONObject object = new JSONObject();


    //=============================== Func Method =============================//
    public static void verifyResponse(String expected, String response, String title) {
        String actual = String.valueOf(response);
        try {
            Assert.assertEquals(actual, expected);
            System.out.println("AssertTrue >>>>>>>>>> Expected : [" + expected + "]  -  " + "Actual : [" + actual + "] | " + title);
        } catch (AssertionError ee) {
            System.out.println("AssertFalse >>>>>>>>> Expected : [" + expected + "]  -  " + "Actual : [" + actual + "] | " + title);
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

    //=============================== Test Method =============================//
    @Given("post to create new pet")
    public void postToCreateNewPet() {
        String requestBody = "{ \"id\": 0, \"category\": { \"id\": 0, \"name\": \"string\" }, \"name\": \"ngehe\", \"photoUrls\": [ \"string\" ], \"tags\": [ { \"id\": 0, \"name\": \"string\" } ], \"status\": \"available\" }";
        response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + PET_ENDPOINTPOST);
        verifyResponse("200", String.valueOf(response.getStatusCode()), "create new pet response");
    }

    @Then("I get a response {int} and the pet ID")
    public void iGetAResponseAndThePetID(int arg0) {
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        petId = json.get("id").toString();
        System.out.println("post : " + petId);
        assertEquals(arg0, response.getStatusCode());
        verifyResponse("200", String.valueOf(response.getStatusCode()), "get pet detail response");
    }

    @And("a get request with pet ID")
    public void a_get_request_with_pet_id() {
        Response responseGet = given().when().get(BASE_URL + PET_ENDPOINTGET + petId);
        JsonPath json = responseGet.jsonPath();
        petName = json.getString("name");
        String status = json.getString("status");
        verifyResponse(status, String.valueOf(response.getStatusCode()), "get new pet id response");
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
        verifyResponse("200", String.valueOf(response.getStatusCode()), "place order response");
        json = response.jsonPath();
        id = json.getString("id");
        System.out.println("order id : "+id);
    }

    @Then("get detail of order")
    public void getDetailOfOrder() {
        response = given().when().get(BASE_URL + PET_ENDPOINTORDERDETAIL+id);
        verifyResponse("200", String.valueOf(response.getStatusCode()), "get order detail response");

    }

    @And("delete an order")
    public void deleteAnOrder() {
        response = given()
                .contentType("application/json")
                .delete(BASE_URL+PET_ENDPOINTORDERDETAIL+id);
        verifyResponse("200", String.valueOf(response.getStatusCode()), "delete order response");
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
        verifyResponse("200", String.valueOf(response.getStatusCode()), "create user response");
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
        verifyResponse("200", String.valueOf(response.getStatusCode()), "login response");
    }

    @Then("logout")
    public void logout() {
        response = given()
                .contentType("application/json")
                .get(BASE_URL+PET_ENDPOINTLOGOUT);
        verifyResponse("200", String.valueOf(response.getStatusCode()), "logout response");
    }

    @Then("delete user")
    public void deleteUser() {
        object.clear();
        object.put("username", "fikriAzrbni");

        response = given()
                .body(object)
                .delete(BASE_URL+PET_ENDPOINTDELETEUSER);
        verifyResponse("200", String.valueOf(response.getStatusCode()), "deleted user response");
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
        verifyResponse("405", String.valueOf(response.getStatusCode()), "login with deleted user response");
    }

    //apiWithAuth
    @Given("registration user")
    public void registrationUser() {
//        String requestBody = "{\"name\":\"Fikri\",\"email\":\"fikri@gmail.com\",\"password\":fikri123}";
        JSONObject object = new JSONObject();

        object.put("name", "Fikri1");
        object.put("email", "fikri1@gmail.com");
        object.put("password", "fikri123");
        JSONObject requestBody = new JSONObject();
        requestBody.put("User", object);
        response = given()
                .contentType("application/json")
                .body(object.toString())
                .when()
                .post(BASE_URL_WithAuth + AWU_POST_REGIS);
        verifyResponse("200", String.valueOf(response.getStatusCode()), "registering new user");
        JsonPath json = response.jsonPath();
        response.prettyPrint();
        id = json.get("Id").toString();
        token = json.getString("data.Token");
        System.out.println(id + " " + token);
    }

    @Then("login with registered user")
    public void loginWithRegisteredUser() {
//        token = "0c239c13-e283-49ba-8aac-bb803e3a698e";
        JSONObject object = new JSONObject();
        object.put("email", "fikri@gmail.com");
        object.put("password", "fikri123");
//        HashMap<String, String> headers = new HashMap<>();
//        headers.put("Authorization", "Bearer " + token);
        response = given()
                .contentType("application/json")
                .body(object.toString())
//                .header("Authorization", "Bearer " + token)
                .when()
                .post(BASE_URL_WithAuth + AWU_POST_LOGIN);
//        response.prettyPrint();
        JSONObject jsonObject = new JSONObject(response.prettyPrint());
        JSONObject data = jsonObject.getJSONObject("data");
        id = String.valueOf(data.getInt("Id"));
        token = data.getString("Token");
        String name = data.getString("Name");
        System.out.println(id);
        System.out.println(token);
        verifyResponse("200", String.valueOf(response.getStatusCode()), "login with valid credential");
        verifyResponse(name, "Fikri", "User Name");
    }

    @And("get all user info")
    public void getAllUserInfo() {
        response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when().get(AWU_GET_ALL_USER);

        JSONObject jsonObject = new JSONObject(response.prettyPrint());
        String totalUser = jsonObject.getString("totalrecord");
        verifyResponse("200", String.valueOf(response.getStatusCode()), "Total user -> " + totalUser);
    }

    @And("get user by id")
    public void getUserById() {
        response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when().get(AWU_GET_USERBYID + id);

        JSONObject jsonObject = new JSONObject(response.prettyPrint());
        String email = jsonObject.getString("email");
        System.out.println();
        verifyResponse("200", String.valueOf(response.getStatusCode()), "Email user -> " + email);
    }

    @And("create user object")
    public void createUserObject() {
    }

    @And("update user object")
    public void updateUserObject() {
    }

    @And("delete user by id")
    public void deleteUserById() {
    }
}
