package hello.scala

object HelloScala extends App {
  println("hello world!")
  println("printing command line args :: " + args.mkString(","))

  var noOfDonuts:Int = _
  noOfDonuts = 12

  println(s"Np of donuts = $noOfDonuts")
}
