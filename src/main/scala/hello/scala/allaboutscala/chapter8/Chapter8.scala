package hello.scala.allaboutscala.chapter8

object Chapter8 extends App {
  // =============  To Use Aggregate Function  =============
  /* def aggregate[B](z: =>B)(seqop: (B, A) => B, combop: (B, B) => B): B = foldLeft(z)(seqop) */
  println("Step 1: How to initialize a Set of type String to represent Donut elements")
  val donutBasket1: Set[String] = Set("Plain Donut", "Strawberry Donut")
  println(s"Elements of donutBasket1 = $donutBasket1")

  println("\nStep 2: How to define an accumulator function to calculate the total length of the String elements")
  val donutLengthAccumulator :(Int, String) => Int = ((acc, donutName) => acc + donutName.length)

  println("\nStep 3: How to call aggregate function with the accumulator function from Step 2")
  //val totalLength = donutBasket1.aggregate(0)(donutLengthAccumulator, _ + _)
  val totalLength = donutBasket1.aggregate(0)(((acc: Int, donut: String) => donutLengthAccumulator(acc, donut)), _+_)
  println(s"Total length of elements in donutBasket1 = $totalLength")

  println("\nStep 4: How to initialize a Set of Tuple3 elements to represent Donut name, price and quantity")
  val donutBasket2: Set[(String, Double, Int)] = Set(("Plain Donut", 1.50, 10), ("Strawberry Donut", 2.0, 10))
  println(s"Elements of donutBasket2 = $donutBasket2")

  println("\nStep 5: How to define an accumulator function to calculate the total cost of Donuts")
  val totalCostAccumulator: (Double, Double, Int) => Double = (accumulator, price, quantity) => accumulator + (price * quantity)

  println("\nStep 6: How to call aggregate function with accumulator function from Step 5")
  val totalCost = donutBasket2.aggregate(0.0)((accumulator: Double, tuple: (String, Double, Int)) => totalCostAccumulator(accumulator, tuple._2, tuple._3), _ + _)
  println(s"Total cost of donuts in donutBasket2 = $totalCost")


  // =============  To Use Collect Function  =============
  /* def collect[B](pf: PartialFunction[A, B]): Traversable[B] */
  println("Step 1: How to initialize a Sequence which contains donut names and prices")
  val donutNamesAndPrices: Seq[Any] = Seq("Plain Donut", 1.5, "Strawberry Donut", 2.0, "Glazed Donut", 2.5)
  println(s"Elements of donutNamesAndPrices = $donutNamesAndPrices")

  println("\nStep 2: How to use collect function to cherry pick all the donut names")
  val donutNames: Seq[Any] =donutNamesAndPrices.collect{case d:String => d}
  println(s"Elements of donutNames = $donutNames")

  println("\nStep 3: How to use collect function to cherry pick all the donut prices")
  val donutPrices: Seq[Double] = donutNamesAndPrices.collect{ case price: Double => price }
  println(s"Elements of donutPrices = $donutPrices")


  // =============  To Use Diff Function  =============
  println("\nStep 1: How to initialize a Set containing 3 donuts")
  val donutBasket21: Set[String] = Set("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donutBasket21 = $donutBasket21")

  println("\nStep 2: How to initialize a Set containing 2 donuts")
  val donutBasket22: Set[String] = Set("Glazed Donut", "Vanilla Donut")
  println(s"Elements of donutBasket22 = $donutBasket22")

  println("\nStep 3: How to find the difference between two Sets using the diff function")
  val diffDonutBasket21From22: Set[String] = donutBasket21 diff donutBasket22
  println(s"Elements of diffDonutBasket1From2 = $diffDonutBasket21From22")

  println("\nStep 4: How to find the difference between two Sets using the diff function")
  val diffDonutBasket22From21: Set[String] = donutBasket22 diff donutBasket21
  println(s"Elements of diff DonutBasket2From1 = $diffDonutBasket22From21")

  println("\nStep 5: How to find the difference between two Sets using the --")
  println(s"Difference between donutBasket1 and donutBasket2 = ${donutBasket21 -- donutBasket22}")
  println(s"Difference between donutBasket2 and donutBasket1 = ${donutBasket22 -- donutBasket21}")


  // =============  To Use drop Function  =============
  /* def drop(n: Int): Repr  */
  println("Step 1: How to initialize a Sequence of donuts")
  val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 2: How to drop the first element using drop function")
  println(s"Drop the first element in the sequence = ${donuts.drop(1)}")

  println("\nStep 3: How to drop the first two elements using the drop function")
  println(s"Drop the first and second elements in the sequence = ${donuts.drop(2)}")


  // =============  To Use dropWhile Function  =============
  /* def dropWhile(p: (A) ⇒ Boolean): Repr */
  println("Step 1: How to initialize a Sequence of donuts2")
  val donuts2: Seq[String] = Seq("Plain Donut 1", "Plain Donut 2", "Strawberry Donut", "Plain Donut 3", "Glazed Donut")
  println(s"Elements of donuts = $donuts2")

  println("\nStep 2: How to drop elements from the sequence using the dropWhile function")
  println(s"Drop donut elements whose name starts with letter P = ${donuts2.dropWhile(_.charAt(0) == 'P')}")

  println("\nStep 3: How to declare a predicate function to be passed-through to the dropWhile function")
  val dropElementsPredicate: (String) => Boolean = (donutName) => donutName.charAt(0) == 'P'
  println(s"Value function dropElementsPredicate = $dropElementsPredicate")

  println("\nStep 4: How to drop elements using the predicate function from Step 3")
  println(s"Drop elements using function from Step 3 = ${donuts2.dropWhile(dropElementsPredicate)}")


  // =============  To Use exists Function  =============
  /* def exists(p: (A) ⇒ Boolean): Boolean */
  println("Step 1: How to initialize a Sequence of donuts")
  val donuts3: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts3")

  println("\nStep 2: How to check if a particular element exists in the sequence using the exists function")
  val doesPlainDonutExists: Boolean = donuts3.exists(donutName => donutName == "Plain Donut")
  println(s"Does Plain Donut exists = $doesPlainDonutExists")

  println("\nStep 3: How to declare a predicate value function for the exists function")
  val plainDonutPredicate: (String) => Boolean = (donutName) => donutName == "Plain Donut"
  println(s"Value function plainDonutPredicate = $plainDonutPredicate")

