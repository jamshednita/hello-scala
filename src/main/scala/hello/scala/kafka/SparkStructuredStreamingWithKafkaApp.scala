package hello.scala.kafka

import org.apache.spark.sql.SparkSession

object SparkStructuredStreamingWithKafkaApp extends App {
  val inputKTopic = "test-in"
  val outputKTopic = "test-out"
  val checkpointLocation = "checkpoint-dir"

  val spark = SparkSession.builder()
    .appName("Structured-Streaming-With-Spark")
    .getOrCreate()

  import spark.implicits._

  // Subscribe to 1 topic
  val kafkaMessages = spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", inputKTopic)
    .load()

  // Write key-value data from a DataFrame to a specific Kafka topic specified in an option
  val ds = kafkaMessages
    .selectExpr("CAST(value AS STRING)").as[String].flatMap(_.split(" ")).groupBy("value").count()
    .writeStream
    .outputMode("complete")
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("topic", outputKTopic)
    .option("checkpointLocation", checkpointLocation)
    .start()

  ds.awaitTermination()
}
