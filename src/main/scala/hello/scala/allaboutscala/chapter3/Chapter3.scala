package hello.scala.allaboutscala

import scala.annotation.tailrec
import scala.util.Random
import scala.util.control.TailCalls.TailRec

object Chapter3 extends App {
  // ============ Create And Use Functions ============
  println("Step 1: How to define and use a function which has no parameters and has a return type")
  def favoriteDonut(): String = {
    "Glazed Donut"
  }

  val myFavoriteDonut = favoriteDonut()
  println(s"My favorite donut is $myFavoriteDonut")

  println("\nStep 2: How to define and use a function with no parenthesis")
  def leastFavoriteDonut = "Plain Donut"
  println(s"My least favorite donut is $leastFavoriteDonut")

  println("\nStep 3: How to define and use a function with no return type")
  def printDonutSalesReport(): Unit = {
    // lookup sales data in database and create some report
    println("Printing donut sales report... done!")
  }
  printDonutSalesReport()

  println("\nStep 1: How to define function with parameters")
  def calculateDonutCost(donutName: String, quantity: Int): Double = {
    println(s"Calculating cost for $donutName, quantity = $quantity")

    // make some calculations ...
    2.50 * quantity
  }

  println("\nStep 2: How to call a function with parameters")
  val totalCost = calculateDonutCost("Glazed Donut", 5)
  println(s"Total cost of donuts = $totalCost")

  println("\nStep 3: How to add default values to function parameters")
  def calculateDonutCost2(donutName: String, quantity: Int, couponCode: String = "NO CODE"): Double = {
    println(s"Calculating cost for $donutName, quantity = $quantity, couponCode = $couponCode")
    // make some calculations ...
    2.50 * quantity
  }

  println("\nStep 4: How to call a function with parameters that has default values")
  val totalCostWithDiscount = calculateDonutCost2("Glazed Donut", 4, "COUPON_12345")
  val totalCostWithoutDiscount = calculateDonutCost2("Glazed Donut", 4)
  println(s"Total cost with discount = $totalCostWithDiscount")
  println(s"Total cost with discount = $totalCostWithoutDiscount")


  // ============ Use Option In Function Parameters ============
  println("\nStep 1: How to define an Option in a function parameter")
  def calculateDonutCost(donutName: String, quantity: Int, couponCode: Option[String]): Double = {
    println(s"Calculating cost for $donutName, quantity = $quantity")

    couponCode match {
      case Some(coupon) =>
        val discount = 0.1 // Let's simulate a 10% discount
      val totalCost = 2.50 * quantity * (1 - discount)
        totalCost

      case None => 2.50 * quantity
    }
  }
  println("\nStep 2: How to call a function which has an Option parameter")
  println(s"""Total cost = ${calculateDonutCost("Glazed Donut", 5, None)}""")

  println("\nStep 3: How to assign a default value to an Option parameter")
  def calculateDonutCostWithDefaultOptionValue(donutName: String, quantity: Int, couponCode: Option[String] = None): Double = {
    println(s"Calculating cost for $donutName, quantity = $quantity")

    couponCode match{
      case Some(coupon) =>
        val discount = 0.1 // Let's simulate a 10% discount
      val totalCost = 2.50 * quantity * (1 - discount)
        totalCost

      case _ => 2.50 * quantity
    }
  }
  println("\nStep 4: How to call a function which has an Option parameter with a default value")
  println(s"""Total cost = ${calculateDonutCostWithDefaultOptionValue("Glazed Donut", 5)}""")
  println(s"""Total cost with discount = ${calculateDonutCostWithDefaultOptionValue("Glazed Donut", 5, Some("COUPON_1234"))}""")

  println(s"\nTip 1: Use the map function to extract a valid Option value")
  val favoriteDonutOption: Option[String] = Some("Glazed Donut")
  favoriteDonutOption.map(d => println(s"Favorite donut = $d"))

  val leastFavoriteDonutOption: Option[String] = None
  leastFavoriteDonutOption.map(d => println(s"Least Favorite donut = $d"))


