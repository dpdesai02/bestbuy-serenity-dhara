package com.bestbuy.bestbuyinfo;


import com.bestbuy.model.StoresPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;



@RunWith(SerenityRunner.class)
public class StoresCRUDTest extends TestBase {
    String name = "Store";
    String type = "Convenience";
    String address = "10 XYZ";
    String address2 = "Wellington";
    String city = "London";
    String state = "England";
    String zip = "AB12CD";
    int lat = 123;
    int lng = 456;
    String hours = "10";
    StoresPojo.Services services;
    static int id;

    @Steps
    StoresSteps storesSteps;
    @Title("Creating stores")
    @Test
    public void test001() {
        ValidatableResponse response = storesSteps.createStores(name, type,address,address2,city,state,zip,lat,lng,hours,services);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
        HashMap<String,Object> value = response.extract().path("");
        Assert.assertThat(value,hasValue(id));

    }
    @Title("Get store")
    @Test
    public void test002() {
        ValidatableResponse response = storesSteps.getStores(id);
        response.log().all().statusCode(200);
        HashMap<String,Object>value = response.extract().path("");
        Assert.assertThat(value,hasValue(id));
    }
    @Title("Update store")
    @Test
    public void test003() {
        name = name+"_updated";
        storesSteps.updateStores(id,name, type,address,address2,city,state,zip,lat,lng,hours,services);
        ValidatableResponse response = storesSteps.getStores(id);
        response.log().all().statusCode(200);
        HashMap<String,Object> value = response.extract().path("");
        Assert.assertThat(value,hasValue(name));
    }
    @Title("Delete the store and verify if the store is deleted!")
    @Test
    public void test004() {
        storesSteps.deleteStores(id).statusCode(200);
        storesSteps.getStores(id).statusCode(404);
    }


}
