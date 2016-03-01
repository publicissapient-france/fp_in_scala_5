package fr.xebia.xke.fp5.exercise3.validation.validators

import fr.xebia.xke.fp5.exercise3.serialization.Serializer.SerializationError
import fr.xebia.xke.fp5.exercise3.validation.Validatable

import scalaz.{Success, Validation}


object StandardTypeValidationSupport {

  private def bypass[T] = new Validatable[T] {

    override def validate(t: T): Validation[SerializationError, T] =
      Success(t)

  }

  implicit def BooleanValidationSupport[A] = bypass[Boolean]

  implicit def StringValidationSupport[A] = bypass[String]

  implicit def IntValidationSupport[A] = bypass[Int]

  implicit def UnitValidationSupport[A] = bypass[Unit]

  implicit def OptionValidationSupport[A] = bypass[Option[A]]

  implicit def EitherValidationSupport[A, B] = bypass[Either[A, B]]

}