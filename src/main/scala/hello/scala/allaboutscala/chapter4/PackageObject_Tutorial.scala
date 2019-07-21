package hello.scala.allaboutscala.chapter4

object PackageObject_Tutorial extends App {

  println("\nStep 5: How to create instances or objects for the Donut case class from package object")
  val vanillaDonut: DonutCaseClass = DonutCaseClass("Vanilla", 1.50)
  println(s"Vanilla donut name = ${vanillaDonut.name}")
  println(s"Vanilla donut price = ${vanillaDonut.price}")
  println(s"Vanilla donut produceCode = ${vanillaDonut.productCode}")
  println(s"Vanilla donut uuid = ${vanillaDonut.uuid}")

  println("\nStep 6: How to create new JodaTime instance using DateTime alias from package object")
  val today = new DesiTime()
  println(s"today = $today, datetime class = ${today.getClass}")

}
