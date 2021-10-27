name := """HumanResourcesApp"""
organization := "humans.com"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.6"

libraryDependencies += guice

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "humans.com.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "humans.com.binders._"


libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
resolvers += "Atlassian 3rd-Party" at "https://maven.atlassian.com/3rdparty/"


libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  "org.postgresql" % "postgresql" % "42.2.24" //org.postgresql.ds.PGSimpleDataSource dependency
)