  println("\nStep 4: How to find element Plain Donut using the exists function and passing through the predicate function from Step 3")
  println(s"Does Plain Donut exists = ${donuts3.exists(plainDonutPredicate)}")

  println("\nStep 5: How to declare a predicate def function for the exists function")
  def plainDonutPredicateFunction(donutName : String) = donutName == "Plain Donut"

  println("\nStep 6: How to find element Plain Donut using the exists function and passing through the predicate function from Step 5")
  println(s"Does plain Donut exists = ${donuts3.exists(plainDonutPredicateFunction(_))}")


  // =============  To Use filter & filterNot Function  =============
  /* def filter(p: (A) ⇒ Boolean): Repr
  * def filterNot(p: (A) ⇒ Boolean): Repr */

  println("Step 1: How to initialize a Sequence of donuts")
  val donuts4: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut", "Vanilla Donut")
  println(s"Elements of donuts = $donuts4")

  println("\nStep 2: How to keep only Plain and Glazed Donuts using the filter method")
  val sequenceWithPlainAndGlazedDonut = donuts4.filter { donutName =>
    donutName.contains("Plain") || donutName.contains("Glazed")
  }
  println(s"Sequence with Plain and Glazed donuts only = $sequenceWithPlainAndGlazedDonut")

  println("\nStep 3: How to filter out element Vanilla Donut using the filterNot function")
  val sequenceWithoutVanillaDonut = donuts4.filterNot(donutName => donutName == "Vanilla Donut" )
  println(s"Sequence without vanilla donut = $sequenceWithoutVanillaDonut")


  // =============  To Use find Function  =============
  /* def find(p: (A) ⇒ Boolean): Option[A] */
  println("Step 1: How to initialize a Sequence of donuts")
  val donuts5: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts5")

  println("\nStep 2: How to find a particular element in the sequence using the find function")
  val plainDonut: Option[String] = donuts5.find(donutName => donutName == "Plain Donut")
  println(s"Find Plain Donut = ${plainDonut.get}")

  println("\nStep 3: How to find element Vanilla Donut which does not exist in the sequence using the find function")
  //val vanillaDonut: String = donuts5.find(_ == "Vanilla Donut").get
  //println(s"Find Vanilla Donuts = $vanillaDonut")

  println("\nStep 4: How to find element Vanilla Donut using the find function and getOrElse")
  val vanillaDonut2: String = donuts5.find(_ == "Vanilla Donut").getOrElse("Vanilla Donut was not found!")
  println(s"Find Vanilla Donuts = $vanillaDonut2")


  // =============  To Use flatMap Function  =============
  /* def flatMap[B](f: (A) ⇒ GenTraversableOnce[B]): TraversableOnce[B] */
  println("Step 1: How to initialize a Sequence of donuts")
  val donuts61: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts1 = $donuts61")

  println("\nStep 2: How to initialize another Sequence of donuts")
  val donuts62: Seq[String] = Seq("Vanilla Donut", "Glazed Donut")
  println(s"Elements of donuts2 = $donuts62")

  println("\nStep 3: How to create a List of donuts initialized using the two Sequences from Step 1 and Step 2")
  val listDonuts: List[Seq[String]] = List(donuts61, donuts62)
  println(s"Elements of listDonuts = $listDonuts")

  println("\nStep 4: How to return a single list of donut using the flatMap function")
  val listDonutsFromFlatMap: List[String] = listDonuts.flatMap(seq => seq)
  println(s"Elements of listDonutsFromFlatMap as a flatMap as a single list = $listDonutsFromFlatMap")


  // =============  To Use flatten Function  =============
  /* def flatten[B]: Traversable[B] */

  println("Step 1: How to initialize a Sequence of donuts")
  val donuts71: Seq[String] = Seq("Plain", "Strawberry", "Glazed")
  println(s"Elements of donuts1 = $donuts71")

  println("\nStep 2: How to initialize another Sequence of donuts")
  val donuts72: Seq[String] = Seq("Vanilla", "Glazed")
  println(s"Elements of donuts2 = $donuts72")

  println("\nStep 3: How to create a List of donuts initialized using the two Sequences from Step 1 and Step 2")
  val listDonuts7: List[Seq[String]] = List(donuts71, donuts72)
  println(s"Elements of listDonuts = $listDonuts7")

  println("\nStep 4: How to return a single list of donut using the flatten function")
  val listDonutsFromFlatten: List[String] = listDonuts7.flatten
  println(s"Elements of listDonutsFromFlatten = $listDonutsFromFlatten")

  println("\nStep 5: How to append the word Donut to each element of listDonuts using flatten and map functions")
  val listDonutsFromFlatten2: List[String] = listDonuts.flatten.map(_ + " Donut")
  println(s"Elements of listDonutsFromFlatten2 = $listDonutsFromFlatten2")


  // =============  To Use fold Function  =============
  /* def fold[A1 >: A](z: A1)(op: (A1, A1) ⇒ A1): A1 */
  println("Step 1: How to initialize a sequence of donut prices")
  val prices: Seq[Double] = Seq(1.5, 2.0, 2.5)
  println(s"Donut prices = $prices")

  println("\nStep 2: How to sum all the donut prices using fold function")
  val sum = prices.fold(0.0)(_ + _)
  //val sum = prices.fold("Amen")(_.toString + _.toString)
  println(s"Sum = $sum")

  println("\nStep 3: How to initialize a Sequence of donuts")
  val donuts8: Seq[String] = Seq("Plain", "Strawberry", "Glazed")
  println(s"Elements of donuts1 = $donuts8")

  println("\nStep 4: How to create a String of all donuts using fold function")
  //val allDonuts = donuts8.fold("")((acc, element) => acc+element+" Donut")
  println(s"All donuts = ${donuts8.fold("")((acc, element) => acc+element+" Donut ")}")

  println("\nStep 5: How to declare a value function to create the donut string")
  val concatDonuts: (String, String) => String = (s1, s2) => s1 + s2 + " Donut "
  println(s"Value function concatDonuts = $concatDonuts")

  println("\nStep 6: How to create a String of all donuts using value function from Step 5 and fold function")
  println(s"All donuts = ${donuts8.fold("")(concatDonuts)}")


  // =============  To Use foldLeft Function  =============
  /* def foldLeft[B](z: B)(op: (B, A) ⇒ B): B */
  println("Step 1: How to initialize a sequence of donut prices")
  val prices9: Seq[Double] = Seq(1.5, 2.0, 2.5)
  println(s"Donut prices = $prices9")

