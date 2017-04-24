name := """pl"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  filters,
  "com.google.code.gson" % "gson" % "2.7",
  "dom4j" % "dom4j" % "1.6.1",
  "org.hibernate" % "hibernate-entitymanager" % "5.2.1.Final",
  javaJpa,
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"
)


fork in run := true