package fr.xebia.xke.fp5.exercise3.serialization

import fr.xebia.xke.fp5.exercise3.serialization.Serializer.SerializationError

import scalaz.Validation


trait Serializable[T] {

  def deserialize(s: String): Validation[SerializationError, T]

  def serialize(t: T): Validation[SerializationError, String]

}
