/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import static com.jayway.restassured.RestAssured.*;

import com.jayway.restassured.parsing.Parser;
import javax.ws.rs.core.MediaType;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 * @author RolfMoikjær
 */
public class QuoteTester {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseURI = "http://localhost:8084";
        defaultParser = Parser.JSON;
        basePath = "/RestTest/api/quote";
    }

    public QuoteTester() {
    }

    @Test
    public void testInsertCategory() {
        when().get("/1").then()
                .contentType(MediaType.APPLICATION_JSON)
                .body("quote", equalTo("Friends are kisses blown to us by angels"));
    }
}
