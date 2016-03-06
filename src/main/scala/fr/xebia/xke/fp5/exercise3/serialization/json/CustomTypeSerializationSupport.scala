package fr.xebia.xke.fp5.exercise3.serialization.json

import fr.xebia.xke.fp5.exercise3.model.Person
import fr.xebia.xke.fp5.exercise3.serialization.Serializer.SerializationError
import fr.xebia.xke.fp5.exercise3.serialization.json.StandardTypeSerializationSupport._
import fr.xebia.xke.fp5.exercise3.serialization.{Serializable, Serializer}

import scalaz.{Failure, Validation}

object CustomTypeSerializationSupport {

  implicit object PersonSerializationSupport extends Serializable[Person] {

    val PersonPattern = """\{"firstName":"(.*)", "lastName":"(.*)", "mail":(.*)\}""".r

    override def deserialize(s: String): Validation[SerializationError, Person] = s match {
      case PersonPattern(eventualFirstName, eventualLastName, eventualMail) =>
        for {
          firstName <- Serializer.read[String](eventualFirstName)
          lastName <- Serializer.read[String](eventualLastName)
          mail <- Serializer.read[Option[String]](eventualMail)
        } yield Person(firstName, lastName, mail)

      case _ => Failure("Expected Person")
    }

    override def serialize(t: Person): Validation[SerializationError, String] =
      for {
        firstName <- Serializer.write(t.firstName)
        lastName <- Serializer.write(t.lastName)
        mail <- Serializer.write(t.mail)
      } yield s"""{"firstName":$firstName, "lastName":$lastName, "mail":$mail}"""

  }

}