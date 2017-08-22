name := """SilkAnalyser"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.42",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"
)


fork in run := true

fork in run := true

fork in run := true