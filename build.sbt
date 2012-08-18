name := "Lift Tests"

version := "1.0"

organization := "code"

scalaVersion := "2.9.2"

{
  val liftVersion = "2.5-SNAPSHOT"
  libraryDependencies ++= Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion,
    "net.liftweb" %% "lift-mapper" % liftVersion
  )
}

libraryDependencies ++= Seq(
  "org.mortbay.jetty" % "jetty" % "6.1.22" % "container",
  "ch.qos.logback" % "logback-classic" % "1.0.6",
  "org.apache.sanselan" % "sanselan" % "0.97-incubator",
  "joda-time" % "joda-time" % "1.6",
  "com.h2database" % "h2" % "1.3.167"
)

scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked")

javacOptions  ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

seq(webSettings :_*)
