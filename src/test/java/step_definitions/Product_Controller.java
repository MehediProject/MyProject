package step_definitions;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import org.json.JSONObject;
import base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
public class Product_Controller extends Base{

	int catagory_Id;
	int random_Id;
	String categoryName;
	String Name;
	String Sku;
	double unitPrice;
	int unitsInstock;
	String UpdateName;
	String description;
	double updateunitPrice;
	int updateUnitsInstock;
	
	@Given("I generate  test random value")
	public void i_generate_test_random_value() {
		
		 random_Id = generateRandomNumber(10000);
	}
	@When("I add  Product Controller {int} and {string} and {string} and {double} and {int}")
	public void i_add_Product_Controller_and_and_and_and(int id, String name, String sku, double unit_Price, int units_Instock) {	
   		catagory_Id = id;		
		Name=name;
		Sku =sku;
		unitPrice=unit_Price;
		unitsInstock = units_Instock;
		
	System.out.println("unitPrice :"+unitPrice);
	
		JSONObject requestBody= new JSONObject();
		requestBody.put("active", true);		
	    requestBody.put("category", new JSONObject()	    		                             
	    		                              .put("id",catagory_Id));
	    requestBody.put("id", random_Id);      
	    requestBody.put("name", Name);
	    requestBody.put("sku", Sku);
	    requestBody.put("unitPrice", unitPrice);
	    requestBody.put("unitsInStock", unitsInstock);	   
	    requestBody.put("description", description);
	    requestBody.put("lastUpdated", "2022-03-22T03:22:11.891Z");
	    requestBody.put("imageUrl", "C:\\Users\\tkhan\\Pictures\\card 2.jpg");
	    requestBody.put("dateCreated", "2023-03-22T03:22:11.891Z");
	    
	   
	    Response responses = given()
	    		           .contentType("application/json")
	    		           .body(requestBody.toString())
	                       .when()
	                       .post("http://skyschooling.com:8081/api/v01/product");
	    assertEquals(201, responses.statusCode());
	    System.out.println("Status code: "+responses.statusCode());
	    
	    JSONObject resBody= new JSONObject(responses.body().asString());
	                   
	    random_Id=resBody.getInt("id");	                   
	                
	    JSONObject category = resBody.getJSONObject("category");
	    
	    categoryName = category.getString("categoryName");
	    	    
	    printAll(responses);      
	}

	@Then("I Verify with categoryName,Id and unitPrice of product")
	public void i_Verify_with_categoryName_Id_and_unitPrice_of_product() {
		
	//	System.out.println(random_Id);	
		//System.out.println("categoryName : "+categoryName);
	   Response response =	given()
		              .when()
		              .get("http://skyschooling.com:8081/api/v01/product/"+random_Id);
	        	   
	       assertEquals(200, response.statusCode());
	   
	   
          JSONObject resBody = new JSONObject(response.body().asString());                        
          JSONObject category = resBody.getJSONObject("category");         
          System.out.println(category);
         
          assertEquals( random_Id ,resBody.getInt("id"));
          assertEquals(categoryName, category.getString("categoryName"));
          
          
          String number = String.format("%.2f", unitPrice); 
          Object unitprice = resBody.get("unitPrice");        
          String number1 = String.format("%.2f", unitprice);         
          assertEquals(number, number1);
          assertEquals(String.format("%.2f", unitPrice), String.format("%.2f", unitprice));
          
         
          
          
          
          System.out.println("unitPrice: "+unitPrice);
          System.out.println("categoryName "+category.getString("categoryName"));          
          printAll(response);

        
		
	}

	@Then("I Update product with {string} and {string} and {int} and {double}")
	public void i_Update_product_with_and_and_and(String UpdateName, String updateDescription, int updateUnitsInstock, double updateUnitPrice) {
	   
		Name = UpdateName;
		description =updateDescription;
		unitsInstock = updateUnitsInstock ;
		unitPrice = updateUnitPrice;
		
		JSONObject requestBody = new JSONObject();		
		requestBody.put("active", true);
		requestBody.put("category", new JSONObject()
												.put("id", catagory_Id));		
		requestBody.put("dateCreated", "2022-03-17T02:14:48.709Z");
		requestBody.put("description", description);
		requestBody.put("id", 32);
		requestBody.put("imageUrl", "C:\\Users\\Microtecj NA\\Pictures\\istockphoto-1269647099-612x612.jpg");
		requestBody.put("lastUpdated", "2023-03-17T02:14:48.709Z");
		requestBody.put("name", Name);
		requestBody.put("sku", "DF-009");
		requestBody.put("unitPrice", unitPrice);
		requestBody.put("unitsInStock", unitsInstock);
					
		Response response =given()
		 .contentType("application/json")
		 .body(requestBody.toString())
		 .when()
		 .put("http://skyschooling.com:8081/api/v01/product/"+random_Id);
	     
		 assertEquals(200, response.statusCode());
		
	}

	@Then("I verify get  after update product")
	public void i_verify_get_after_update_product() {
		Response response =	given()
		 .when()
		 .get("http://skyschooling.com:8081/api/v01/product/"+random_Id);
     
		assertEquals(200, response.statusCode());
		
		
		JSONObject resBody = new JSONObject(response.body().asString());       
      
		assertEquals( random_Id ,resBody.getInt("id"));  
        assertEquals(Name, resBody.getString("name"));
        assertEquals(description, resBody.getString("description"));
        assertEquals(unitsInstock, resBody.getInt("unitsInStock"));
        
//        String number = String.format("%.2f", unitPrice);           
//       double unitPrice1  =   Double.parseDouble(number);           
//        assertEquals(unitPrice1, resBody.get("unitPrice"));   
//	

        String number = String.format("%.2f", unitPrice); 
        Object unitprice = resBody.get("unitPrice");        
        String number1 = String.format("%.2f", unitprice);         
        assertEquals(number, number1);
        assertEquals(String.format("%.2f", unitPrice), String.format("%.2f", unitprice));
 		
        
        
		response.then().log().all();
		
	}

	@Then("I delete product")
	public void i_delete_product() {
		given()
		 .when()
		 .delete("http://skyschooling.com:8081/api/v01/product/"+random_Id)
		 .then()
		 .statusCode(204)
		 .log().all();
	}

	@Then("I get after delete product and verify statusCode {int}")
	public void i_get_after_delete_product_and_verify_statusCode(Integer int1) {
		given()
		 .when()
		 .get("http://skyschooling.com:8081/api/v01/product/"+random_Id)
		 .then()
		 .statusCode(int1)
		 .log().all();
	}


	
}
