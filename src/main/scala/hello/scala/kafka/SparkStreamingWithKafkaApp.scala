package hello.scala.kafka

import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, ConsumerStrategy, KafkaUtils, LocationStrategies}

/**
  * Unstructured stream processing with kafka spark
  */
object SparkStreamingWithKafkaApp extends App {
  val inputTopics = "test-in"
  val outputTopics = "test-out"

  // Step - 1: Define spark streaming context
  val ssc = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Spark-Streaming-Kafka")
    val streamingContext = new StreamingContext(conf, Seconds(1))
    //streamingContext.checkpoint("C:/Users/jamansar/IdeaProjects/hello-scala/checkpoint-dir")

    streamingContext
  }

  // Step - 2: Configure kafka producer (KafkaSink) and broadcast it to all executors
  val kafkaProducerConfig = {
    val p = new Properties()
    p.setProperty("bootstrap.servers", "localhost:9092")
    p.setProperty("key.serializer", classOf[StringSerializer].getName)
    p.setProperty("value.serializer", classOf[StringSerializer].getName)
    p
  }
  import scala.collection.JavaConverters._
  val kafkaSink = ssc.sparkContext.broadcast[KafkaSink[String, String]](KafkaSink(mapAsJavaMap(kafkaProducerConfig.asScala)))

  //val kafkaProducerConfig = Map("bootstrap.servers" -> "localhost:9092","key.serializer" -> classOf[StringSerializer].getName, "value.serializer" -> classOf[StringSerializer].getName)
  //val kafkaSink = ssc.sparkContext.broadcast[KafkaSink[String, String]](KafkaSink(scala.collection.JavaConverters.mapAsJavaMap(kafkaProducerConfig)))

  // Step - 3: Configure kafka consumer and create DStream from input topic
  val kafkaParams = Map[String, Object]("bootstrap.servers" -> "localhost:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "use_a_separate_group_id_for_each_stream",
    "auto.offset.reset" -> "latest",
    "enable.auto.commit" -> (false: java.lang.Boolean))
  val stream : InputDStream[ConsumerRecord[String,String]] = KafkaUtils.createDirectStream[String, String](ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String,String](Array(inputTopics),kafkaParams))

  // Step - 4: Process input DStream and publish to output topic
  //stream.foreachRDD(rdd => rdd.foreach(println(_)))
  stream.foreachRDD(rdd => rdd.foreach(record => kafkaSink.value.send(outputTopics,record.value())))

  // Step - 5: Start
  ssc.start()
  ssc.awaitTermination()
}
