package hello.scala.allaboutscala.chapter4

class Donut(name: String, productCode: Option[Long] = None) {
  println("Define Donut class") // The println statements will be executed when this class is instantiated

  def print = {
    println(s"Donut name = $name , product code = $productCode , uuid = ${Donut.uuid}")
    Donut.prrintInCompanionObject
  }
}

object Donut {

  private val uuid = 1

  def apply(name: String, productCode: Long): Donut ={
     new Donut(name, Some(productCode))
  }

  // Overriding apply for tutorial - Companion Objects' Apply Method As A Factory (Class Hierarchy Via Inheritance)
  def apply(name: String): Donut ={
    println(s"Creating object for donut = $name")
    name match {
      case "Glazed Donut" => new GlazedDonut(name)
      case "Vanilla Donut" => new VanillaDonut(name)
      case _ => new Donut(name)
    }
  }

  def prrintInCompanionObject={
    println(s"Printing from companion object , uuid = $uuid")
  }
}

class GlazedDonut(name: String) extends Donut(name)
class VanillaDonut(name: String) extends Donut(name)

