/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Streaming;

import com.google.common.base.Objects;
import static com.mycompany.MongoDB.StoreDocument.connectToMongo;

import static com.mycompany.MongoDB.StoreDocument.storeTweet;

import java.io.Serializable;
import java.util.Date;

import twitter4j.Status;


/**
 *
 * @author Danyal-PC
 */
public class Tweet implements Serializable {
        private long id;
        private String text;
        private String location;
        private String name;
        private Date date;
        private String source;
        private String lang;
        private String countryCode;
        public Tweet(Status status)
        {
            try{
                 if(status.getPlace().getCountry()!=null&&status.getPlace().getCountryCode()!=null)
                 
                      Tweet(status.getId(),status.getUser().getScreenName(),status.getText(),status.getCreatedAt(),status.getPlace().getCountry(),status.getPlace().getCountryCode(),status.getSource(),status.getLang());
                 
            //     else
            //          Tweet(status.getId(),status.getUser().getScreenName(),status.getText(),status.getCreatedAt(),"Not Available","Not Available",status.getSource(),status.getLang());  
                }catch(NullPointerException e){}
        }
        
        
        public void Tweet(long id ,String name, String text,Date date, String loc , String countryCode , String source , String lang) throws NullPointerException{
            this.source = source;
            this.id=id;
            this.name=name;
            this.text=text;
            this.date=date;
            this.location=loc;
            this.countryCode=countryCode;
            this.lang=lang;
            MongoDB();
        }

        private void MongoDB()
        {   
            
         connectToMongo("127.0.0.1",27017,"TweeterData");
         storeTweet(Long.toString(id), text ,location,countryCode,name,date,source,lang);
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
                
        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setDate(Date date) {
            this.date = date;
        }
        
        public Date getDate() {
            return date ;
        }
        
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .add("id", id)
                    .add("text", text)
                    .add("location", location)
                    .add("date",date)
                    .add("name", name)
                    .toString();
        }
    }