  println("\nStep 2: How to sum all the donut prices using foldLeft function")
  val sum9 = prices9.foldLeft(0.0)(_ + _)
  println(s"Sum = $sum9")

  println("\nStep 3: How to initialize a Sequence of donuts")
  val donuts9: Seq[String] = Seq("Plain", "Strawberry", "Glazed")
  println(s"Elements of donuts1 = $donuts9")

  println("\nStep 4: How to create a String of all donuts using foldLeft function")
  println(s"All donuts = ${donuts9.foldLeft("")((acc, b) => acc + b + " Donut ")}")

  println("\nStep 5: How to declare a value function to create the donut string")
  import scala.collection.mutable.ListBuffer
  val concatDonuts9: (ListBuffer[String], String) => ListBuffer[String] = (accList, b) => accList += b
  println(s"Value function concatDonuts = $concatDonuts9")

  println("\nStep 6: How to create a String of all donuts using value function from Step 5 and foldLeft function")
  println(s"All donuts = ${donuts9.foldLeft(ListBuffer.empty[String])(concatDonuts9)}")


  // =============  To Use foldRight Function  =============
  /* def foldRight[B](z: B)(op: (A, B) ⇒ B): B */
  println("Step 1: How to initialize a sequence of donut prices")
  val prices10: Seq[Double] = Seq(1.5, 2.0, 2.5)
  println(s"Donut prices = $prices10")

  println("\nStep 2: How to sum all the donut prices using foldRight function")
  val sum10 = prices10.foldRight(0.0)(_+_)
  println(s"Sum10 = $sum10")

  println("\nStep 3: How to initialize a Sequence of donuts")
  val donuts10: Seq[String] = Seq("Plain", "Strawberry", "Glazed")
  println(s"Elements of donuts1 = $donuts10")

  println("\nStep 4: How to create a String of all donuts using foldRight function")
  println(s"All donuts = ${donuts10.foldRight("")((b, acc) => b + " Donut " + acc)}")

  println(s"\nStep 5: How to create a value function to create donut list")
  val concatDonuts10:(String, ListBuffer[String]) => ListBuffer[String] = (b, acc) => acc += (b+" Donut")
  println(s"Value function concatDonuts = $concatDonuts10")

  println("\nStep 6: How to create a List of all donuts using value function from Step 5 and foldRight function")
  println(s"All donuts = ${donuts10.foldRight(ListBuffer.empty[String])(concatDonuts10)}")



  // =============  To Use foreach Function  =============
  /*def foreach(f: (A) ⇒ Unit): Unit*/
  println("Step 1: How to initialize a Sequence of donuts")
  val donuts11: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts11")

  println("\nStep 2: How to loop through all the elements in the sequence using the foreach function")
  donuts11.foreach(println(_))

  println("\nStep 3: How to loop through and access all the elements in the sequence using the foreach function")
  donuts11.foreach(donutName => println(s"donutName = $donutName"))

  println("\nStep 4: How to declare a value function to format a donut names into upper case format")
  val uppercase: (String) => String = (s) => {
    val upper = s.toUpperCase
    println(upper)
    upper
  }
  println(s"Value function formatting donut names to uppercase = $uppercase")

  println("\nStep 5: How to format all donuts to uppercase using value function from Step 4")
  donuts11.foreach(uppercase)


  // =============  To Use groupBy Function  =============
  /*groupBy[K](f: (A) ⇒ K): immutable.Map[K, Repr]*/
  println("Step 1: How to initialize a Sequence of donuts")
  val donuts12: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts12")

  println("\nStep 2: How to group elements in a sequence using the groupBy function")
  val donutsGroup: Map[Char, Seq[String]] = donuts12.groupBy(_.charAt(0))
  println(s"Group elements in the donut sequence by the first letter of the donut name = $donutsGroup")

  println("\nStep 3: How to create a case class to represent Donut objects")
  case class Donut(name: String, price: Double)

  println("\nStep 4: How to create a Sequence of type Donut")
  val donuts121: Seq[Donut] = Seq(Donut("Plain Donut", 1.5), Donut("Strawberry Donut", 2.0), Donut("Glazed Donut", 2.5))
  println(s"Elements of donuts2 = $donuts121")

  println(s"\nStep 5: How to group case classes donut objects by the name property")
  val donutsGroup2 : Map[String, Seq[Donut]] = donuts121.groupBy(_.name)
  println(s"Group element in the sequence of type Donut grouped by the donut name = $donutsGroup2")


  // =============  To Use head Function  =============
  /*def head: A*/
  println("Step 1: How to initialize a Sequence of donuts")
  val donuts13: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts13")

  println("\nStep 2: How to access the first element of the donut sequence")
  println(s"First element of donut sequence = ${donuts13(0)}")

  println("\nStep 3: How to access the first element of the donut sequence using the head method")
  println(s"First element of donut sequence using head method = ${donuts13.head}")

  println("\nStep 4: How to create an empty sequence")
  val donuts131: Seq[String] = Seq.empty[String]
  println(s"Elements of donuts2 = $donuts131")

  //println(s"First element of empty sequence = ${donuts131.head}")
  println("\nStep 5: How to access the first element of the donut sequence using the headOption function")
  println(s"First element of empty sequence = ${donuts131.headOption.getOrElse("No donut was found")}")


  // =============  To Use isEmpty Function  =============
  /*abstract def isEmpty: Boolean*/
  println("Step 1: How to initialize a Sequence of donuts")
  val donuts14: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts14")

  println("\nStep 2: How to find out if a sequence is empty using isEmpty function")
  println(s"Is donuts sequence empty = ${donuts14.isEmpty}")

  println("\nStep 4: How to find out if a sequence is empty using isEmpty function")
  println(s"Is donuts2 sequence empty = ${donuts131.isEmpty}")


  // =============  To Use intersect Function  =============
  /*def intersect(that: GenSet[A]): Repr*/
  println("Step 1: How to initialize a Set of donuts")
  val donuts15: Set[String] = Set("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts1 = $donuts15")

  println("\nStep 2: How to initialize another Set of donuts")
  val donuts152: Set[String] = Set("Plain Donut", "Chocolate Donut", "Vanilla Donut")
  println(s"Elements of donuts2 = $donuts152")

  println("\nStep 3: How to find the common elements between two Sets using intersect function")
  println(s"Common elements between donuts1 and donuts2 = ${donuts15 intersect donuts152}")
  println(s"Common elements between donuts2 and donuts1 = ${donuts152 intersect donuts15}")

