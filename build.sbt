name := "sbt-avrohugger"
organization := "com.julianpeeters"
description := "Sbt plugin for compiling Avro to Scala"

version := "2.0.0-RC22-dhl"

enablePlugins(SbtPlugin)

(fork in run) := true

(connectInput in run) := true

(outputStrategy in run) := Some(StdoutOutput)

scalaVersion := appConfiguration.value.provider.scalaProvider.version
crossSbtVersions := Seq(sbtVersion.value)
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Ywarn-value-discard")

libraryDependencies ++= Seq(
  "com.julianpeeters" %% "avrohugger-core" % "1.0.0-RC22-dhl",
  "com.julianpeeters" %% "avrohugger-filesorter" % "1.0.0-RC22",
  "io.spray" %% "spray-json" % "1.3.2",
  "org.specs2" %% "specs2-core" % "3.8.6" % "test")

publishMavenStyle := true
publishArtifact in Test := false
publishTo := Some(
  "DHL Artifactory" at "https://repo.dhlparcel.nl/artifactory/dhlparcel-sbt-local"
)

credentials += Credentials(
  Path.userHome / ".sbt" / ".artifactory-credentials"
)
pomIncludeRepository := { _ => false }
licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
homepage := Some(url("https://github.com/julianpeeters/sbt-avrohugger"))
pomExtra := (
  <scm>
    <url>git://github.com/julianpeeters/sbt-avrohugger.git</url>
    <connection>scm:git://github.com/julianpeeters/sbt-avrohugger.git</connection>
  </scm>
  <developers>
    <developer>
      <id>julianpeeters</id>
      <name>Julian Peeters</name>
      <url>http://github.com/julianpeeters</url>
    </developer>
  </developers>)

scriptedLaunchOpts := { scriptedLaunchOpts.value ++
  Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
}
scriptedBufferLog := false
