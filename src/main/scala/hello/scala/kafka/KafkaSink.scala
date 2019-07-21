package hello.scala.kafka

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

class KafkaSink[K,V](createProducer: () => KafkaProducer[K, V]) extends Serializable {
  lazy val producer = createProducer()
  def send(topic: String, value:V): Unit = producer.send(new ProducerRecord[K,V](topic,value))
}

object KafkaSink{
  def apply[K,V](config: java.util.Map[String,Object]): KafkaSink[K,V] = {
    val f = () =>{
      val producer = new KafkaProducer[K,V](config)
      sys.addShutdownHook(producer.close())

      producer
    }
    new KafkaSink(f)
  }
}
