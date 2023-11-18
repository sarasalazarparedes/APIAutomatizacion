package runner;

import config.Configuration;
import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import io.restassured.response.Response;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.Matchers.equalTo;

public class CrudUser {
    Response response;
    RequestInformation requestInfo=new RequestInformation();
    Map<String,String> variables = new HashMap<>();
    Configuration configuration= new Configuration();

    @Given("using token in todo.ly")
    public void usingTokenInTodoLy() {
        // request
        String credentials = Base64.getEncoder()
                .encodeToString((configuration.getUser()+":"+Configuration.pwd).getBytes());

        requestInfo.setUrl(Configuration.host+"/api/authentication/token.json")
                .setHeaders("Authorization","Basic "+credentials);
        response = FactoryRequest.make("get").send(requestInfo);
        // get token
        String token = response.then().extract().path("TokenString");
        requestInfo = new RequestInformation();
        requestInfo.setHeaders("Token",token);
    }
     @Given("I don't need authentication")
     public void iDontNeedA(){
        requestInfo= new RequestInformation();
     }
    @When("I send {} request {string} with body")
    public void sendPOSTRequestWithBody(String method,String url,String body) {
        requestInfo.setUrl(Configuration.host+this.replaceValues(url)).setBody(body);
        response = FactoryRequest.make(method).send(requestInfo);
    }
    @Then("the response code should be {int}")
    public void responseCodeShouldBe(int expectedCode) {
        response.then().statusCode(expectedCode);
    }
    @And("the attribute {string} should be {string}")
    public void theAttributeShouldBe(String attribute, String expectedValue) {
        response.then().body(attribute,equalTo(expectedValue));
    }
    @And("save {string} in the variable {string}")
    public void saveInTheVariable(String attribute, String nameVariable) {
        variables.put(nameVariable,response.then().extract().path(attribute)+"");
        if(attribute.equals("Email")){
            configuration.setUser(variables.get("Email"));
        }
    }
    private String replaceValues(String value){
        for (String key:variables.keySet() ) {
            value=value.replace(key,variables.get(key));
        }
        return value;
    }


}
