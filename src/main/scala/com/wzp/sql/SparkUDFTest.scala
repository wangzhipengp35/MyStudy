package com.wzp.sql

import org.apache.spark.sql.SparkSession

/**
  * @创建人 wangzhipeng
  * @创建时间 2019/8/22
  * @描述
  */
object SparkUDFTest {
  val spark = SparkSession
    .builder()
    .appName("SparkUDFTest")
    //      .master("local[1]")
    .enableHiveSupport()
    .getOrCreate()


}
