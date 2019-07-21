package hello.scala.allaboutscala.chapter5

object Chapter5 extends App {
  // ============  To Create And Extend Trait In Scala  ============
  println("Step 1: Create a trait which will define the methods for a data access layer")

  trait DonutShoppingCartDao {
    def add(donutName: String): Long

    def update(donutName: String): Boolean

    def search(donutName: String): String

    def delete(donutName: String): Boolean
  }
  println("\nStep 2: Create a DonutShoppingCart class which extends the trait from Step 1 and implements its methods")
  class DonutShoppingCart extends DonutShoppingCartDao {
    override def add(donutName: String): Long = {
      println(s"DonutShoppingCart-> add method -> donutName: $donutName")
      1
    }

    override def update(donutName: String): Boolean = {
      println(s"DonutShoppingCart-> update method -> donutName: $donutName")
      true
    }

    override def search(donutName: String): String = {
      println(s"DonutShoppingCart-> search method -> donutName: $donutName")
      donutName
    }

    override def delete(donutName: String): Boolean = {
      println(s"DonutShoppingCart-> delete method -> donutName: $donutName")
      true
    }
  }

  println("\\nStep 3: Create an instance of DonutShoppingCart and call the add, update, search and delete methods")
  val donutShoppingCart: DonutShoppingCart = new DonutShoppingCart
  donutShoppingCart.add("Strawberry Donut")
  donutShoppingCart.update("Vanilla Donut")
  donutShoppingCart.search("Glazed Donut")
  donutShoppingCart.delete("Sweet Donut")

  println("\nStep 4: Create an instance of DonutShoppingCart and assign its type to the trait DonutShoppingCartDao")
  val donutShoppingCart2: DonutShoppingCartDao = new DonutShoppingCart()
  donutShoppingCart2.add("Vanilla Donut")
  donutShoppingCart2.update("Vanilla Donut")
  donutShoppingCart2.search("Vanilla Donut")
  donutShoppingCart2.delete("Vanilla Donut")


  // ============  To Create Trait With Type Parameters  ============
  println("Step 1: Create a trait with type which will define the methods for a data access layer")
  trait TypedDonutShoppingCartDao[A] { // [A <: String]

    def add(donut: A): Long

    def update(donut: A): Boolean

    def search(donut: A): A

    def delete(donut: A): Boolean

  }
  println("\nStep 2: Create a TypedDonutShoppingCart class of type A which extends the trait from Step 1 and implements its methods")
  class TypedDonutShoppingCart[A] extends TypedDonutShoppingCartDao[A] {

    override def add(donut: A): Long = {
      println(s"DonutShoppingCart-> add method -> donut: $donut")
      1
    }

    override def update(donut: A): Boolean = {
      println(s"DonutShoppingCart-> update method -> donut: $donut")
      true
    }

    override def search(donut: A): A = {
      println(s"DonutShoppingCart-> search method -> donut: $donut")
      donut
    }

    override def delete(donut: A): Boolean = {
      println(s"DonutShoppingCart-> delete method -> donut: $donut")
      true
    }
  }

  println("\nStep 3: Create an instance of TypedDonutShoppingCart of type String and call the add, update, search and delete methods")
  val donutShoppingCart1: TypedDonutShoppingCart[String] = new TypedDonutShoppingCart[String]
  donutShoppingCart1.add("Vanilla Donut")
  donutShoppingCart1.update("Vanilla Donut")
  donutShoppingCart1.search("Vanilla Donut")
  donutShoppingCart1.delete("Vanilla Donut")

  println("\nStep 4: Create an instance of TypedDonutShoppingCart of type String and assign its type to the trait TypedDonutShoppingCartDao")
  val donutShoppingCart3: TypedDonutShoppingCartDao[String] = new TypedDonutShoppingCart[String]()
  donutShoppingCart3.add("Vanilla Donut")
  donutShoppingCart3.update("Vanilla Donut")
  donutShoppingCart3.search("Vanilla Donut")
  donutShoppingCart3.delete("Vanilla Donut")


  // ============  To Extend Multiple Traits  ============
  println("\nStep 2: Create a second trait which will define the methods for checking donut inventory")
  trait DonutInventoryService[A] {
    def checkStockQuantity(donut: A): Int
  }

  println("\nStep 3: Create a DonutShoppingCart2 class which extends multiple traits namely trait DonutShoppingCartDao and trait DonutInventoryService")
  class DonutShoppingCart2[A] extends TypedDonutShoppingCartDao[A] with DonutInventoryService[A] {

    override def add(donut: A): Long = {
      println(s"DonutShoppingCart2-> add method -> donut: $donut")
      1
    }

    override def update(donut: A): Boolean = {
      println(s"DonutShoppingCart2-> update method -> donut: $donut")
      true
    }

