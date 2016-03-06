package fr.xebia.xke.fp5.exercise3.validation

import fr.xebia.xke.fp5.exercise3.serialization.Serializer.SerializationError

import scalaz.Validation


trait Validatable[T] {

  def validate(t: T): Validation[SerializationError, T]

}
