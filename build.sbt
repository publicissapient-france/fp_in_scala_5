scalaVersion := "2.11.7"

lazy val nextStep = taskKey[Unit]("Get the next step of the exercise")

lazy val initialState = "exo1-step1"

lazy val lastStep = "exo2-step7"

lazy val transitions = Map(
  initialState -> "exo1-step2",
  "exo1-step2" -> "exo1-step3",
  "exo1-step3" -> "exo1-step4",
  "exo1-step4" -> "exo1-step5",
  "exo1-step5" -> "exo2-step1",
  "exo2-step1" -> "exo2-step2",
  "exo2-step2" -> "exo2-step3",
  "exo2-step3" -> "exo2-step4",
  "exo2-step4" -> "exo2-step5",
  "exo2-step5" -> "exo2-step6",
  "exo2-step6" -> lastStep
)

def currentCommit = ("git rev-parse HEAD" !!).trim.stripLineEnd

def currentStep = (s"git name-rev --tags --name-only $currentCommit" !!).trim.stripLineEnd

def addAll = "git add -A" !!

def resetHard = "git reset --hard" !!

def checkout(ref: String) = s"git checkout $ref" !!

nextStep := {
  currentStep match {
    case "undefined" =>
      addAll
      resetHard
      checkout(initialState)

    case step if step == lastStep =>
      println("You already reached the last step")

    case _ =>
      addAll
      resetHard
      checkout(transitions(currentStep))
  }
}
