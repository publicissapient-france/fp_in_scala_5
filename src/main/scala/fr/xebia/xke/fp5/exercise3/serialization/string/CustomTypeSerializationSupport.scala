package fr.xebia.xke.fp5.exercise3.serialization.string

import fr.xebia.xke.fp5.exercise3.model.Person
import fr.xebia.xke.fp5.exercise3.serialization.Serializable
import fr.xebia.xke.fp5.exercise3.serialization.Serializer.SerializationError

import scalaz.Validation

object CustomTypeSerializationSupport {

  implicit object PersonSerializationSupport extends Serializable[Person] {

    val PersonPattern = """Person\((.*),(.*),(.*)\)""".r

    override def deserialize(s: String): Validation[SerializationError, Person] = ???

    override def serialize(t: Person): Validation[SerializationError, String] = ???

  }

}