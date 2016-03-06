package fr.xebia.xke.fp5.exercise3.persistance.outputstream

import java.io.PrintWriter

import fr.xebia.xke.fp5.exercise3.model.Person
import fr.xebia.xke.fp5.exercise3.persistance.Persister.persist
import org.mockito.Mockito.{mock, verify, verifyZeroInteractions}
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class PersisterSpec extends FlatSpec with Matchers with BeforeAndAfter {

  var printWriterMock: PrintWriter = mock(classOf[PrintWriter])
  implicit val mockedOutputStream = OutputStreamPersister.outputStreamPersister(printWriterMock)

  "Persister" should "write valid Person to stream in string format" in {
    // Given

    // When
    persist(Person("fn", "ln", Option("mail@gmail.com")))

    // Then
    verify(printWriterMock).println("Person(fn,ln,Some(mail@gmail.com))")
  }

  it should "write valid Person to stream in json format" in {
    // Given

    // When
    persist(Person("fn", "ln", Option("mail@gmail.com")))

    // Then
    verify(printWriterMock).println("""{"firstName":"fn", "lastName":"ln", "mail":{"present":true, "value":"mail@gmail.com"}}""")
  }

  it should "not write invalid Person to stream" in {
    // Given

    // When
    persist(Person("fn", "ln37", Option("mail@gmail.com")))

    // Then
    verifyZeroInteractions(printWriterMock)
  }

}