  println("\nStep 4: How to find the common elements between two Sets using & function")
  println(s"Common elements between donuts1 and donuts2 = ${donuts15 & donuts152}")
  println(s"Common elements between donuts2 and donuts1 = ${donuts152 & donuts15}")


  // =============  To Use last Function  =============
  /*def last: A*/
  println("Step 1: How to initialize a Sequence of donuts")
  val donuts16: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts16")

  println("\nStep 2: How to access the last element of the donut sequence by index")
  println(s"Last element of donut sequence = ${donuts16(donuts16.size - 1)}")

  println("\nStep 3: How to access the last element of the donut sequence by using the last function")
  println(s"Last element of donut sequence = ${donuts16.last}")

  println("\nStep 5: How to access the last element of the donut sequence using the lastOption function")
  println(s"Last element of empty sequence = ${donuts131.lastOption.getOrElse("No donut was found!")}")


  // =============  To Use map Function  =============
  /*def map[B](f: (A) ⇒ B): Traversable[B]*/
  println("Step 1: How to initialize a Sequence of donuts")
  val donuts17: Seq[String] = Seq("Plain", "Strawberry", "Glazed")
  println(s"Elements of donuts1 = $donuts17")

  println("\nStep 2: How to append the word Donut to each element using the map function")
  val donuts172: Seq[String] = donuts17.map(_ + " Donut")
  println(s"Elements of donuts2 = $donuts172")

  println("\nStep 3: How to create a donut sequence with one None element")
  val donuts173: Seq[AnyRef] = Seq("Plain", "Strawberry", None)
  donuts173.foreach(println(_))

  println("\nStep 4: How to filter out the None element using map function")
  val donuts174: Seq[String] = donuts173.map {
    case donut: String => donut + " Donut"
    case None => "Unknown Donut"
  }
  println(s"Elements of donuts4 = $donuts174")

  println("\nStep 5: How to define couple of functions which returns an Option of type String")
  def favoriteDonut: Option[String] = Some("Glazed Donut")
  def leastFavoriteDonut: Option[String] = None
  println("\nStep 6: How to use map function to filter out None values")
  favoriteDonut.map(donut => println(s"Favorite donut = $donut"))
  leastFavoriteDonut.map(donut=> println(s"Least favorite donut = $donut"))


  // =============  To Use max Function  =============
  /*def max: A*/
  println("\nStep 2: How to find the maximum element in the sequence using the max function")
  println(s"Max element in the donuts sequence = ${donuts17.max}")

  println("\nStep 4: How to find the maximum element in the sequence using the max function")
  println(s"Max element in the donut prices sequence = ${prices.max}")


  // =============  To Use maxBy Function  =============
  /*def maxBy[B](f: (A) ⇒ B): A*/
  println("\nStep 2: How to create a Sequence of type Donut")
  val donuts4MaxBy: Seq[Donut] = Seq(Donut("Plain Donut", 1.5), Donut("Strawberry Donut", 2.0), Donut("Glazed Donut", 2.5))
  println(s"Elements of donuts = $donuts4MaxBy")

  println("\nStep 3: How to find the maximum element in a sequence of case classes objects using the maxBy function")
  println(s"Maximum element in sequence of case class of type Donut, ordered by price = ${donuts4MaxBy.maxBy(donut => donut.price)}")

  println("\nStep 4: How to declare a value predicate function for maxBy function")
  val donutsMaxBy: (Donut) => Double = (donut) => donut.price
  println(s"Value function donutMaxBy = $donutsMaxBy")

  println("\nStep 5: How to find the maximum element using maxBy function and pass through the predicate function from Step 4")
  println(s"Maximum element in sequence using function from Step 3 = ${donuts4MaxBy.maxBy(donutsMaxBy)}")


  // =============  To Use min Function  =============
  /*def min: A*/
  println("\nStep 2: How to find the minimum element in the sequence using the min function")
  println(s"Min element in the donuts sequence = ${donuts17.min}")

  println("\nStep 4: How to find the minimum element in the sequence using the min function")
  println(s"Min element in the donut prices sequence = ${prices.min}")


  // =============  To Use minBy Function  =============
  /*def minBy[B](f: (A) ⇒ B): A*/
  println("\nStep 2: How to create a Sequence of type Donut")
  val donuts4MinBy: Seq[Donut] = Seq(Donut("Plain Donut", 1.5), Donut("Strawberry Donut", 2.0), Donut("Glazed Donut", 2.5))
  println(s"Elements of donuts = $donuts4MinBy")

  println("\nStep 3: How to find the minimum element in a sequence of case classes objects using the minBy function")
  println(s"Minimum element in sequence of case class of type Donut, ordered by price = ${donuts4MinBy.minBy(donut => donut.price)}")

  println("\nStep 4: How to declare a value predicate function for minBy function")
  val donutsMinBy: (Donut) => Double = (donut) => donut.price
  println(s"Value function donutMaxBy = $donutsMaxBy")

  println("\nStep 5: How to find the minimum element using minBy function and pass through the predicate function from Step 4")
  println(s"Minimum element in sequence using function from Step 3 = ${donuts4MinBy.minBy(donutsMinBy)}")


  // =============  To Use mkString Function  =============
  println(s"\nStep 2: Donuts elements using mkString function = ${donuts.mkString}") // without any separator

  println("\nStep 2: How to concatenate the elements of a sequence into a String using mkString function")
  val donuts1AsString: String = donuts.mkString(" and ")
  println(s"Donuts elements using mkString function = $donuts1AsString")

  println("\nStep 3: How to concatenate the elements of a sequence into a String using mkString and specifying prefix and suffix")
  val donutsWithPrefixAndSuffix: String = donuts.mkString("My favorite donuts namely ", " and ", " are very tasty!")
  println(s"$donutsWithPrefixAndSuffix")


  // =============  To Use nonEmpty Function  =============
  /*def nonEmpty: Boolean*/
  println("Step 1: How to initialize a Sequence of donuts")
  //val donuts14: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts14")

  println("\nStep 2: How to find out if a sequence is non empty using isEmpty function")
  println(s"Is donuts sequence empty = ${donuts14.nonEmpty}")

  println("\nStep 4: How to find out if a sequence is non empty using isEmpty function")
  println(s"Is donuts2 sequence empty = ${donuts131.nonEmpty}")