    override def search(donut: A): A = {
      println(s"DonutShoppingCart2-> search method -> donut: $donut")
      donut
    }

    override def delete(donut: A): Boolean = {
      println(s"DonutShoppingCart2-> delete method -> donut: $donut")
      true
    }

    override def checkStockQuantity(donut: A): Int = {
      println(s"DonutShoppingCart2-> checkStockQuantity method -> donut: $donut")
      10
    }
  }

  println("\nStep 4: Create an instance of DonutShoppingCart and call the add, update, search and delete methods")
  val donutShoppingCart4: DonutShoppingCart2[String] = new DonutShoppingCart2[String]()
  donutShoppingCart4.add("Vanilla Donut")
  donutShoppingCart4.update("Vanilla Donut")
  donutShoppingCart4.search("Vanilla Donut")
  donutShoppingCart4.delete("Vanilla Donut")

  println("\nStep 5: Call the checkStockQuantity method which was inherited from trait DonutInventoryService")
  donutShoppingCart4.checkStockQuantity("Vanilla Donut")


  // ============  To Use Traits For Dependency Injection  ============
  println("Step 1: Create a trait which knows how to do create, read, update and delete operations CRUD to a given database")
  trait DonutDatabase[A] {
    def addOrUpdate(donut: A): Long
    def query(donut: A): A
    def delete(donut: A): Boolean
  }
  println("\nStep 2: Create a class which extends trait DonutDatabase and knows how to perform CRUD operations with Apache Cassandra as storage layer")
  class CassandraDonutStore[A] extends DonutDatabase[A] {

    override def addOrUpdate(donut: A): Long = {
      println(s"CassandraDonutDatabase-> addOrUpdate method -> donut: $donut")
      1
    }

    override def query(donut: A): A = {
      println(s"CassandraDonutDatabase-> query method -> donut: $donut")
      donut
    }

    override def delete(donut: A): Boolean = {
      println(s"CassandraDonutDatabase-> delete method -> donut: $donut")
      true
    }
  }
  println("\nStep 3: Create a trait which will define the methods for a data access layer and will require dependency injection for DonutDatabase")
  trait DonutShoppingCartDao3[A] {

    val donutDatabase: DonutDatabase[A] // dependency injection

    def add(donut: A): Long = {
      println(s"DonutShoppingCartDao3-> add method -> donut: $donut")
      donutDatabase.addOrUpdate(donut)
    }

    def update(donut: A): Boolean = {
      println(s"DonutShoppingCartDao3-> update method -> donut: $donut")
      donutDatabase.addOrUpdate(donut)
      true
    }

    def search(donut: A): A = {
      println(s"DonutShoppingCartDao3-> search method -> donut: $donut")
      donutDatabase.query(donut)
    }

    def delete(donut: A): Boolean = {
      println(s"DonutShoppingCartDao3-> delete method -> donut: $donut")
      donutDatabase.delete(donut)
    }

  }
  println("\nStep 4: Create a trait which will define the methods for checking donut inventory and will require dependency injection for DonutDatabase")
  trait DonutInventoryService3[A] {

    val donutDatabase: DonutDatabase[A] // dependency injection

    def checkStockQuantity(donut: A): Int = {
      println(s"DonutInventoryService3-> checkStockQuantity method -> donut: $donut")
      donutDatabase.query(donut)
      1
    }

  }
  println("\nStep 5: Create a trait which will act as a facade which extends multiple traits namely trait DonutShoppingCartDao and trait DonutInventoryService. It also inject the correct DonutDatabase implementation - a CassandraDonutStore")
  trait DonutShoppingCartServices[A] extends DonutShoppingCartDao3[A] with DonutInventoryService3[A] {
    override val donutDatabase: DonutDatabase[A] = new CassandraDonutStore[A]()
  }

  println("\nStep 6: Create a DonutShoppingCart class which extends a single facade named DonutShoppingCartServices to expose all the underlying features required by a DonutShoppingCart")
  class DonutShoppingCart3[A] extends DonutShoppingCartServices[A]{
  }

  println("\nStep 7: Create an instance of DonutShoppingCart and call the add, update, search and delete methods")
  val donutShoppingCart13: DonutShoppingCart3[String] = new DonutShoppingCart3[String]()
  donutShoppingCart13.add("Vanilla Donut")
  donutShoppingCart13.update("Vanilla Donut")
  donutShoppingCart13.search("Vanilla Donut")
  donutShoppingCart13.delete("Vanilla Donut")

  println("\nStep 8: Call the checkStockQuantity method")
  donutShoppingCart13.checkStockQuantity("Vanilla Donut")


