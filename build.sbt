
lazy val commonSettings = Seq(
  name := "canon",
  version := "1.0",
  scalaVersion := "2.11.8",
  description :=
    "Beautiful, compositional document layouts; a Wadler, Leijen, Christiansen pretty printer",

  libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "utest" % "0.4.3" % "test"
  ),

  testFrameworks +=
    new TestFramework("utest.runner.Framework"),

  scalacOptions ++=
    Seq("-unchecked", "-deprecation", "-feature")
)

commonSettings

enablePlugins(ScalaJSPlugin)

lazy val root = project.in(file("."))
  .aggregate(canonJS, canonJVM)
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val canonJVM = canon.jvm
lazy val canonJS = canon.js

lazy val canon = crossProject.in(file("."))
  .settings(commonSettings:_*)
  .jvmSettings()
  .jsSettings()