  // =============  To Use par Function  =============
  /*def par: ParRepr*/
  println("Step 1: How to initialize an Immutable Sequence of various donut flavours")
  val donutFlavours: Seq[String] = Seq("Plain", "Strawberry", "Glazed")
  println(s"Elements of donutFlavours immutable sequence = $donutFlavours")

  println("\nStep 2: Convert the Immutable donut flavours Sequence into Parallel Collection")
  import scala.collection.parallel.ParSeq
  val donutFlavoursParallel: ParSeq[String] = donutFlavours.par

  println("\nStep 3: How to use Scala Parallel Collection")
  val donutsPar: ParSeq[String] = donutFlavoursParallel.map(d => s"$d donut")
  println(s"Elements of donuts parallel collection = $donutsPar")


  // =============  To Use partition Function  =============
  /*def partition(p: (A) ⇒ Boolean): (Repr, Repr)*/
  println("Step 1: How to initialize a sequence which contains donut names and prices")
  //val donutNamesAndPrices: Seq[Any] = Seq("Plain Donut", 1.5, "Strawberry Donut", 2.0, "Glazed Donut", 2.5)
  println(s"Elements of donutNamesAndPrices = $donutNamesAndPrices")

  println("\nStep 2: How to split the sequence by the element types using partition function")
  val namesAndPrices: (Seq[Any], Seq[Any]) = donutNamesAndPrices.partition {
    case name: String => true
    case price: Double => false
  }
  println(s"Elements of namesAndPrices = $namesAndPrices")

  println("\nStep 3: How to access the donut String sequence from Step 2")
  println(s"Donut names = ${namesAndPrices._1}")

  println("\nStep 4: How to access the donut prices sequence from Step 2")
  println(s"Donut prices = ${namesAndPrices._2}")

  println("\nStep 5: How to extract the pair returned by partition function")
  val (donutNames1, donutPrices1) = donutNamesAndPrices.partition {
    case name: String => true
    case _ => false
  }
  println(s"donutNames = $donutNames1")
  println(s"donutPrices = $donutPrices1")


  // =============  To Use reduce Function  =============
  /*def reduce[A1 >: A](op: (A1, A1) ⇒ A1): A1*/
  println("Step 1: How to initialize a sequence of donut prices")
  //val donutPrices: Seq[Double] = Seq(1.5, 2.0, 2.5)
  println(s"Elements of donutPrices = $donutPrices")

  println("\nStep 2: How to find the sum of the elements using reduce function")
  //val sum: Double = donutPrices.reduce(_ + _)
  println(s"Sum of elements from donutPrices = ${donutPrices.reduce(_ + _)}")

  println("\nStep 3: How to find the sum of elements using reduce function explicitly")
  val sum1: Double = donutPrices.reduce((a, b) => a + b)
  println(s"Sum of elements from donutPrices by calling reduce function explicitly= $sum1")

  println("\nStep 4: How to find the cheapest donut using reduce function")
  println(s"Cheapest donut price = ${donutPrices.reduce(_ min _)}")

  println("\nStep 5: How to find the most expensive donut using reduce function")
  println(s"Most expensive donut price = ${donutPrices.reduce(_ max _)}")

  println("\nStep 6: How to initialize a Sequence of donuts")
  //val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 7: How to concatenate the elements from the sequence using reduce function")
  println(s"Elements of donuts sequence concatenated = ${donuts.reduce((left, right) => left + ", " + right)}")

  println("\nStep 8: How to declare a value function to concatenate donut names")
  val concatDonutNames: (String, String) => String = (left, right) => {
    left + ", " + right
  }
  println(s"Value function concatDonutNames = $concatDonutNames")

  println("\nStep 9: How to pass a function to reduce function")
  println(s"Elements of donuts sequence concatenated by passing function to the reduce function = ${donuts reduce concatDonutNames}")

  println("\nStep 10: How to use option reduce to avoid exception if the collection is empty")
  println(s"Using reduce option will NOT throw any exception = ${Seq.empty[String].reduceOption(_ + ", " + _)}")


  // =============  To Use reduceLeft Function  =============
  /*def reduceLeft[B >: A](op: (B, A) ⇒ B): B*/
  println("Step 1: How to initialize a sequence of donut prices")
  //val donutPrices: Seq[Double] = Seq(1.5, 2.0, 2.5)
  println(s"Elements of donutPrices = $donutPrices")

  println("\nStep 2: How to find the sum of the elements using reduceLeft function")
  //val sum: Double = donutPrices.reduceLeft(_ + _)
  println(s"Sum of elements from donutPrices = ${donutPrices.reduceLeft(_ + _)}")

  println("\nStep 3: How to find the sum of elements using reduceLeft function explicitly")
  val sum2: Double = donutPrices.reduceLeft((a, b) => a + b)
  println(s"Sum of elements from donutPrices by calling reduceLeft function explicitly= $sum2")

  println("\nStep 4: How to find the cheapest donut using reduceLeft function")
  println(s"Cheapest donut price = ${donutPrices.reduceLeft(_ min _)}")

  println("\nStep 5: How to find the most expensive donut using reduceLeft function")
  println(s"Most expensive donut price = ${donutPrices.reduceLeft(_ max _)}")

  println("\nStep 6: How to initialize a Sequence of donuts")
  //val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 7: How to concatenate the elements from the sequence using reduceLeft function")
  println(s"Elements of donuts sequence concatenated = ${donuts.reduceLeft((left, right) => left + ", " + right)}")

  println("\nStep 8: How to declare a value function to concatenate donut names")
  /*val concatDonutNames: (String, String) => String = (left, right) => {
    left + ", " + right
  }*/
  println(s"Value function concatDonutNames = $concatDonutNames")

  println("\nStep 9: How to pass a function to reduceLeft function")
  println(s"Elements of donuts sequence concatenated by passing function to the reduceLeft function = ${donuts reduceLeft concatDonutNames}")

  println("\nStep 10: How to use reduceLeftOption to avoid exception if the collection is empty")
  println(s"Using reduceLeftOption will NOT throw any exception = ${Seq.empty[String].reduceLeftOption(_ + ", " + _)}")


  // =============  To Use reduceRight Function  =============
  /*abstract def reduceRight[B >: A](op: (A, B) ⇒ B): B*/
  println("Step 1: How to initialize a sequence of donut prices")
  //val donutPrices: Seq[Double] = Seq(1.5, 2.0, 2.5)
  println(s"Elements of donutPrices = $donutPrices")