  // ============  To Use Traits For Dependency Injection Part 2 - Avoid Cake Pattern  ============
  println("\nStep 1: How to define a class to encapsulate inventory services")
  class DonutInventoryService4[T]{
    def checkStock(donut: T): Boolean = {
      println("DonutInventoryService->checkStock")
      true
    }
  }
  println("\nStep 2: How to define a class to encapsulate pricing services")
  class DonutPricingService4[T]{
    def calculatePrice(donut: T): Double = {
      println("DonutPricingService->calculatePrice")
      2.50
    }
  }
  println("\nStep 3: How to define a class to encapsulate creating a donut order")
  class DonutOrderService4[T] {
    def createOrder(donut: T, quantity: Int, price: Double): Int = {
      println(s"Saving donut order to database: donut = $donut, quantity = $quantity, price = $price")
      100 // the id of the booked order
    }
  }
  println("\nStep 4: How to define a class to encapsulate shopping cart services")
  class DonutShoppingCartService4[T](donutInventoryService4: DonutInventoryService4[T],donutPricingService4: DonutPricingService4[T], donutOrderService4: DonutOrderService4[T]){
    def bookOrder(donut: T, quantity: Int): Int = {
      println("DonutShoppingCartService->bookOrder")

      donutInventoryService4.checkStock(donut) match {
        case true =>
          val price = donutPricingService4.calculatePrice(donut)
          donutOrderService4.createOrder(donut, quantity, price) // the id of the booked order

        case false =>
          println(s"Sorry donut $donut is out of stock!")
          -100 // return some error code to identify out of stock
      }
    }
  }
  println("\nStep 5: How to define a trait to encapsulate all the services for Donut store")
  trait DonutStoreServices {
    val donutInventoryService = new DonutInventoryService4[String]
    val donutPricingService = new DonutPricingService4[String]
    val donutOrderService = new DonutOrderService4[String]
    val donutShoppingCartService = new DonutShoppingCartService4[String](donutInventoryService, donutPricingService, donutOrderService)
  }
  println("\nStep 6: How to define a facade to expose functionality of DonutStoreServices")
  trait DonutStoreAppController{
    this: DonutStoreServices =>

    def bookOrder(donut: String, quantity: Int): Int = {
      println("DonutStoreAppController->bookOrder")
      donutShoppingCartService.bookOrder(donut, quantity)
    }
  }

  println("\nStep 7: How to create a Donut store app which extends facade from Step 5 and injects the required donut services from Step 4")
  object DonutStoreApp extends DonutStoreAppController with DonutStoreServices
  println("\nStep 8: How to call the bookOrder method of the Donut store app from Step 7")
  DonutStoreApp.bookOrder("Vanilla Donut", 10)

  println("\nStep 9: Test DonutStoreApp by injecting a mocked version of DonutStoreServices")
  trait MockedDonutStoreServices extends DonutStoreServices {
    override val donutInventoryService: DonutInventoryService4[String] = new DonutInventoryService4[String]
    override val donutPricingService: DonutPricingService4[String] = new DonutPricingService4[String]
    override val donutOrderService: DonutOrderService4[String] = new DonutOrderService4[String]
    override val donutShoppingCartService: DonutShoppingCartService4[String] = new DonutShoppingCartService4[String](
      donutInventoryService, donutPricingService, donutOrderService)
  }
  println("\nStep 1: Create a Mocked Donut Store App and inject mocked donut services")
  object MockedDonutStoreApp extends DonutStoreAppController with MockedDonutStoreServices
  MockedDonutStoreApp.bookOrder("Mocked Donut",10)


  // ============  To Use Traits For Dependency Injection Part 2 - Avoid Cake Pattern  ============
  // Step 1: Define a wrapper object called Cakes to hold various types of cakes
  object Cakes {

    // Step 2: Define a base trait to represent a Cake
    trait Cake {
      def name: String
    }

    // Step 3: Define class implementations for the Cake trait namely: Cupcake, Donut and UnknownCake
    class UnknownCake extends Cake {
      override def name: String = "Unknown Cake ... but still delicious!"
    }

    class Cupcake extends Cake {
      override def name: String = "Cupcake"
    }

    class Donut extends Cake {
      override def name: String = "Donut"
    }

  }
  // Step 4: Define a wrapper object called CakeFactory")
  object CakeFactory {
    import Cakes._

    // Step 5: Define an apply method which will act as a factory to produce the correct Cake implementation
    def apply(cake: String): Cake = {
      cake match {
        case "cupcake" => new Cupcake
        case "donut" => new Donut
        case _ => new UnknownCake
      }
    }
  }

  // Step 6: Call the CakeFactory
  println(s"A cupcake = ${CakeFactory("cupcake").name}")
  println(s"A donut = ${CakeFactory("donut").name}")
  println(s"Unknown cake = ${CakeFactory("coconut tart").name}")

  //7079902137
}
