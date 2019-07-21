package hello.scala.allaboutscala.chapter4

object Chapter4 extends App {
  println("Step 1: How to define a simple class to represent a Donut object")
  class DonutX(name: String, productCode: Long) {
    def print = println(s"Donut name = $name , product code = $productCode")
  }

  println("\nStep 2: How to create instances of Donut class")
  val glazedDonutX = new DonutX("Glazed Donut", productCode = 1111)
  val vanillaDonutX = new DonutX("Vanilla Donut", productCode = 2222)

  println("\nStep 3: How to call the print function for each of the donut object")
  glazedDonutX.print
  vanillaDonutX.print

  println("\nStep 4: How to access the properties of class Donut")
  //glazedDonut.name
  //glazedDonut.productCode
  //Above code will give compilation error, will see next how to access it.


  // ============  Companion Objects  ============
  println("\nStep 3: How to create instances of the Donut class using the companion object")
  val glazedDonut = Donut("Glazed Donut", 1111)
  val vanillaDonut = Donut("Vanilla Donut", 2222)
  glazedDonut.print
  vanillaDonut.print


  // ============  Companion Objects' Apply Method As A Factory (Class Hierarchy Via Inheritance)  ============
  println("\nStep 4: How to call apply method of companion object which is a factory")
  val glazedDonutY = Donut("Glazed Donut")
  println(s"The class type of glazedDonut = ${glazedDonutY.getClass}")
  glazedDonutY.print

  val vanillaDonutY = Donut("Vanilla Donut")
  println(s"The class type of vanillaDonut = ${vanillaDonutY.getClass}")
  vanillaDonutY.print


  // ============  Singleton Object  ============
  println("\nStep 4: How to call global discount field from Step 2")
  println(s"Global discount = ${DonutShoppingCartCalculator.discount}")

  println("\nStep 5: How to call the utility function calculateTotalCost from Step 3")
  println(s"Call to calculateTotalCost function = ${DonutShoppingCartCalculator.calculateTotalCost(List())}")

  // ============  Case Class  ============
  case class DonutCaseClass(name: String, price: Double, productCode: Option[Long]= None)

  println("\nStep 2: How to create instances or objects for the Donut case class")
  val vanillaDonutCC: DonutCaseClass = DonutCaseClass("Vanilla Donut", 1.50)
  val glazedDonutCC: DonutCaseClass = DonutCaseClass("Glazed Donut", 2.0)
  println(s"Vanilla Donut = $vanillaDonutCC")
  println(s"Glazed Donut = $glazedDonutCC")

  println("\nStep 3: How to access fields of the Donut object")
  println(s"Vanilla Donut name field = ${vanillaDonutCC.name}")
  println(s"Vanilla Donut price field = ${vanillaDonutCC.price}")
  println(s"Vanilla Donut productCode field = ${vanillaDonutCC.productCode}")

  println("\nStep 4: How to modify or update fields of the Donut object")
  // vanillaDonut.name = "vanilla donut" // compiler error. fields are immutable by default

  println("\nStep 5: How to define the hashCode and equals method for Donut object")
  val shoppingCart: Map[DonutCaseClass, Int] = Map(vanillaDonutCC -> 4, glazedDonutCC -> 3)
  println(s"All items in shopping cart = ${shoppingCart}")
  println(s"Quantity of vanilla donuts in shopping cart = ${shoppingCart(vanillaDonutCC)}")
  println(s"Quantity of glazed donuts in shopping cart = ${shoppingCart(glazedDonutCC)}")

  println("\nTIP: How to create a new object of Donut by using the copy() method of the case class")
  val chocolateVanillaDonut: DonutCaseClass = vanillaDonutCC.copy(name = "Chocolate And Vanilla Donut", price = 5.0)
  println(s"Chocolate And Vanilla Donut = $chocolateVanillaDonut")


  // ============  To Use Type Alias: Type Aliasing Versus Case Class  ============
  println("\nStep 2: How to create instances or objects for the Donut case class")
  //val vanillaDonut: DonutCaseClass = DonutCaseClass("Vanilla", 1.50)
  //val glazedDonut: DonutCaseClass = DonutCaseClass("Glazed", 2.0)
  println(s"Vanilla Donut = $vanillaDonutCC")
  println(s"Glazed Donut = $glazedDonutCC")

  println(s"\nStep 3: How to use type alias to name a Tuple2 pair into a domain specific type called CartItem")
  type CartItem[DonutCaseClass, Int] = Tuple2[DonutCaseClass, Int]

  println("\nStep 4: How to create instances of the aliased typed CartItem")
  val cartItem = new CartItem(vanillaDonutCC, 5)
  println(s"cartItem = $cartItem")
  println(s"cartItem first value = ${cartItem._1}")
  println(s"cartItem second value = ${cartItem._2}")

  println("\nStep 5: How to use an aliased typed into a function parameter")
  def calculateTotal(shoppingCartItems: Seq[CartItem[DonutCaseClass, Int]]) :Double ={
    // calculate the total cost
    shoppingCartItems.foreach{
      cartItem => println(s"CartItem donut = ${cartItem._1}, quantity = ${cartItem._2}")
    }
    10 // some random total cost
  }

