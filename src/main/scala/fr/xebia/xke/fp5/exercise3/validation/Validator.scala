package fr.xebia.xke.fp5.exercise3.validation

import scalaz.Validation

object Validator {

  type ValidationError = String

  def validate[T](target: T)(implicit ev: Validatable[T]): Validation[ValidationError, T] =
    ev.validate(target)

}