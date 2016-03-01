package fr.xebia.xke.fp5.exercise3.persistance.outputstream

import java.io.PrintWriter

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import fr.xebia.xke.fp5.EXO_3_3
import fr.xebia.xke.fp5.exercise3.model.Person
import fr.xebia.xke.fp5.exercise3.persistance.Persister.persist
import fr.xebia.xke.fp5.exercise3.validation.validators.CustomTypeValidationSupport._
import fr.xebia.xke.fp5.exercise3.validation.validators.StandardTypeValidationSupport._
import org.mockito.Mockito.{mock, verify, verifyZeroInteractions}
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class PersisterSpec extends FlatSpec with Matchers with BeforeAndAfter {

  implicit val materializer = ActorMaterializer()(ActorSystem())

  var printWriterMock: PrintWriter = mock(classOf[PrintWriter])
  implicit val mockedOutputStream = OutputStreamPersister.outputStreamPersister(printWriterMock)

  "Persister" should "write valid Person to stream in string format" taggedAs EXO_3_3 in {
    // Given
    import fr.xebia.xke.fp5.exercise3.serialization.string.CustomTypeSerializationSupport._

    // When
    persist(Person("fn", "ln", Option("mail@gmail.com")))

    // Then
    verify(printWriterMock).println("Person(fn,ln,Some(mail@gmail.com))")
  }

  it should "not write invalid Person to stream" taggedAs EXO_3_3 in {
    // Given
    import fr.xebia.xke.fp5.exercise3.serialization.string.CustomTypeSerializationSupport._

    // When
    persist(Person("fn", "ln37", Option("mail@gmail.com")))

    // Then
    verifyZeroInteractions(printWriterMock)
  }

  it should "write Either to given stream in string format" taggedAs EXO_3_3 in {
    // Given
    import fr.xebia.xke.fp5.exercise3.serialization.string.StandardTypeSerializationSupport._

    // When
    persist(Left("val"): Either[String, String])

    // Then
    verify(printWriterMock).println("""Left("val")""")
  }

  it should "write Either to given stream in json format" taggedAs EXO_3_3 in {
    // Given
    import fr.xebia.xke.fp5.exercise3.serialization.json.StandardTypeSerializationSupport._

    // When
    persist(Left("val"): Either[String, String])

    // Then
    verify(printWriterMock).println("""{"branch":"left", "value":"val"}""")
  }

}
