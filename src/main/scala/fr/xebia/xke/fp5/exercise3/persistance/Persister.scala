package fr.xebia.xke.fp5.exercise3.persistance

import fr.xebia.xke.fp5.exercise3.serialization.Serializable
import fr.xebia.xke.fp5.exercise3.validation.Validatable

object Persister {

  def persist[T, U](t: T)(implicit serializableEvidence: Serializable[T], validatableEvidence: Validatable[T], persistanceLayer: PersistanceLayer): Unit =
    for {
      valid <- validatableEvidence.validate(t)
      serialized <- serializableEvidence.serialize(t)
    } yield persistanceLayer.persist(serialized)

}
