name := """word"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
  "com.google.code.gson" % "gson" % "2.7",
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final"
)
