package com.bestbuy.bestbuyinfo;


import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;




@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends TestBase {
    static int id;
    String name = "Washing Machine 3000";
    String type = "Washing Machine";
    int price = 200;
    int shipping = 20;
    String upc = "ABC";
    String description = "Washing Machine For Clothes";
    String manufacturer = "Bosch";
    String model = "Washer";
    String url = "www.bosch.com";
    String image = "boschmachine.jpg";

    @Steps
    BestBuySteps bestBuySteps;
    @Title("Creating product")
    @Test
    public void test001() {
        ValidatableResponse response = bestBuySteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
    }

    @Title("Get single product")
    @Test
    public void test002() {
        ValidatableResponse response = bestBuySteps.getProduct(id);
        response.log().all().statusCode(200);
    }
    @Title("update product")
    @Test
    public void test003() {
        name = "washing machine_updated";
        ValidatableResponse response = bestBuySteps.updateProduct(id,name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(200);
    }
    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test004() {
        bestBuySteps.deleteProduct(id).statusCode(200);
        bestBuySteps.getProduct(id).statusCode(404);
    }
}






