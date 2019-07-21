package hello.scala.allaboutscala.chapter4

class StrawberryDonut(name: String) extends BaseDonut(name) {
  override def printName: Unit = println(name)
}

object StrawberryDonut{
  def apply(name: String): BaseDonut = new StrawberryDonut(name)
}