  // ============ Function With Option Return Type ============
  println(s"\nStep 1: Define a function which returns an Option of type String")
  def dailyCouponCode(): Option[String] = {
    // look up in database if we will provide our customers with a coupon today
    val couponFromDb = "COUPON_1234"
    Option(couponFromDb).filter(_.nonEmpty)
  }

  println(s"\nStep 2: Call function with Option return type using getOrElse")
  val todayCoupon: Option[String] = dailyCouponCode()
  println(s"Today's coupon code = ${todayCoupon.getOrElse("No Coupon's Today")}")

  println(s"\nStep 3: Call function with Option return type using pattern matching")
  dailyCouponCode() match {
    case Some(cupon) => println(s"Today's coupon code = $cupon")
    case None => println(s"Sorry there is no coupon code today!")
  }

  println(s"\nStep 4: Call function with Option return type using map")
  dailyCouponCode().map(couponCode => println(s"Today's coupon code = $couponCode"))

  println("\nStep 5: Review function from previous tutorial which has an Option parameter")
  println(s"""Total cost with daily cupon code = ${calculateDonutCost("Glazed Donut",5, dailyCouponCode)}""")

  println(s"\nTip - 1: Call function with Option return type using fold")
  val todayCouponUsingFold: String = dailyCouponCode().fold("No Coupon Available")(couponCode => couponCode)
  println(s"Today's coupon code = $todayCouponUsingFold")


  // ============ Create Function With Implicit Parameters ============
  println(s"\nStep 1: How to define a function with an implicit parameter")
  def totalCost(donutType: String, quantity: Int)(implicit discount: Double): Double = {
    println(s"Calculating the price for $quantity $donutType")
    val totalCost = 2.50 * quantity * (1 - discount)
    totalCost
  }

  println("\nStep 2: How to define an implicit value")
  implicit val discountX: Double = 0.1
  println(s"All customer will receive a ${discountX * 100}% discount")

  println("\nStep 3: How to call a function which has an implicit parameter")
  println(s"""Total cost with discount of 5 Glazed Donuts = ${totalCost("Glazed Donut", 5)}""")

  println("\nStep 4: How to define a function which takes multiple implicit parameters")
  def totalCost2(donutType: String, quantity: Int)(implicit discount: Double, storeName: String): Double = {
    println(s"[$storeName] Calculating the price for $quantity $donutType")
    val totalCost = 2.50 * quantity * (1 - discount)
    totalCost
  }

  println("\nStep 5: How to call a function which takes multiple implicit parameters")
  implicit val storeName: String = "Tasty Donut Store"
  println(s"""Total cost with discount of 5 Glazed Donuts = ${totalCost2("Glazed Donut", 5)}""")

  println("\nStep 6: How to manually pass-through implicit parameters")
  println(s"""Total cost with discount of 5 Glazed Donuts, manually passed-through = ${totalCost2("Glazed Donut", 5)(0.1, "Scala Donut Store")}""")


  // ============ Create Implicit Function ============
  println("\nStep 1: How to create a wrapper String class which will extend the String type")
  class DonutString(s: String) {

    def isFavoriteDonut: Boolean = s == "Glazed Donut"

  }

  println("\nStep 2: How to create an implicit function to convert a String to the wrapper String class")
  object DonutConversios{
    implicit def stringToDonutString(s: String) = new DonutString(s)
  }

  println("\nStep 3: How to import the String conversion so that it is in scope")
  import DonutConversios._
  println("\nStep 4: How to create String values")
  val glazedDonut = "Glazed Donut"
  val vanillaDonut = "Vanilla Donut"

  println("\nStep 5: How to access the custom String function called isFavaoriteDonut")
  println(s"Is glazed donut is my favourite donut = ${glazedDonut.isFavoriteDonut}")
  println(s"Is vanilla donut is my favourite donut = ${vanillaDonut.isFavoriteDonut}")


  // ============ Create Typed Function ============
  println("\nHow to define a function which takes a String parameter")
  def applyDiscount(cuponCode: String) ={
    println(s"Lookup percentage discout in database for $cuponCode")
  }

  println("\nHow to define a function which takes parameter of type Double")
  def applyDiscount(percentageDiscount: Double) ={
    println(s"$percentageDiscount will be applied")
  }

  println("\nStep 3: Calling applyDiscount function with String or Double parameter types")
  applyDiscount("COUPON_1234")
  applyDiscount(10)

  println("\nHow to define a generic typed function which will specify types of its parameter")
  def applyDiscount[T](discount:T)={
    discount match {
      case d:String => println(s"Lookup percentage discount in database for $d")
      case d:Double => println(s"$d discout will be applied")
      case _ => println("Unsupported discount type")
    }
  }
  println("\nStep 5: How to call a function which has typed parameters")
  applyDiscount[String]("COUPON_123")
  applyDiscount[Double](10)


  // ============ Polymorphic Function With Generic Return Type ============
  println("\nStep 3: How to define a generic typed function which also has a generic return type")
  def applyDiscountWithReturnType[T](discount: T):Seq[T] = {
    discount match {
      case d: String =>
        println(s"Lookup percentage discount in database for $d")
        Seq[T](discount)
      case d: Double =>
        println(s"$d discout will be applied")
        Seq[T](discount)
      case d @ _ =>
        println("Unsupported discount type")
        Seq[T](discount)
    }
  }
  println("\nStep 4: How to call a generic typed function which also has a generic return type")
  println(s"Result of applyDiscountWithReturnType with String parameter = ${applyDiscountWithReturnType[String]("COUPON_123")}")
  println(s"Result of applyDiscountWithReturnType with Double parameter = ${applyDiscountWithReturnType[Double](10.5)}")
  println(s"Result of applyDiscountWithReturnType with Char parameter = ${applyDiscountWithReturnType[Char]('U')}")


  // ============ Variable Argument Function ============
  def printReport(names: String*) {
    println(s"""Donut Report = ${names.mkString(" - ")}""")
  }

  println("\nStep 2: How to call function which takes variable number of String arguments")
  //printReport("Glazed Donut", "Strawberry Donut", "Vanilla Donut")
  //printReport("Chocolate Donut")
  //printReport()

  println("\nStep 3: How to pass a List to a function with variable number of arguments")
  val listDonuts: List[String] = List("Glazed Donut", "Strawberry Donut", "Vanilla Donut")
  printReport(listDonuts:_*) // To make varargs accept List,Seq,Array. We need to use type ascription. i.e varNames: _*
  println("\nStep 4: How to pass a Sequence to a function with variable number of arguments")
  val seqDonuts: Seq[String] = Seq("Chocolate Donut", "Plain Donut")
  printReport(seqDonuts:_*)
  println("\nStep 5: How to pass an Array to a function with variable number of arguments")
  val arrDonuts: Array[String] = Array("Coconut Donut")
  printReport(arrDonuts:_*)

  // ============ Create Functions As Symbols ============
  println("\nStep 3: How to define function whose name is just the symbol minus -")
  class DonutCostCalculator {

    // We are hard-coding the totalCost value for simplicity.
    val totalCost = 100
    def -(discount: Double): Double = {
      totalCost - discount
    }

    def +++(taxAmount: Double): Double = {
      totalCost + taxAmount
    }
  }
  val donutCostCalculator = new DonutCostCalculator()
  println("\nStep 4: How to call function whose name is just the symbol -")
  println(s"Calling function - = ${donutCostCalculator.-(10.5)}")

  println("\nStep 5: How to call a function using the operator style notation")
  println(s"Calling function - with operator style notation = ${donutCostCalculator - 10.5}")


  // ============ Create Function Currying With Parameter Groups ============
  println("\nStep 1: How to define function with curried parameter groups")
  def totalCostWithCurrying(donutType: String)(quantity: Int)(discount: Double): Double = {
    println(s"Calculating total cost for $quantity $donutType with ${discount * 100}% discount")
    val totalCost = 2.50 * quantity
    totalCost - (totalCost * discount)
  }
  println("\nStep 2: How to call a function with curried parameter groups")
  println(s"Total cost = ${totalCostWithCurrying("Glazed Donut")(10)(0.1)}")

  println("\nStep 3: How to create a partially applied function from a function with curried parameter groups")
  val totalCostForGlazedDonuts = totalCostWithCurrying("Glazed Donuts") _

  println("\nStep 4: How to call a partially applied function")
  println(s"\n Total cost for glazed donuts = ${totalCostForGlazedDonuts(10)(0.1)}")


  // ============ Higher Order Function ============
  println("\nStep 2: How to define a higher order function which takes another function as parameter")
  def totalCostWithDiscountFunctionParameter(donutType: String)(quantity: Int)(f:Double => Double)={
    println(s"Calculating total cost for $quantity $donutType")
    val totalCost = 2.50 * quantity
    f(totalCost)
  }
  println("\nStep 3: How to call higher order function and pass an anonymous function as parameter")
  val totalCostOf5Donuts = totalCostWithDiscountFunctionParameter("Glazed Donut")(5){totalCost =>
    val discount = 2 // Assume you fetch discount from database
    totalCost - discount
  }
  println(s"Total cost of 5 Glazed Donuts with anonymous discount function = $totalCostOf5Donuts")

  println("\nStep 4: How to define and pass a function to a higher order function")
  def applyDiscountPass(totalCost: Double) :Double ={
    val discount = 2; // assume you fetch discount from database
    totalCost - discount
  }
  println(s"Total cost of 5 glazed donuts with discount function = ${totalCostWithDiscountFunctionParameter("Glazed Donuts")(5)(applyDiscountPass(_))}")


  // ============ Create Higher Order Function - Call-By-Name Function ============
  println("Step 1: How to define a List with Tuple3 elements")
  val listOrders = List(("Glazed Donut", 5, 2.50), ("Vanilla Donut", 10, 3.50))

  println("\nStep 2: How to define a function to loop through each Tuple3 of the List and calculate total cost")
  def placeOrder(orders: List[(String, Int, Double)])(exchangeRate: Double): Double = {
    var totalCost: Double = 0.0
    orders.foreach {order =>
      val costOfItem = order._2 * order._3 * exchangeRate
      println(s"Cost of ${order._2} ${order._1} = £$costOfItem")
      totalCost += costOfItem
    }
    totalCost
  }
  println("\nStep 3: How to call function with curried group parameter for List of Tuple3 elements")
  println(s"Total cost of order = £${placeOrder(listOrders)(0.5)}")

  println("\nStep 4: How to define a call-by-name function")
  def placeOrderWithByNameParameter(orders: List[(String, Int, Double)])(exchangeRate: => Double): Double = {
    var totalCost: Double = 0.0
    orders.foreach {order =>
      val costOfItem = order._2 * order._3 * exchangeRate
      println(s"Cost of ${order._2} ${order._1} = £$costOfItem")
      totalCost += costOfItem
    }
    totalCost
  }

  println("\nStep 5: How to define a simple USD to GBP function")
  val randomExchangeRate = new Random(10)
  def usdToGbp: Double = {
    val rate = randomExchangeRate.nextDouble()
    println(s"Fetching USD to GBP exchange rate = $rate")
    rate
  }

  println("\nStep 6: How to call function with call-by-name parameter")
  println(s"Total cost of order = £${placeOrderWithByNameParameter(listOrders)(usdToGbp)}")


  // ============ Create Higher Order Function - Callback Function Parameter ============
  println("\nStep 1: How to define a function with a callback parameter")
  def printReport(sendEmailCallback: ()=> Unit): Unit ={
    println("Printing report ... started")
    // look up some data in database and create a report
    println("Printing report ... finished")
    sendEmailCallback()
  }
  println("\nStep 2: How to call a function which has a callback parameter")
  printReport(()=>{
    println("Sending email ... finished")
  })
  println("\nStep 3: How to call a function without providing its callback parameter")
  // printReport() // You get compile time error
  printReport(()=>{})

  println("\nStep 4: How to define a function Function with an Option callback")
  def printReportWithOptionCallback(sendEmailCallback:Option[()=>Unit]=None) ={
    println("Printing report ... started")
    // look up some data in database and create a report
    println("Printing report ... finished")
    sendEmailCallback.map(callback => callback())
  }

  println("\nStep 5: How to call a function without providing its callback parameter")
  printReportWithOptionCallback() // more elegant

  println("\nStep 6: How to call a function with Option callback parameter")
  printReportWithOptionCallback(Some(()=> {
    println("Sending email wrapped in Some() ... finished")
  }))


  // ============ Create Function Using The Val Keyword Instead Of Def ============
  println("\nStep 1: Using already defined a higher order function which takes another function as parameter")
  println("Step 2: Using already defined and pass a def function to a higher order function")
  println(s"Total cost of 5 glazed donuts with discount def function = ${totalCostWithDiscountFunctionParameter("Glazed Donut")(5)(applyDiscountPass(_))}")

  println("\nStep 3: How to define and pass a val function to a higher order function")
  val applyDiscountValueFunction = (totalCost: Double) =>{
    val discount = 2 // assume you fetch discount from database
    totalCost - discount
  }
  println(s"Total cost of 5 Glazed Donuts with discount val function = ${totalCostWithDiscountFunctionParameter("Glazed Donut")(5)(applyDiscountValueFunction)}")


  // ============ Function Composition Using AndThen ============
  println("\nStep 1: Assume a pre-calculated total cost amount")
  val totalCostX: Double = 10
  println("\nStep 2: How to define a val function to apply discount to total cost")
  val applyDiscountValFunctionX = (amount: Double) =>{
    println("Apply discount val function")
    val discount = 2 // Fetch discount from database
    amount - discount
  }

  println("\nStep 3: How to call a val function")
  //println(s"Total cost of 5 donuts with discount = ${applyDiscountValFunction(totalCostX)}")

  println("\nStep 4: How to define a val function to apply tax to total cost")
  val applyTaxValFunction = (amount: Double) =>{
    println("Apply tax val function")
    val tax = 1 // fetch tx from database
    amount + tax
  }

  println("\nStep 5: How to call andThen on a val function")
  println(s"Total cost of 5 donuts using andThen = ${(applyDiscountValFunctionX andThen applyTaxValFunction)(totalCostX)}")


  // ============ Function Composition Using Compose ============
  println("\nStep 5: How to call compose on a val function")
  println(s"Total cost of 5 donuts using compose = ${(applyDiscountValFunctionX compose applyTaxValFunction)(totalCostX)}")


  // ============ Create Tail Recursive Function - @annotation.tailrec ============
  println("\nStep 1: How to define an Array of type String")
  val donutsArray:Array[String] = Array("Vanilla Donut", "Strawberry Donut", "Plain Donut", "Glazed Donut")
  @tailrec
  def search(donutName: String, donuts: Array[String], index: Int): Option[Boolean] ={
    if(donuts.length == index) None
    else if(donuts(index) == donutName) Some(true)
    else search(donutName, donuts, index+1)
  }

  println("\nStep 3: How to call a tail recursive function")
  println(s"Find glazed donut = ${search("Glazed Donut", donutsArray, 0).get}")
  println(s"Find chocolate donut = ${search("Chocolate Donut", donutsArray, 0)}")


  // ============ Create Tail Recursive Function - scala.util.control.TailCalls._ ============
  println("\nHow to define a tail recursive function using scala.util.control.TailCalls._")
  import scala.util.control.TailCalls._
  def tailSearch(donutName: String, donuts: Array[String], index: Int): TailRec[Option[Boolean]] = {
    if(donuts.length == index) done(None)
    else if(donuts(index) == donutName) done(Some(true))
    else tailcall(tailSearch(donutName, donuts, index +1))
  }

  println("\nStep 4: How to call tail recursive function using scala.util.control.TailCalls._")
  println(s"Find Glazed Donut using TailCall = ${tailcall(tailSearch("Glazed Donut", donutsArray, 0)).result}")
  println(s"Find Chocolate Donut using TailCall = ${tailcall(tailSearch("Chocolate Donut", donutsArray, 0)).result}")


  // ============ Create Trampoline Tail Recursive Function Using scala.util.control.TailCalls._ ============
  println("\nStep 1: How to define a trampoline function using scala.util.control.TailCalls")
  def verySweetDonut(donutList: List[String]): TailRec[Boolean] ={
    println(s"verySweetDonut function: donut list = $donutList")
    if(donutList.isEmpty){
      println("verySweetDonut function: donutLis is empty, returning false")
      done(false)
    }else{
      if(Set("Vanilla Donut","Strawberry Donut","Glazed Donut").contains(donutList.head)) {
        println(s"verySweetDonut function: found donut list's head = ${donutList.head} to be VERY sweet, returning true")
        done(true)
      }else{
        println(s"verySweetDonut function: donut list's head = ${donutList.head} is not very sweet, forwarding donut list to notSweetDonut function")
        tailcall(notSweetDonut(donutList))
      }
    }
  }

  println("\nStep 2: How to define a trampoline function using scala.util.control.TailCalls")
  def notSweetDonut(donutList: List[String]): TailRec[Boolean] = {
    println(s"notSweetDonut function: with donut list = $donutList")
    if(donutList.isEmpty){
      println("notSweetDonut function: donut list isEmpty, returning false")
      done(false)
    }else{
      println(s"notSweetDonut function: donut list's head = ${donutList.head} is NOT sweet, forwarding donut list's tail to verySweetDonut function")
      tailcall(verySweetDonut(donutList.tail))
    }
  }

  println("\nStep 3: How to call a trampoline tail recursive function")
  val donutList: List[String] = List("Plain Donut", "Strawberry Donut", "Plain Donut", "Glazed Donut")
  println(s"\nFound very sweet donut = ${tailcall(verySweetDonut(donutList)).result}")


  // ============ Create Partial Function Using the PartialFunction Trait ============
  println("\nStep 2: How to define a Partial Function named isVeryTasty")
  val isVeryTasty: PartialFunction[String, String]= {
    case "Glazed Donut" | "Strawberry Donut" => "Very Tasty"
  }
  println("\nStep 3: How to call the Partial Function named isVeryTasty")
  println(s"Calling partial function isVeryTasty = ${isVeryTasty("Glazed Donut")}")

  println("\nStep 4: How to define Partial Function named isTasty and unknownTaste")
  val isTasty: PartialFunction[String, String]={
    case "Plain Donut" => "Tasty"
  }

  val unknownTaste: PartialFunction[String, String]={
    case donut @ _ => s"Unknown taste for donut = $donut"
  }

  println("\nStep 5: How to compose Partial Function using orElse")
  val donutTaste = isVeryTasty orElse isTasty orElse unknownTaste
  println(donutTaste("Glazed Donut"))
  println(donutTaste("Plain Donut"))
  println(donutTaste("Chocolate Donut"))


  // ============ Create Nested Function ============
  println("Step 1: How to define a function")
  def checkDonutAvailability(donutName: String): Boolean = {
    // retrieve donut list that is currently in stock
    val listDonutsFromStock: List[String] = List("Vanilla Donut", "Strawberry Donut", "Plain Donut", "Glazed Donut")

    val iDonutInStock = (donutName.nonEmpty && donutName.length > 0) && (listDonutsFromStock contains donutName)

    iDonutInStock
  }

  println("\nStep 2: How to call a function")
  println(s"Calling checkDonutAvailability with Vanilla Donut = ${checkDonutAvailability("Vanilla Donut")}")

  println("\nStep 3: How to define a Nested Function")
  def checkDonutAvailabilityWithNestedFunction(donutName: String): Boolean = {
    // retrieve donut list that is currently in stock
    val listDonutsFromStock = List[String]("Vanilla Donut", "Strawberry Donut", "Plain Donut", "Glazed Donut")

    // validate the donutName parameter by some business logic
    val validate = (name: String) => {
      name.nonEmpty && name.length > 0
    }

    // first run validate and then check if we have a matching donut from our list
    val isDonutInStock = validate(donutName) && (listDonutsFromStock contains donutName)

    isDonutInStock
  }

  println("\nStep 4: How to call a Nested Function")
  println(s"Calling checkDonutAvailabilityWithNestedFunction with Vanilla Donut = ${checkDonutAvailabilityWithNestedFunction("Vanilla Donut")}")

}
