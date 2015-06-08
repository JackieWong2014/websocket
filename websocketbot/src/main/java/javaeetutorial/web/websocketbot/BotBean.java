/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.web.websocketbot;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javaeetutorial.web.websocketbot.decoders.NBADecoder;
import javaeetutorial.web.websocketbot.messages.NBA;

import javax.inject.Named;


import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
@Named
public class BotBean {
    
    /* Respond to a message from the chat */
    public String respond(String msg) {
        String response = null;     
        String nbaresults;
        List<NBA> list = null;
        
        NBADecoder nbadecoder;
        /* Remove question marks */
        msg = msg.toLowerCase().replaceAll("\\?", "");
        if (msg.contains("how are you")) {
            response = "I'm doing great, thank you!";
        } else if (msg.contains("how old are you")) {
            Calendar dukesBirthday = new GregorianCalendar(1995, Calendar.MAY, 23);
            Calendar now = GregorianCalendar.getInstance();
            int dukesAge = now.get(Calendar.YEAR) - dukesBirthday.get(Calendar.YEAR);
            response = String.format("I'm %d years old.", dukesAge);
        } else if (msg.contains("when is your birthday")) {
            response = "My birthday is on May 23rd. Thanks for asking!";
        } else if (msg.contains("队")| msg.contains("球")) {
            String  nbaUri ="http://op.juhe.cn/onebox/basketball/team?key=0564267c0eb0ed260583698c8ea0e605&dtype=json&team=";
            String divUri = "http://ltpapi.voicecloud.cn/analysis/?api_key=41g4M3f3b6C7L387G0c6XcEOcY5QwaMCWDRoYhGa&pattern=ws&&pattern=dp&format=plain&text=";
            String divresults = get(msg,divUri);
            
            String[] phrase = divresults.split("\\s");
            for(int j = 0; j<phrase.length;j++){
               nbaresults = get(phrase[j],nbaUri);
               
               nbadecoder = new NBADecoder();
               list = nbadecoder.decode(nbaresults);
               
               if(list != null){
                   response += "球队："+phrase[j];
                   for(NBA nba :list){
                       response += "\n日期"+nba.getPlaydate()+nba.getPlaytime()+",球队："+nba.getName1()+"VS"+"球队："+nba.getName2()+"最终比分："+nba.getScore()+"详情链接："+nba.getLink();
                       response +="\n";
                   }
               }
               else{
                   response = "查找的球队赛程不存在！";
               }
            }
            
            
            
        } else {
            response = "Sorry, I did not understand what you said. ";
            response += "You can ask me how I'm doing today; how old I am; or ";
            response += "what my favorite color is.";
        }
        try {
            Thread.sleep(1200);
        } catch (InterruptedException ex) { }
        return response;
    }
    
    public String get(String msg,String uri) {
		msg = msg.toLowerCase().replaceAll("\\?", "");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(uri+ msg);
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				System.out.println(response.getStatusLine());
				if (entity != null) {
					String jsonresult = EntityUtils.toString(entity);
					System.out.println("Response content:" + jsonresult);
					return jsonresult;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				httpclient.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return null;
	}
}