  println("\nStep 2: How to find the sum of the elements using reduceRight function")
  //val sum: Double = donutPrices.reduceRight(_ + _)
  println(s"Sum of elements from donutPrices = ${donutPrices.reduceRight(_ + _)}")

  println("\nStep 3: How to find the sum of elements using reduceRight function explicitly")
  val sum3: Double = donutPrices.reduceRight((a, b) => a + b)
  println(s"Sum of elements from donutPrices by calling reduceRight function explicitly= $sum3")

  println("\nStep 4: How to find the cheapest donut using reduceRight function")
  println(s"Cheapest donut price = ${donutPrices.reduceRight(_ min _)}")

  println("\nStep 5: How to find the most expensive donut using reduceRight function")
  println(s"Most expensive donut price = ${donutPrices.reduceRight(_ max _)}")

  println("\nStep 6: How to initialize a Sequence of donuts")
  //val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 7: How to concatenate the elements from the sequence using reduceRight function")
  println(s"Elements of donuts sequence concatenated = ${donuts.reduceRight((left, right) => left + ", " + right)}")
  println(s"Elements of donuts sequence concatenated = ${donuts.reduceRight((left, right) => left + ", " + right)}")

  println("\nStep 8: How to declare a value function to concatenate donut names")
  /*val concatDonutNames: (String, String) => String = (left, right) => {
    left + ", " + right
  }*/
  println(s"Value function concatDonutNames = $concatDonutNames")

  println("\nStep 9: How to pass a function to reduceRight function")
  println(s"Elements of donuts sequence concatenated by passing function to the reduceRight function = ${donuts reduceRight concatDonutNames}")

  println("\nStep 10: How to use reduceRightOption to avoid exception if the collection is empty")
  println(s"Using reduceRightOption will NOT throw any exception = ${Seq.empty[String].reduceRightOption(_ + ", " + _)}")


  // =============  To Use reverse Function  =============
  /*def reverse: Repr*/
  println("Step 1: How to initialize a Sequence of donuts")
  //val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 2: How to get the elements of the sequence in reverse using the reverse method")
  println(s"Elements of donuts in reversed order = ${donuts.reverse}")

  println("\nStep 3: How to access each reversed element using reverse and foreach methods")
  donuts.reverse.foreach(donut => println(s"donut = ${donut}"))


  // =============  To Use reverseIterator Function  =============
  /*def reverseIterator: Iterator[A]*/
  println("Step 1: How to initialize a Sequence of donuts")
  //val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 2: How to print all elements in reversed order using reverseIterator function")
  println(s"Elements of donuts in reversed order = ${donuts.reverseIterator.toList}")

  println("\nStep 3: How to iterate through elements using foreach method")
  val reverseIterator: Iterator[String] = donuts.reverseIterator
  reverseIterator.foreach(donut => println(s"donut = $donut"))


  // =============  To Use scan Function  =============
  println("Step 1: How to initialize a sequence of numbers")
  val numbers: Seq[Int] = Seq(1, 2, 3, 4, 5)
  println(s"Elements of numbers = $numbers")

  println("\nStep 2: How to create a running total using the scan function")
  val runningTotal: Seq[Int] = numbers.scan(0)(_ + _)
  println(s"Running total of all elements in the collection = $runningTotal")

  println("\nStep 3: How to create a running total using the scan function explicitly")
  val runningTotal2: Seq[Int] = numbers.scan(0)((a, b) => a + b)
  println(s"Running total of all elements in the collection = $runningTotal2")


  // =============  To Use scanLeft Function  =============
  /*def scanLeft[B, That](z: B)(op: (B, A) ⇒ B)(implicit bf: CanBuildFrom[Repr, B, That]): That*/
  println("Step 1: How to initialize a sequence of numbers")
  //val numbers: Seq[Int] = Seq(1, 2, 3, 4, 5)
  println(s"Elements of numbers = $numbers")

  println("\nStep 2: How to create a running total using the scanLeft function")
  //val runningTotal: Seq[Int] = numbers.scanLeft(0)(_ + _)
  println(s"Running total of all elements in the collection = ${numbers.scanLeft(0)(_ + _)}")

  println("\nStep 3: How to create a running total using the scanLeft function explicitly")
  val runningTotal3: Seq[String] = numbers.scanLeft("")((acc, b) => acc + b.toString)
  println(s"Running total of all elements in the collection = $runningTotal3")


  // =============  To Use scanRight Function  =============
  /*def scanRight[B, That](z: B)(op: (A, B) ⇒ B)(implicit bf: CanBuildFrom[Repr, B, That]): That*/
  println("Step 1: How to initialize a sequence of numbers")
  //val numbers: Seq[Int] = Seq(1, 2, 3, 4, 5)
  println(s"Elements of numbers = $numbers")

  println("\nStep 2: How to create a running total using the scanRight function")
  val runningTotal4: Seq[Int] = numbers.scanRight(0)(_ + _)
  println(s"Running total of all elements in the collection = $runningTotal4")

  println("\nStep 3: How to create a running total using the scanRight function explicitly")
  val runningTotal5: Seq[Int] = numbers.scanRight(0)((a, b) => a + b)
  println(s"Running total of all elements in the collection = $runningTotal5")
  val runningTotal5LB: Seq[ListBuffer[String]] = numbers.scanRight(ListBuffer.empty[String])((a, accList) => accList.clone += a.toString)
  println(s"Running total of all elements in the collection = $runningTotal5LB")


  // =============  To Use size Function  =============
  /*def size: Int*/
  println("\nStep 2: How to count the number of elements in the sequence using size function")
  println(s"Size of donuts sequence = ${donuts.size}")

  println("\nStep 3: How to use the count function")
  println(s"Number of times element Plain Donut appear in donuts sequence = ${donuts.count(_ == "Plain Donut")}")


  // =============  To Use slice Function  =============
  /*def slice(from: Int, until: Int): Repr*/
  println("Step 1: How to initialize a Sequence of donuts")
  //val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 2: How to take a section from the sequence using the slice function")
  println(s"Take elements from the sequence from index 0 to 1 = ${donuts.slice(0,1)}")
  println(s"Take elements from the sequence from index 0 to 2 = ${donuts.slice(0,2)}")
  println(s"Take elements from the sequence from index 0 to 3 = ${donuts.slice(0,3)}")

  println("\nStep 3: Slice function where the index is out of range")
  println(s"Take elements from the sequence from index 0 to 4 = ${donuts.slice(0,4)}")


