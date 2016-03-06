package fr.xebia.xke.fp5.exercise3.validation.validators

import fr.xebia.xke.fp5.exercise3.model.Person
import fr.xebia.xke.fp5.exercise3.validation.Validatable
import fr.xebia.xke.fp5.exercise3.validation.Validator.ValidationError

import scalaz._


object CustomTypeValidationSupport {

  implicit object PersonValidationSupport extends Validatable[Person] {

    override def validate(t: Person): Validation[ValidationError, Person] = ???

  }

  private def validateName(name: String): Validation[ValidationError, String] =
    if (name.matches(".*[0-9].*"))
      Failure("contains invalid characters")
    else
      Success(name)

  lazy val MailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$".r
  private def validateMail(mail: Option[String]): Validation[ValidationError, Option[String]] = {
    if (mail.isEmpty)
      Success(mail)
    else
      mail.get match {
        case MailRegex() => Success(mail)
        case _ => Failure("invalid mail")
      }
  }

}