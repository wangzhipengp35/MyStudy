package com.wzp.sql

import java.io.{File, PrintWriter}


import org.apache.spark.sql.SparkSession

/**
  * @创建人 wangzhipeng
  * @创建时间 2019/8/13
  * @描述
  */
object CreateTable {

  def deleteLastCharInstr (s: String) = {
    s.dropRight(2)
  }

  def writeToFile(text : String,path : String) = {
    val writer = new PrintWriter(new File(path))
    writer.write(text)
    writer.close()
  }

  def main(args: Array[String]): Unit = {

//    val dataMap = Map("double" -> "DECIMAL(10,2)","long" -> "INT","string" -> "STRING")

    val spark = SparkSession
      .builder()
      .appName("CreateTable")
      .master("local[1]")
      .getOrCreate()
//      .enableHiveSupport()



    val test = spark.read.parquet("/Users/wangzhipeng/Desktop/test/000000_0")
    test.show()

    test.write.option("header", "true").csv("/Users/wangzhipeng/Desktop/test_result/")

//    val path = "json/test.json"
//    spark.read.json(path).createOrReplaceTempView("test")
//    import spark.implicits._
//
//
//    val sourceDF = spark.sql("select * from test")
//    println(sourceDF)
//    val selectDf = sourceDF.where("id = 1")
//    println(selectDf)
//
//    val resultDF = selectDf.na.fill("\\N")
//    val ss = Array(null,"")
//    println(resultDF)


//    val readConfig = "/opt/avenger/createDDLJob/TableConfig"
//
//    import scala.io.Source
//    val source = Source.fromFile(readConfig, "UTF-8")
//    val lines = source.getLines().toArray
//    for (line <- lines) {
//      val sourceDatabase = line.split(",")(0)
//      val sourceTableName = line.split(",")(1)
//      val sinkDatabase = line.split(",")(2)
//
//      val sinkTableName = sourceTableName
//      var createTableSql = "create table if not exists " + sinkDatabase + "." + sinkTableName + " (\n"
//
////      spark.read.json(path).createOrReplaceTempView("test")
////      val schema = spark.sql("select * from " + "test" + " limit 1" ).schema
//
//      val schema = spark.sql("select * from " + sourceDatabase + "." + sourceTableName + " limit 1").drop("o_date").schema
//
//      schema.fields.foreach(row => {
//        createTableSql += (row.name + " " + dataMap.apply(row.dataType.typeName) + ",\n")
//      })
//      createTableSql = deleteLastCharInstr(createTableSql)
//
//      createTableSql += "\n) partitioned by (o_date string) \nROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde' \nSTORED AS TEXTFILE;"
//      //执行创建表语句
//      //    spark.sql(createTableSql)
//      //执行插入数据的语句
//      val insertSql = "insert overwrite table " + sinkDatabase + "." + sinkTableName + " partition (o_date = '${hivevar:o_date}')" + "\nselect * from " + sourceDatabase + "." + sourceTableName + " where substr(etl_date,1,8) = '${hivevar:o_date}'"
//      //    spark.sql(insertSql)
//
//      val hiveShell = "current_date=$1\nif [ ! -n \"$current_date\" ];then \ncurrent_date=`date +%Y%m%d` \nfi \necho ${current_date}\nhive --hivevar o_date=\"$current_date\" -f /opt/avenger/job/SQL/redshift/" +sinkTableName + ".sql"
//
//      //脚本写入文件
//      writeToFile(insertSql,"/opt/avenger/job/SQL/redshift/"+sinkTableName+".sql")
//      writeToFile(createTableSql,"/opt/avenger/job/DDL/redshift/"+sinkTableName+".sql")
//      writeToFile(hiveShell,"/opt/avenger/job/SCRIPT/redshift/"+sinkTableName+".sh")

    }

//  }


}
