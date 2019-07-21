package hello.scala.allaboutscala.chapter4

class ChocolateDonut(name: String) extends BaseDonut(name){
  override def printName: Unit = println(name)
}

object ChocolateDonut{
  def apply(name: String): ChocolateDonut = new ChocolateDonut(name)
}