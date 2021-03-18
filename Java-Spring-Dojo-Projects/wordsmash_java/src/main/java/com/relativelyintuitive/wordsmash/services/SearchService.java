package com.relativelyintuitive.wordsmash.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.relativelyintuitive.wordsmash.repositories.CommentRepository;
import com.relativelyintuitive.wordsmash.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SearchService {
	private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    
    public SearchService(UserRepository userRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public ArrayList<HashMap<String, Object>> getMwDictResults(String query) {
        // create ArrayList to hold a map for each entry found for the query, to be returned
        ArrayList<HashMap<String, Object>> results = new  ArrayList<HashMap<String, Object>>();
    	// try the request to the API, via Unirest
    	try {
			HttpResponse<JsonNode> response = Unirest.get("https://www.dictionaryapi.com/api/v3/references/collegiate/json/"
													+ query
													+ "?key=31ea89b9-151a-4d82-8d3a-b1df86b69415")
													.asJson();
			// print the status, content type, and response if request successful
			System.out.println(response.getStatus());
	        System.out.println(response.getHeaders().get("Content-Type"));
	        // pretty-print the entire response for the searched query 
			Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
	          JsonParser parser = new JsonParser();
	          JsonElement prettyElement = parser.parse(response.getBody().toString());
	          String prettyJsonString = gsonPretty.toJson(prettyElement);
	          System.out.println(prettyJsonString);
	        // convert the entire response to a string and parse it into a JsonElement
	        String jsonResponse = response.getBody().toString();
	          JsonElement jsonTree = parser.parse(jsonResponse);
	        // verify that the JsonElement is an Array as expected
	        if(jsonTree.isJsonArray()) {
	        	// convert JsonElement explicitly to JsonArray
	            JsonArray jsonArray= jsonTree.getAsJsonArray();
	            // iterate through each entry found
	            for (int i = 0; i < jsonArray.size(); i++) {
	            	// create a complete map for each entry found for the query, to be pulled from
		            JsonElement entryElement = jsonArray.get(i);
		              JsonObject entryObject = entryElement.getAsJsonObject();
		              HashMap<String, Object> objHashMap = new Gson().fromJson(entryObject.toString(), HashMap.class);
		              System.out.println(objHashMap);
		            // create a HashMap for each entry holding the needed fields and add it to entryMaps
		            HashMap<String, Object> entryMap = new HashMap<String, Object>();
		            // pull the fields needed from each entry to fill each resultsHashMap
		            // *meta*
		            ObjectMapper objectMapper = new ObjectMapper();
		              Object metaObj = objHashMap.get("meta");
		              HashMap<String, Object> metaMap = objectMapper.convertValue(metaObj, HashMap.class);
		              String offensive = metaMap.get("offensive").toString();
		              if (offensive.equals("true")) {
		            	  entryMap.put("isOffensive", "is considered offensive!");
		              } else {
		            	  entryMap.put("isOffensive", "is not considered offensive.");
		              }
		            // *shortdefs*
		            ArrayList<String> shortDefsNew = new ArrayList<String>();
            		  ArrayList<String> shortDefsRaw = (ArrayList<String>) objHashMap.get("shortdef");
            	  	  for (String def : shortDefsRaw) {
            		  	  // get shortdefs, removing brackets for each and capitalizing before adding to their new array
            			  String defEdit = def.substring(0, 1).toUpperCase() + def.substring(1) + ".";
            			  shortDefsNew.add(defEdit);
            		  }
            	  	  entryMap.put("shortDefs", shortDefsNew);
        	  	    // add the completed map for the entry to the results ArrayList
            	  	results.add(entryMap);
	            }
	            // return the results ArrayList
	            return results;
	        // if the original JsonElement holding the entire response is not a JsonArray, return errors
	        } else {
	        	HashMap<String, Object> errors = new HashMap<String, Object>();
	        	  errors.put("error", "MW results not JsonArray...");
	        	  results.add(errors);
	        	return results;
	        }
	    // return errors if the original request fails
		} catch (UnirestException e) {
			e.printStackTrace();
        	  HashMap<String, Object> errors = new HashMap<String, Object>();
		 	  errors.put("error", e.toString());
			  results.add(errors);
		    return results;
		}
    }
}