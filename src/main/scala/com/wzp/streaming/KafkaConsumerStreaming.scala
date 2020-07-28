package com.wzp.streaming

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

/**
  * @创建人 wangzhipeng
  * @创建时间 2019/8/5
  * @描述
  */
object KafkaConsumerStreaming {

  val conf = new SparkConf().setMaster("local[2]").setAppName("KafkaConsumerStreaming")


  val kafkaParams = Map[String,Object](
    "bootstrap.servers" -> "localhost:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "my_first_test",
    "auto.offset.reset" -> "latest",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )

  val topics = Array("test")

  val checkPointDir = "/Users/wangzhipeng/opt/checkpointDir/test"

  def functionToCreateContext() : StreamingContext = {
    val ssc = new StreamingContext(conf,Seconds(10))
    ssc.checkpoint(checkPointDir)
    ssc
  }

  def addValue(values : Seq[Int],state:Option[Int]) = {
    var newValue = state.getOrElse(0)
    for (value <- values){
      newValue += value
    }
    Option(newValue)
  }





  def main(args: Array[String]): Unit = {
//    stream.map(record => (record.key(),record.value()))

    val addFunc = (currValues: Seq[Int], prevValueState: Option[Int]) => {
      //通过Spark内部的reduceByKey按key规约，然后这里传入某key当前批次的Seq/List,再计算当前批次的总和
      val currentCount = currValues.sum
      // 已累加的值
      val previousCount = prevValueState.getOrElse(0)
      // 返回累加后的结果，是一个Option[Int]类型
      Some(currentCount + previousCount)
    }

    val context = StreamingContext.getOrCreate(checkPointDir,functionToCreateContext _)
    val stream = KafkaUtils.createDirectStream[String,String](
      context,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    val lines = stream.map(_.value())
    val words = lines.flatMap(_.split(","))
    val pairs = words.map((_,1))
    val wordCounts = pairs.reduceByKey(_ + _)
    val stateCounts = wordCounts.updateStateByKey(
      (currValues:Seq[Int],preValue:Option[Int]) =>{
        val currValue = currValues.sum
        Option(currValue + preValue.getOrElse(0))
    })
//    stateCounts.checkpoint(Duration(10))
    stateCounts.print()

    context.start()
    context.awaitTermination()
    context.stop()
  }


}
