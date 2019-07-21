package hello.scala.allaboutscala.chapter9

import java.util.concurrent.{Executor, Executors}

import scala.concurrent.{Await, ExecutionContext, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

object Chapter9 extends App {

  /*// ============ Scala Future ============
  println("This is start")

  val f = Future{
    println("Printing in future")
  }
  Thread.sleep(1)
  println("This is end")

  Thread.sleep(100)*/


  // ============ Method with future as return type ============
  //println("Step 1: Define a method which returns a Future")
  def donutStock(donut : String) : Future[Int] = Future{
    println(s"Checking stock availability for donut = $donut")
    10
  }

  /*println("\nStep 2: Call method which returns a Future")
  import scala.concurrent.duration._
  val vanillaDonutStock = Await.result(donutStock("Vanilla Donut"), 5 seconds)
  println(s"Stock of vanilla donuts = $vanillaDonutStock")*/


  // ============ Non blocking future result ============
  /*println("\nStep 2: Non blocking future result")
  donutStock("Vanilla Donut").onComplete{
    case Success(stock) => println(s"Stock for vanilla donut = $stock")
    case Failure(ex) => println(s"Something wrong fetching stock, exception = $ex")
  }*/


  // ============ Chain futures using flatMap() ============
  //println("\nStep 2: Define another method which returns a Future")
  def buyDonuts(quantity: Int): Future[Boolean] = Future {
    println(s"buying $quantity donuts")
    true
  }

  /*import scala.concurrent.duration._
  val buyingDonuts : Future[Boolean] = donutStock("Vanilla Donut").flatMap(qty => buyDonuts(qty))
  //val isSuccess = Await.result(buyingDonuts, 5 seconds)
  //println(s"Buying vanilla donuts was successful = $isSuccess")
  Thread.sleep(200)
  println(s"Buying vanilla donuts was successful = ${buyingDonuts.value.get.get}")*/
  /*val buyingDonuts = donutStock("Vanilla Donut").map(qty => buyDonuts(qty))
  println(s"Buying vanilla donuts was successful = ${buyingDonuts}") // returns Future(Success(Future(Success(true))))*/


  // ============ Chain futures using for comprehension ============
  /*println("\nStep 3: Chaining Futures using for comprehension")
  for {qty <- donutStock("Vanilla Donut")
       isSuccess <- buyDonuts(qty)
  }yield println(s"Buyiing vanilla donut was successful = $isSuccess")

  Thread.sleep(200)*/


  // ============ Future Option with for comprehension ============
  //println("Step 1: Define a method which returns a Future Option")
  def donutStockOption(donut: String): Future[Option[Int]] = Future {
    // assume some long running database operation
    println("checking donut stock")
    if(donut == "vanilla donut") Some(10) else None
  }
  /*println("\nStep 3: Chaining Future Option using for comprehension")
  for {
    someStock  <- donutStockOption("vanilla donut")
    isSuccess  <- buyDonuts(someStock.getOrElse(0))
  } yield println(s"Buying vanilla donut was successful = $isSuccess")

  Thread.sleep(200)
*/

  // ============ Future Option with map ============
  /*println(s"\nStep 2: Access value returned by future using map() method")
  donutStockOption("vanilla donut")
    .map(qty => println(s"Buying ${qty.getOrElse(0)} vanilla donuts"))

  donutStock("vanilla donut")
    .map(qty => println(s"Buying ${qty} vanilla donuts"))

  Thread.sleep(200)*/


  // ============ Composing Futures ============
  /*println(s"\nStep 3: Calling map() method over multiple futures")
  val resultFromMap: Future[Future[Boolean]] = donutStockOption("vanilla donut")
    .map(someQty => buyDonuts(someQty.getOrElse(0)))
  Thread.sleep(1000)

  println(s"\nStep 4: Calling flatMap() method over multiple futures")
  val resultFromFlatMap: Future[Boolean] = donutStockOption("vanilla donut")
    .flatMap(someQty => buyDonuts(someQty.getOrElse(0)))
  Thread.sleep(1000)*/


  // ============ Future Sequence ============
  /*//println("Step 1: Define a method which returns a Future Option of Int")
  def donutStockOptionWithSleep(donut: String): Future[Option[Int]] = Future {
    println("checking donut stock ... sleep for 2 minutes")
    Thread.sleep(2000)
    if(donut == "vanilla donut") Some(10) else None
  }

  //println("\nStep 2: Define another method which returns a Future[Boolean]")
  def buyDonutsWithSlepp(quantity : Int) : Future[Boolean] = Future{
    println(s"Buying $quantity donuts ... sleeps for 3 minutes")
    if(quantity > 0) true else false
  }

  //println("\nStep 3: Define another method for processing payments and returns a Future[Unit]")
  def processPayment() : Future[Unit] = Future{
    println("Processing payment ... sleeps for 1 minute")
  }

  //println("\nStep 4: Combine future operations into a list")
  val futureOperations : List[Future[Any]] = List(donutStockOptionWithSleep("vanilla donut"), buyDonutsWithSlepp(10), processPayment)

  //println(s"\nStep 5: Call Future.sequence to run the future operations in parallel")
  val futureSequenceResults : Future[List[Any]] = Future.sequence(futureOperations)
  futureSequenceResults.onComplete{
    case Success(results) => println(s"Results $results")
    case Failure(ex) => println(s"Failure $ex")
  }

  Thread.sleep(7000)*/


  // ============ Future Traverse ============
  //println(s"\nStep 2: Create a List of future operations")
  val futureOperations4Traverse = List(
    donutStockOption("vanilla donut"),
    donutStockOption("plain donut"),
    donutStockOption("chocolate donut")
  )

  /*val futureOperations4TraverseResults1 = Future.traverse(futureOperations4Traverse) {someDonutQty => someDonutQty.map(qty => qty)}// Future[List[Option[Int]]]
  val futureOperations4TraverseResults2 = Future.traverse(futureOperations4Traverse){     // Future[List[Int]]
    someDonutQty => someDonutQty.map(qty => qty.getOrElse(0))
  }
  futureOperations4TraverseResults1.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  Thread.sleep(1000)
*/

  // ============ Future foldLeft ============
  /*println(s"\nStep 2: Use a List of future operations = $futureOperations4Traverse")

  println(s"\nStep 3: Call Future.foldLeft to fold over futures results from left to right")
  //val futureFoldLeft = Future.foldLeft(futureOperations4Traverse)(0){case(acc, someQty) => acc + someQty.getOrElse(0)}
  val futureFoldLeft = Future.foldLeft(futureOperations4Traverse)(0){(acc, someQty) =>
  { println(someQty)
    acc + someQty.getOrElse(0)}
  }

  futureFoldLeft.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  Thread.sleep(200)*/


  // ============ Future reduceLeft ============
  /*println(s"\nStep 3: Call Future.reduceLeft to fold over futures results from left to right")
  val futureReduceLeft : Future[Option[Int]]= Future.reduceLeft(futureOperations4Traverse){ case (acc, someQty) =>
    acc.map(qty => qty + someQty.getOrElse(0))
  }
  futureReduceLeft.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  Thread.sleep(200)*/


  // ============ Future firstCompletedOf ============
  /*println(s"\nStep 3: Call Future.firstCompletedOf to get the results of the first future that completes")
  val futureFirstCompletedResult = Future.firstCompletedOf(futureOperations4Traverse)
  futureFirstCompletedResult.onComplete {
    case Success(results) => println(s"Results $results")
    case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
  }

  Thread.sleep(200)
*/

  // ============ Future zip ============
  //println(s"\nStep 2: Define a method which returns a Future Double for donut price")
  def donutPrice() : Future[Double] = Future.successful(31.5)

  /*val donutStockAndPriceOperation : Future[(Option[Int] , Double)] = donutStockOption("vanilla donut") zip donutPrice()
  donutStockAndPriceOperation.onComplete{
    case Success(results) => println(s"Results = $results")
    case Failure(ex) => println(s"Somrthing wrong, exception = $ex")
  }

  Thread.sleep(200)*/


  // ============ Future zipWith ============
 /* println(s"\nStep 3: Define a value function to convert Tuple (Option[Int], Double) to Tuple (Int, Double)")
  val qtyANdPriceF:(Option[Int], Double) => (Int, Double) = (someQty,price) => (someQty.getOrElse(0), price)
  println(s"\nStep 4: Call Future.zipWith and pass-through function qtyAndPriceF")
  val donutStockAndPriceOperationZipWith = donutStockOption("vanilla donut").zipWith(donutPrice())(qtyANdPriceF)

  donutStockAndPriceOperationZipWith.onComplete{
    case Success(results) => println(s"Results = $results")
    case Failure(ex) => println(s"Something wrong, exception= $ex")
  }
  Thread.sleep(200)
*/

  // ============ Future andThen ============
  /*println(s"\nStep 2: Call Future.andThen with a PartialFunction")
  val donutStockOperationAndThen = donutStockOption("vanilla donut").andThen{case Success(qty) => /*qty.getOrElse(0)}*/println(s"Donut stock qty = $qty")}
  println(s"Donut stock with andThen = $donutStockOperationAndThen")

  Thread.sleep(2000)*/


  // ============ Future configure threadpool ============
  /*println("Step 1: Define an ExecutionContext")
  val executor = Executors.newSingleThreadExecutor()
  implicit val ec = ExecutionContext.fromExecutor(executor)

  println("\nStep 3: Call method which returns a Future")
  val donutStockOperation = donutStockOption("vanilla donut")
  donutStockOperation.onComplete{
    case Success(results) => println(s"Results = $results")
    case Failure(ex) => println(s"Something wrong, exception = $ex")
  }

  Thread.sleep(200)
  executor.shutdown()*/


  // ============ Future recover ============
  println("Step 1: Define a method which returns a Future")
  def donutStockWithException(donut : String) : Future[Int] = Future{
    if (donut == "vanilla donut") 10
    else throw new IllegalStateException("out of stock")
  }

  println("\nStep 2: Execute donutStock() future operation")
  donutStockWithException("unknown vanilla donut")
    .onComplete{
      case Success(results) => println(s"Results = $results")
      case Failure(ex) => println(s"Something wrong, exception = $ex")
    }

  /*donutStockWithExecption("unknown vanilla donut")
    .recover{case ex: IllegalStateException if ex.getMessage == "out of stock" =>
      println(s"In recover callback for exception = $ex")
      0
    }
    .onComplete{
      case Success(results) => println(s"Results with recover callback = $results")
      case Failure(ex) => println(s"Something wrong, exception = $ex")
    }

  Thread.sleep(290)*/


  // ============ Future recoverWith ============
  /*donutStockWithExecption("unknown donut")
    .recoverWith{case ex: IllegalStateException if ex.getMessage == "out of stock" =>
      println(s"In recoverWith callback for exception = $ex")
      Future.successful(0)}
    .onComplete{
      case Success(results) => println(s"Results with recoverWith callback = $results")
      case Failure(ex) => println(s"Something wrong, exception = $ex")
    }

  Thread.sleep(290)*/


  // ============ Future fallbackTo ============
  /*println("\nStep 2: Define another method which returns a Future to match a similar donut stock")
  def backupDonutStock(donut: String): Future[Int] = Future {
    println(s"replacing donut stock from a similar donut = $donut")
    if(donut == "vanilla donut") 20 else 5
  }

  println("\nStep 3: Call Future.fallbackTo")
  donutStockWithExecption("plain donut")
    .fallbackTo(backupDonutStock("vanilla donut"))
    .onComplete{
      case Success(results) => println(s"Results = $results")
      case Failure(ex) => println(s"Something seriously wrong, exception = $ex")
    }

  Thread.sleep(280)*/


  // ============ Future promise ============
  println("Step 1: Define a method which returns an Int instead of a Future")
  def donutStockGen(donut: String): Int = {
    if (donut == "vanilla donut") 10
    else throw new IllegalStateException("out of stock")
  }

  println(s"\nStep 2: Define a Promise of type Int")
  val donutStockPromise = Promise[Int]()

  println("\nStep 3: Define a future from Promise")
  val donutStockPromiseFuture = donutStockPromise.future
  donutStockPromiseFuture.onComplete{
    case Success(stock) => println(s"stock for vanilla donut =$stock")
    case Failure(ex) => println(s"something wrong, exception = $ex")
  }

  println("\nStep 4: Use Promise.success or Promise.failure to control execution of your future")
  val donut = "vanilla donut"

  if(donut == "vanilla donut")
    donutStockPromise.success(donutStockGen(donut))
  else
    donutStockPromise.failure(Try(donutStockGen(donut)).failed.get)

  println("\nStep 5: Completing Promise using Promise.complete() method")
  val donutStockPromise2 = Promise[Int]()
  val donutStockPromiseFuture2 = donutStockPromise2.future
  donutStockPromiseFuture2.onComplete {
    case Success(stock) => println(s"Stock for vanilla donut = $stock")
    case Failure(e)     => println(s"Failed to find vanilla donut stock, exception = $e")
  }
  donutStockPromise2.complete(Try(donutStockGen("unknown donut")))

}