  // =============  To Use sortBy Function  =============
  /*def sortBy[B](f: (A) ⇒ B)(implicit ord: math.Ordering[B]): Repr*/

  println("\nStep 2: How to create a Sequence of type Donut")
  //val donuts121: Seq[Donut] = Seq(Donut("Plain Donut", 1.5), Donut("Strawberry Donut", 2.0), Donut("Glazed Donut", 2.5))
  println(s"Elements of donuts = $donuts121")

  println("\nStep 3: How to sort a sequence of case class objects using the sortBy function")
  println(s"Sort a sequence of case class objects of type Donut, sorted by price = ${donuts121.sortBy(donut => donut.price)}")
  val sortByFunc: (Donut) => Double = (donut => donut.price)
  println(s"Sort a sequence of case class objects of type Donut, sorted by price = ${donuts121.sortBy(sortByFunc)}")


  // =============  To Use sorted Function  =============
  /*def sorted[B >: A](implicit ord: math.Ordering[B]): Repr*/
  println("Step 1: How to initialize donut prices")
  //val prices: Seq[Double] = Seq(1.50, 2.0, 2.50)
  println(s"Elements of prices = $prices")

  println("\nStep 2: How to sort a sequence of type Double using the sorted function")
  println(s"Sort a sequence of type Double by their natural ordering = ${prices.sorted}")

  println("\nStep 3: How to initialize a Sequence of donuts")
  //val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 4: How to sort a sequence of type String using the sorted function")
  println(s"Sort a sequence of type String by their natural ordering = ${donuts.sorted}")


  // =============  To Use sortWith Function  =============
  /*def sortWith(lt: (A, A) ⇒ Boolean): Repr*/
  println("\nStep 2: How to create a Sequence of type Donut")
  //val donuts: Seq[Donut] = Seq(Donut("Plain Donut", 1.5), Donut("Strawberry Donut", 2.0), Donut("Glazed Donut", 2.5))
  println(s"Elements of donuts = $donuts121")

  println("\nStep 3: How to sort a sequence of case class objects using the sortWith function")
  println(s"Sort a sequence of case classes of type Donut, sorted with price = ${donuts121.sortWith(_.price < _.price)}")

  println("\nStep 4: How to sort a sequence of case class objects in ascending order using the sortWith function")
  println(s"Sort a sequence of case classes of type Donut, sorted with price in ascending order = ${donuts121.sortWith(_.price < _.price)}")
  println(s"Sort a sequence of case classes of type Donut, sorted with price in ascending order explicitly = ${donuts121.sortWith((d1,d2) => d1.price < d2.price)}")

  println("\nStep 5: How to sort a sequence of case class objects in descending order using the sortWith function")
  println(s"Sort a sequence of case classes of type Donut, sorted with price in descending order = ${donuts121.sortWith(_.price > _.price)}")
  println(s"Sort a sequence of case classes of type Donut, sorted with price in descending order explicitly = ${donuts121.sortWith((d1,d2) => d1.price > d2.price)}")


  // =============  To Use tail Function  =============
  /*def tail: Repr*/
  println("\nStep 2: How to return all elements in the sequence except the head using the tail function")
  println(s"Elements of donuts excluding the head = ${donuts.tail}")

  println("\nStep 3: How to access the last element of the donut sequence by using the last function")
  println(s"Last element of donut sequence = ${donuts.last}")

  println("\nStep 4: How to access the first element of the donut sequence by using the head function")
  println(s"First element of donut sequence = ${donuts.head}")


  // =============  To Use take Function  =============
  /*def take(n: Int): Repr*/
  println("\nStep 2: How to take elements from the sequence using the take function")
  println(s"Take the first donut element in the sequence = ${donuts.take(1)}")
  println(s"Take the first and second donut elements in the sequence = ${donuts.take(2)}")
  println(s"Take the first, second and third donut elements in the sequence = ${donuts.take(3)}")

  println(s"take method returns all elements in the sequence in case N>size = ${donuts.take(10)}")


  // =============  To Use takeRight Function  =============
  /*def takeRight(n: Int): Repr*/
  println("\nStep 2: How to take the last N elements using the takeRight function")
  println(s"Take the last donut element in the sequence = ${donuts.takeRight(1)}")
  println(s"Take the last two donut elements in the sequence = ${donuts.takeRight(2)}")
  println(s"Take the last three donut elements in the sequence = ${donuts.takeRight(3)}")

  println(s"takeRight method returns all elements in the sequence in case N>size = ${donuts.takeRight(10)}")


  // =============  To Use takeWhile Function  =============
  /*def takeWhile(p: (A) ⇒ Boolean): Repr*/
  println("Step 1: How to initialize a List of donuts")
  //val donuts: Seq[String] = List("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 2: How to take elements from the List using the takeWhile function")
  println(s"Take donut elements which start with letter P = ${donuts.takeWhile(_.charAt(0) == 'P')}")

  println("\nStep 3: How to declare a predicate function to be passed-through to the takeWhile function")
  val takeDonutPredicate: (String) => Boolean = (donutName) => donutName.charAt(0) == 'P'
  println(s"Value function takeDonutPredicate = $takeDonutPredicate")

  println("\nStep 4: How to take elements using the predicate function from Step 3")
  println(s"Take elements using function from Step 3 = ${donuts.takeWhile(takeDonutPredicate)}")


  // =============  To Use transpose Function  =============
  println("Step 1: How to initialize a Sequence of donuts")
  //val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 2: How to initialize donut prices")
  //val prices: Seq[Double] = Seq(1.50, 2.0, 2.50)
  println(s"Elements of prices = $prices")

  println("\nStep 3: How to create a List of donuts and prices")
  val donutList = List(donuts, prices)
  println(s"Sequence of donuts and prices = $donutList")

  println("\nStep 4: How to pair each element from both donuts and prices Sequences using the transpose function")
  println(s"Transposed list of donuts paired with their individual prices = ${donutList.transpose}")


  // =============  To Use union Function  =============
  /*def union(that: GenSet[A]): This*/
  println("Step 1: How to initialize a Set of donuts")
  val donutsUnion1: Set[String] = Set("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts1 = $donutsUnion1")

  println("\nStep 2: How to initialize another Set of donuts")
  val donutsUnion2: Set[String] = Set("Plain Donut", "Chocolate Donut", "Vanilla Donut")
  println(s"Elements of donuts2 = $donutsUnion2")

