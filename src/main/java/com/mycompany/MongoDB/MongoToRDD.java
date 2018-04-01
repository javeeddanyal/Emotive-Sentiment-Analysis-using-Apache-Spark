///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.mycompany.MongoDB;

import static com.mycompany.Streaming.RealTimeTwitterStream.sparkContext;

import org.apache.spark.api.java.JavaRDD;

import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;


/**
 *
 * @author Danyal-PC
 */
public class MongoToRDD {
 
   // private static final SparkConf sparkConf=new SparkConf().setAppName("Emotive Sentiment Analysis Using spark").setMaster("local[4]").set("spark.serializer",KryoSerializer.class.getName()).set("es.nodes","127.0.0.1:9200").set("es.index.auto.create","true");
   // private static final JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
    public static void saveToES(String file , String index_name , String index_type)
    {
      JavaRDD<String> data = sparkContext.textFile(file);
      JavaEsSpark.saveJsonToEs(data,index_name+"/"+index_type);
    
    }
    
    
}
//