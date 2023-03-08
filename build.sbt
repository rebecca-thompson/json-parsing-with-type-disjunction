ThisBuild / scalaVersion := "2.12.16"

libraryDependencies ++= Seq(
  "net.liftweb" %% "lift-json" % "3.5.0"
)

lazy val root = (project in file("."))
  .settings(
    name := "json-parsing-with-type-disjunction"
  )