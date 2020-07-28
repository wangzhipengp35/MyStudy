package com.wzp

import org.apache.spark.sql.SparkSession

import scala.collection.mutable.ListBuffer

/**
  * @创建人 wangzhipeng
  * @创建时间 2019/8/5
  * @描述
  */
object test {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[1]").appName("test").getOrCreate()
    val sourceDF = spark.read.json("json/test.json")
    val resultDF = sourceDF.na.fill("\\N")
    resultDF.show()

    resultDF.foreach(row => {

      val rowKey : String = row.getAs("row_key").toString
      val family = "f1"

      println(rowKey)
      row.schema.fields.foreach(field => {
        if(!"row_key".equals(field.name)) {
          println("field_name : " + field.name)
          println("field_value : " + row.getAs(field.name))
        }
      })
    })
  }
}