  println("\nStep 3: How to merge two Sets using union function")
  println(s"Union of Sets donuts1 and donuts2 = ${donutsUnion1 union donutsUnion2}")
  println(s"Union of Sets donuts2 and donuts1 = ${donutsUnion2 union donutsUnion1}")

  println("\nStep 4: How to merge two Sets using ++ function")
  println(s"Union of Sets donuts1 and donuts2 = ${donutsUnion1 ++ donutsUnion2}")
  println(s"Union of Sets donuts2 and donuts1 = ${donutsUnion2 ++ donutsUnion1}")


  // =============  To Use unzip Function  =============
  /*def unzip[A1, A2](implicit asPair: (A) ⇒ (A1, A2)): (CC[A1], CC[A2])*/
  println("Step 1: How to initialize a Sequence of donuts")
  //val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 2: How to initialize a Sequence of donut prices")
  //val donutPrices = Seq[Double](1.5, 2.0, 2.5)
  println(s"Elements of donut prices = $donutPrices")

  println("\nStep 3: How to zip the donuts Sequence with their corresponding prices")
  val zippedDonutsAndPrices: Seq[(String, Double)] = donuts zip donutPrices
  println(s"Zipped donuts and prices = $zippedDonutsAndPrices")

  println("\nStep 4: How to unzip the zipped donut sequence into separate donuts names and prices Sequences")
  val unzipped: (Seq[String], Seq[Double]) = zippedDonutsAndPrices.unzip
  println(s"Donut names unzipped = ${unzipped._1}")
  println(s"Donut prices unzipped = ${unzipped._2}")

  println("\nStep 5: How to initialize a Sequence of donut prices")
  val donutQuantities = Seq[Int](5, 2, 3)
  println(s"Elements of donut prices = $donutQuantities")

  println("\nStep 6: How to zip the donuts Sequence with their corresponding prices and quantities")
  val zippedDonutsAndPricesWithQuantities: Seq[(String, Double, Int)] = donuts zip donutPrices zip donutQuantities map {case ((a,b),c) => (a,b,c)}
  //val zippedDonutsAndPricesWithQuantities = List(donuts, donutPrices, donutQuantities).transpose
  println(s"Zipped donuts, prices and quantities = $zippedDonutsAndPricesWithQuantities")

  println("\nStep 7: How to unzip the zipped donut sequence into separate donuts names, prices and quantities Sequences")
  val unzippedWithQuantities: (Seq[String], Seq[Double], Seq[Int]) = zippedDonutsAndPricesWithQuantities.unzip3
  //val unzippedWithQuantities = zippedDonutsAndPricesWithQuantities.unzip3()
  println(s"Donut names unzipped = ${unzippedWithQuantities._1}")
  println(s"Donut prices unzipped = ${unzippedWithQuantities._2}")
  println(s"Donut Quantities unzipped = ${unzippedWithQuantities._3}")


  // =============  To Use unzip3 Function  =============
  /*def unzip3[A1, A2, A3](implicit asTriple: (A) ⇒ (A1, A2, A3)): (CC[A1], CC[A2], CC[A3])*/
  println("Step 1: How to initialize a Sequence of Tuple3 elements")
  val donutsPricesTaste: Seq[(String, Double, String)] = Seq(("Plain Donut",1.5,"Tasty"), ("Glazed Donut",2.0,"Very Tasty"), ("Strawberry Donut",2.5,"Very Tasty"))
  println(s"Donuts tuple3 elements = $donutsPricesTaste")

  println("\nStep 2: How to call unzip3 function to unzip Tuple3 elements")
  val unzipped3: (Seq[String], Seq[Double], Seq[String]) = donutsPricesTaste.unzip3
  println(s"Unzipped donut names = ${unzipped3._1}")
  println(s"Unzipped donut prices = ${unzipped3._2}")
  println(s"Unzipped donut taste = ${unzipped3._3}")


  // =============  To Use view Function  =============
  /*def view: TraversableView[A, Repr]*/
  println("Step 1: How to create a large numeric range and take the first 10 odd numbers")
  val largeOddNumberList: List[Int] = (1 to 1000000).filter(_ % 2 != 0).take(10).toList

  println(s"\nStep 2: How to lazily create a large numeric range and take the first 10 odd numbers")
  val lazyLargeOddNumberList = (1 to 1000000).view.filter(_ % 2 != 0).take(10).toList
  println(s"Lazily take the first 10 odd numbers from lazyLargeOddNumberList = ${lazyLargeOddNumberList}")


  // =============  To Use withFilter Function  =============
  /*def withFilter(p: (A) ⇒ Boolean): FilterMonadic[A, Repr]*/
  println("Step 1: How to initialize a Sequence of donuts")
  val donutsWithFilter: Seq[String] = List("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donutsWithFilter")

  println("\nStep 2: How to filter elements using the withFilter function")
  donuts
    .withFilter(_.charAt(0) == 'P')
    .foreach(donut => println(s"Donut starting with letter P = $donut"))


  // =============  To Use zip Function  =============
  /*def zip[B](that: GenIterable[B]): Iterable[(A, B)]*/
  println("Step 1: How to initialize a Sequence of donuts")
  //val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 2: How to initialize a Sequence of donut prices")
  val donutPricesZip: Seq[Double] = Seq(1.5, 2.0, 2.5, 3.5)
  println(s"Elements of donut prices = $donutPricesZip")

  println("\nStep 3: How to use zip method to zip two collections")
  val zippedDonutsAndPrices1: Seq[(String, Double)] = donuts zip donutPricesZip
  println(s"Zipped donuts and prices = $zippedDonutsAndPrices1")

  println("\nStep 4: How to use unzip method to un-merge a zipped collections")
  val unzipped1: (Seq[String], Seq[Double]) = zippedDonutsAndPrices.unzip
  println(s"Donut names unzipped = ${unzipped1._1}")
  println(s"Donut prices unzipped = ${unzipped1._2}")


  // =============  To Use zipWithIndex Function  =============
  /*def zipWithIndex: Iterable[(A, Int)]*/

  println("Step 1: How to initialize a Sequence of donuts")
  //val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
  println(s"Elements of donuts = $donuts")

  println("\nStep 2: How to zip the donuts Sequence with their corresponding index using zipWithIndex method")
  val zippedDonutsWithIndex: Seq[(String, Int)] = donuts.zipWithIndex
  zippedDonutsWithIndex.foreach{ donutWithIndex =>
    println(s"Donut element = ${donutWithIndex._1} is at index = ${donutWithIndex._2}")
  }

}
