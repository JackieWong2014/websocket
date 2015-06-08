/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.web.websocketbot.messages;

/**
 *
 * @author TEST
 */
public class NBA {
    private String playtime;
    private String playdate;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
    private String score;
    private String name1;
    private String name2;
   private String link;
    public NBA() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPlaytime() {
        return playtime;
    }

    public void setPlaytime(String playtime) {
        this.playtime = playtime;
    }

    public String getPlaydate() {
        return playdate;
    }

    public void setPlaydate(String playdate) {
        this.playdate = playdate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
    public NBA(String t,String d,String l,String n1,String n2,String s){
        
       super();
       this.playdate = d;
       this.playtime = t;
       this.link = l;
       this.name1 = n1;
       this.name2 = n2;
       this.score = s;
       
    }
     @Override
     public String toString(){
         return "NBA [赛事结果是"+ name1 +"VS" + name2+ ",日期："+ playdate + playtime + "比赛得分是"+score+"详情看链接：" + link +"]";
     }
    
    
    
    
    
}
