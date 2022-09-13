/*
 * TestController.java
 *
 */
package com.joev.example.angularboot;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
// import com.google.code.gson.*; 
import com.google.gson.*;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;



@RestController()
@RequestMapping(path = "/api")
public class TestController {

//**************************************************************************************************
//  Fields
//**************************************************************************************************
//**************************************************************************************************
//  Constructors
//**************************************************************************************************
//**************************************************************************************************
//  Abstract Methods
//**************************************************************************************************
//**************************************************************************************************
//  Overridden Methods
//**************************************************************************************************
//**************************************************************************************************
//  Other Methods
//**************************************************************************************************
   String response="";

    private String sendGet() throws Exception {

//        String url = "https://www.google.com/search?q=mkyong";
    String url="https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random";
    
        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        // optional default is GET
        httpClient.setRequestMethod("GET");

        //add request header
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");

        httpClient.addRequestProperty("x-rapidapi-host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com");
        httpClient.addRequestProperty("x-rapidapi-key", "b1ca2090c8mshbe1c0739a807645p1f2242jsn5f4cb0ed4eb5");
        httpClient.addRequestProperty("accept", "application/json");

        int responseCode = httpClient.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
           
        String r2="";
        String CHNK="";
        
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        //     JSONObject obj = new JSONObject(json);
        // String val = obj.getJSONObject(response).getString("value");

            //print result
            // Gson gson = new Gson();
            // String val=gson.toJson(response.toString());
            // String val2=gson.fromJson(val, String.class);
            // System.out.println(val);
            // System.out.println(val2);


    		JSONParser jsonParser = new JSONParser();
			Object object = jsonParser.parse(response.toString());

			JSONObject jsonObject = (JSONObject) object;

			// System.out.println(jsonObject);

			CHNK = (String) jsonObject.get("value");
            
            System.out.println("Original: "+CHNK);

            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response3 = Unirest.post("https://neutrinoapi-bad-word-filter.p.rapidapi.com/bad-word-filter")
	.header("x-rapidapi-host", "neutrinoapi-bad-word-filter.p.rapidapi.com")
	.header("x-rapidapi-key", "b1ca2090c8mshbe1c0739a807645p1f2242jsn5f4cb0ed4eb5")
	.header("content-type", "application/x-www-form-urlencoded")
	.body("censor-character=*&content="+CHNK)
    .asString();
    
              System.out.println("Response Body: "+response3.getBody().toString());

              JSONParser jsonParser2 = new JSONParser();
              Object object2 = jsonParser2.parse(response3.getBody().toString());
  
              JSONObject jsonObject2 = (JSONObject) object2;
              
              String chuckCensored=(String) jsonObject2.get("censored-content");

              System.out.println("Censored Fact: "+chuckCensored);

            r2=chuckCensored;
            // System.out.println("value: " + value);
            
            // List<String> fromJson = gson.fromJson(val, String);
            // for (String movie : fromJson) {
            // System.out.println(movie);
            // }


//            System.out.println(response.toString());
            // r2=response.toString();
            // r2=val.toString();

        }



            //put through profanity filter
        //     String url2="https://neutrinoapi-bad-word-filter.p.rapidapi.com/bad-word-filter";
        //     //create another instance
        //     HttpURLConnection httpClient2 =
        //     (HttpURLConnection) new URL(url2).openConnection();
        //     // optional default is GET
        //      httpClient2.setRequestMethod("POST");
        //     //add request header
        //     // httpClient2.setRequestProperty("User-Agent", "Mozilla/5.0");
        //     httpClient2.addRequestProperty("x-rapidapi-host", "neutrinoapi-bad-word-filter.p.rapidapi.com");
        //     httpClient2.addRequestProperty("x-rapidapi-key", "b1ca2090c8mshbe1c0739a807645p1f2242jsn5f4cb0ed4eb5");
        //     // httpClient2.addRequestProperty("accept", "application/json");
        //     // httpClient2.addRequestProperty("content-type", "application/x-www-form-urlencoded");
        //     httpClient2.addRequestProperty("content", CHNK);
            
        //     int responseCode2 = httpClient2.getResponseCode();
        //     System.out.println("\nSending 'POST' request to URL : " + url2);
        //     System.out.println("Response Code : " + responseCode2);
            
        //     //putting outside so that it has same scope as initial response

        //     try (BufferedReader in2 = new BufferedReader(
        //         new InputStreamReader(httpClient2.getInputStream()))) {

        //             StringBuilder response2 = new StringBuilder();
        //             String line2;
        
        //     while ((line2 = in2.readLine()) != null) {
        //         response2.append(line2);
        //     }
        //     System.out.println(response2.toString());
        // }
        

        return r2;
    }

    

    @GetMapping(path = "/hello-world")
    public String helloWorld() throws Exception {
       String sG = this.sendGet();
        return sG;
    }
//**************************************************************************************************
//  Getter/Setter Methods
//**************************************************************************************************

//**************************************************************************************************
//  Static Methods
//**************************************************************************************************
//**************************************************************************************************
//  Inner Classes
//**************************************************************************************************
}
