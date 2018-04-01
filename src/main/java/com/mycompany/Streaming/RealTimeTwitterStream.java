/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.spark.SparkException;
import java.io.IOException;

import static com.mycompany.MongoDB.StoreDocument.disconnectToMongo;

import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Danyal-PC
 */
public class RealTimeTwitterStream {
    
    public static SparkConf sparkConf=new SparkConf().setAppName("Emotive Sentiment Analysis Using spark").setMaster("local[4]");
    //public static SparkContext context = new SparkContext(sparkConf);
    public static JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
    
    public static ConfigurationBuilder Authentication()
    {
       ConfigurationBuilder builder=new ConfigurationBuilder();
         builder.setDebugEnabled(true)
        	.setOAuthConsumerKey("sOfRbrrgtZzGLGnTMTdMyaaOZ")
        	.setOAuthConsumerSecret("9mQpCp2vFOqUlXCIzXZEpygeG8329OymVZbCSrJVp46Z6GauVg")
        	.setOAuthAccessToken("4363969401-QIus1noWJvf6VnDxjw2Lp467cT0uQKcHxZe4s76")
        	.setOAuthAccessTokenSecret("6Zb2RUnyps0FdWlOXtnGBYNE1GnJsM1x7YQmbKVDM42w9");   
                 return builder;
    } 
  
    public static JavaStreamingContext run(int i) throws IOException,SparkException , NullPointerException
    {

    ObjectMapper mapper=new ObjectMapper();
    System.out.println("Spark is initializing...");
    

    JavaStreamingContext sc=new JavaStreamingContext(sparkContext,new Duration(5000));
    
    System.out.println("Initializing Twitter stream...");
        try{
                       TwitterUtils.createStream(sc,new OAuthAuthorization(Authentication().build()))
                       .map(status -> new Tweet(status) )
                       .map(t -> mapper.writeValueAsString(t))
                       .foreachRDD(tweets -> {tweets
                       .collect().stream();

                        return null;
                                              }
                                   );
            }catch(NullPointerException e) {} 
    sc.start();
    sc.awaitTermination(i);
    disconnectToMongo();

    return sc;
    }

}   


