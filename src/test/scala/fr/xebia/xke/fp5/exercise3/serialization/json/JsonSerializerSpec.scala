package fr.xebia.xke.fp5.exercise3.serialization.json

import fr.xebia.xke.fp5.exercise3.helper.ValidationHelper._
import fr.xebia.xke.fp5.exercise3.model.Person
import fr.xebia.xke.fp5.exercise3.serialization.Serializer._
import fr.xebia.xke.fp5.exercise3.serialization.json.StandardTypeSerializationSupport._
import fr.xebia.xke.fp5.exercise3.serialization.json.CustomTypeSerializationSupport._
import org.scalatest.{Assertions, FlatSpec, Matchers}

class JsonSerializerSpec extends FlatSpec with Matchers with Assertions {

  "Serializable" should "serialize Booleans" in {
    write(true).value should equal("true")
    write(false).value should equal("false")
  }

  it should "deserialize Booleans" in {
    read[Boolean]("true").value should equal(true)
    read[Boolean]("false").value should equal(false)
  }

  it should "fail for invalid Boolean read" in {
    read[Boolean]("no boolean").isFailure should be(true)
  }

  it should "serialize Strings" in {
    write("Hello world !").value should equal("\"Hello world !\"")
  }

  it should "deserialize String" in {
    read[String]("deserialize me").value should equal("deserialize me")
  }

  it should "serialize Ints" in {
    write(5).value should equal("5")
    write(-13).value should equal("-13")
  }

  it should "deserialize Ints" in {
    read[Int]("-17").value should equal(-17)
    read[Int]("101").value should equal(101)
  }

  it should "fail for invalid Int read" in {
    read[Int]("ten").isFailure should be(true)
  }

  it should "serialize Unit" in {
    write(()).value should equal("undefined")
  }

  it should "deserialize Unit" in {
    read[Unit]("undefined").value should equal(())
  }

  it should "fail on invalid Unit read" in {
    read[Unit]("some").isFailure should be(true)
  }

  it should "serialize Option" in {
    write(Option.empty[String]).value should equal("""{"present":false}""")
    write(Option("hi")).value should equal("""{"present":true, "value":"hi"}""")
  }

  it should "deserialize Option" in {
    read[Option[Int]]("""{"present":false}""").value should equal(None)
    read[Option[String]]("""{"present":true, "value":"val"}""").value should equal(Some("val"))
  }

  it should "fail on invalid Option read" in  {
    read[Option[Int]]("""{"value":15}""").isFailure should be(true)
  }

  it should "serialize Either" in {
    write(Left("leftValue"):Either[String, Int]).value should equal("""{"branch":"left", "value":"leftValue"}""")
    write(Right(-14):Either[String, Int]).value should equal("""{"branch":"right", "value":-14}""")
  }

  it should "deserialize Either" in {
    read[Either[Int, String]](s"""{"branch":"left", "value":4}""").value should equal(Left(4))
    read[Either[Int, Unit]](s"""{"branch":"right", "value":undefined}""").value should equal(Right(()))
  }

  it should "serialize Person" in {
    write(Person("first", "last", None)).value should equal("""{"firstName":"first", "lastName":"last", "mail":{"present":false}}""")
    write(Person("first", "last", Some("flast@gmail.com"))).value should equal("""{"firstName":"first", "lastName":"last", "mail":{"present":true, "value":"flast@gmail.com"}}""")
  }

  it should "deserialize Person" in {
    read[Person]("""{"firstName":"martin", "lastName":"odersky", "mail":{"present":false}}""").value should equal(Person("martin", "odersky", None))
  }

  it should "fail for invalid Person read" in {
    read[Person]("Person(hi)").isFailure should be(true)
  }

  it should "fail on invalid Either read" in {
    read[Either[Int, Option[String]]]("either").isFailure should be(true)
    read[Either[Int, Option[String]]](s"""{"branch":"right", "value":3}""").isFailure should be(true)
  }

}
