scalaVersion := "2.11.7"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.0.6"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.4.6"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.2"
libraryDependencies += "com.typesafe.akka" %% "akka-http-experimental" % "2.4.2"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.2" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"
libraryDependencies += "org.mockito" % "mockito-core" % "1.10.19" % "test"

lazy val exo3_1: TaskKey[Unit] = taskKey("Launch EXO 3_1")

exo3_1 := {(testOnly in Test).toTask(" -- -n EXO_3_1").value}

lazy val exo3_2: TaskKey[Unit] = taskKey("Launch EXO 3_2")

exo3_2 := {(testOnly in Test).toTask(" -- -n EXO_3_2").value}

lazy val exo3_3: TaskKey[Unit] = taskKey("Launch EXO 3_3")

exo3_3 := {(testOnly in Test).toTask(" -- -n EXO_3_3").value}