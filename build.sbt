scalaVersion := "2.11.7"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.0.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"
libraryDependencies += "org.mockito" % "mockito-core" % "1.10.19" % "test"

configurations := Set(Configurations.Test, Configurations.Sources)