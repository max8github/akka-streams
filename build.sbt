name := "akka-streams"
version := "0.1"
scalaVersion := "2.12.8"

//repo of dependency used for text rendering
resolvers += "indvd00m-github-repo" at "https://raw.githubusercontent.com/indvd00m/maven-repo/master/repository"

lazy val akkaVersion = "2.5.19"
lazy val scalaTestVersion = "3.0.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion,
  "net.mikolak" %% "travesty" % "0.9_2.5.11"
)

