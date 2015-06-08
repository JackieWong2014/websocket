/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.web.websocketbot.decoders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javaeetutorial.web.websocketbot.messages.NBA;
/**
 *
 * @author TEST
 */
public class NBADecoder {
    public List<NBA> decode (String string){
        List<NBA> list = new ArrayList<NBA>();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement jsonEl = parser.parse(string);
        JsonObject jsonObject = null;
        jsonObject = jsonEl.getAsJsonObject();
        
        String reason = jsonObject.get("reason").getAsString();
        if(reason.equals("successed!")){
           JsonElement resultElement = jsonObject.get("result");
           if(resultElement.isJsonArray()){
              JsonArray jsonArray = resultElement.getAsjsonArray();
              NBA nba;
              for(Iterator  iter = jsonArray.iterator();iter.hasNext();){
                     JsonObject obj = (JsonObject) iter.next();
                      nba = new NBA();
                      nba = gson.formJson (obj,NBA.class);
                      list.add(nba);
              }
           }
           return list;
        }else{
    
    return null;
    }
        
        
        

    }
    
}