  println("\nStep 6: How to use a case class instead of an aliased typed")
  case class ShoppingCartItem(donut: DonutCaseClass, quantity: Int)

  val shoppingItem: ShoppingCartItem = ShoppingCartItem(DonutCaseClass("Glazed Donut", 2.50), 10)
  println(s"shoppingItem donut = ${shoppingItem.donut}")
  println(s"shoppingItem quantity = ${shoppingItem.quantity}")

  println("\nStep 7: How to use case class from Step 6 to represent a Sequence of Donut items in a shopping cart")
  def calculateTotal2(shoppingCartItems: Seq[ShoppingCartItem]): Double = {
    // calculate the total cost
    shoppingCartItems.foreach { shoppingCartItem =>
      println(s"ShoppingCartItem donut = ${shoppingCartItem.donut}, quantity = ${shoppingCartItem.quantity}")
    }
    10 // some random total cost
  }


  // ============  To Use Implicit Class - Extension Methods  ============
  println("\nStep 3: How to define an implicit class to augment or extend the Donut object with a uuid field")
  object DonutImplicits {
    implicit class AugmentedDonut(donut: DonutCaseClass) {
      def uuid: String = s"${donut.name} - ${donut.productCode.getOrElse(12345)}"
    }
  }

  println("\nStep 4: How to import and use the implicit class AugmentedDonut from Step 3")
  import DonutImplicits._
  println(s"Vanilla donut uuid = ${vanillaDonutCC.uuid}")

  println("\nStep 6: How to create new ZonedDateTime instance using DesiTime alias from package object")
  val today = new DesiTime()
  println(s"today = $today, datetime class = ${today.getClass}")


  // ============  To Extend Class - Class Inheritance  ============
  println("\nStep 4: How to instantiate Donut objects")
  val strawberryDonut: BaseDonut = StrawberryDonut("Vanilla Donut")
  strawberryDonut.printName

  val chocolateDonut: BaseDonut = ChocolateDonut("Chocolate Donut")
  chocolateDonut.printName


  // ============  To Extend Case Class - Case Class Inheritance  ============
  println("\nStep 2: How to extend abstract class Donut and define a case class called VanillaDonutExtndBaseDonut")
  case class VanillaDonutExtndBaseDonut (name: String) extends BaseDonut(name) {
    override def printName: Unit = println(name)
  }

  println("\nStep 3: How to extend abstract class Donut and define another case class called GlazedDonutExtndBaseDonut")
  case class GlazedDonutExtndBaseDonut(name: String) extends BaseDonut(name) {
    override def printName: Unit = println(name)
  }

  println("\nStep 4: How to instantiate Donut objects")
  val vanillaDonutE: VanillaDonutExtndBaseDonut = VanillaDonutExtndBaseDonut("Vanilla Donut")
  vanillaDonutE.printName

  val glazedDonutE: BaseDonut = GlazedDonutExtndBaseDonut("Glazed Donut")
  glazedDonutE.printName


  // ============  To Create Typed Class  ============
  println("\nStep 5: How to define a ShoppingCart typed class which expects Donut types")
  class ShoppingCart[D <: BaseDonut](donuts: Seq[D]){
    def printCartItems ={
      //donuts.foreach(item => println(item))
      //donuts.foreach(println(_))
      donuts.foreach(_.printName)
    }
  }

  println("\nStep 6: How to create instances or objects of ShoppingCart class")
  val shoppingCartBaseDonut: ShoppingCart[BaseDonut] = new ShoppingCart(Seq[BaseDonut](vanillaDonutE, glazedDonutE))
  shoppingCartBaseDonut.printCartItems
  //val shoppingCartVanillaDonut: ShoppingCart[BaseDonut] = new ShoppingCart[VanillaDonutExtndBaseDonut](Seq(vanillaDonutE))
  //shoppingCartVanillaDonut.printCartItems


  // ============  To Create Covariance Type Class  ============
  println(s"\nStep 7: How to enable covariance on ShoppingCart")
  class ShoppingCart2[+D <: BaseDonut](donuts: Seq[D]) {
    def printCartItems: Unit = donuts.foreach(_.printName)
  }

  val shoppingCart2: ShoppingCart2[BaseDonut] = new ShoppingCart2[VanillaDonutExtndBaseDonut](Seq(vanillaDonutE))
  shoppingCart2.printCartItems
  //val shoppingCart: ShoppingCart[VanillaDonutExtndBaseDonut] = new ShoppingCart[BaseDonut](Seq(glazedDonutE))


  // ============  To Create Contra-Covariance Type Class  ============
  println(s"\nStep 7: How to enable covariance on ShoppingCart")
  class ShoppingCart3[-D <: BaseDonut](donuts: Seq[D]) {
    def printCartItems: Unit = donuts.foreach(_.printName)
  }

  val shoppingCart3: ShoppingCart3[VanillaDonutExtndBaseDonut] = new ShoppingCart3[BaseDonut](Seq(glazedDonutE))
  shoppingCart3.printCartItems
}
