name := "ScalaWorkshopV2014-ej1"

organization := "org.scala-meetup-mvd"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.11.2"

libraryDependencies += "org.scalatest"     %% "scalatest"    % "2.2.1" % "test"

//Scala compiler options
scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",         // yes, this is 2 args
  "-Xlint",
  "-Ywarn-dead-code",           // N.B. doesn't work well with the ??? hole
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Xexperimental",
  "-Xlog-reflective-calls",
  "-Xcheckinit",
  "-unchecked"
  //"-Xfatal-warnings",           // Does not work well with DelayedInit
  //"-feature",
  //"-language:existentials",
  //"-language:higherKinds",
  //"-language:implicitConversions",
  //"-Yno-adapted-args",
)



