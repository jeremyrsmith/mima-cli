organization := "io.github.jeremyrsmith"
name := "mima-cli"
version := "0.1.0"
scalaVersion := "2.13.8"
licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
scmInfo := Some(ScmInfo(
    url("https://github.com/jeremyrsmith/mima-cli"),
    "scm:git:git@github.com:jeremyrsmith/mima-cli.git"))
pomExtra := {
  <developers>
    <developer>
      <id>jeremyrsmith</id>
      <name>Jeremy Smith</name>
      <url>https://github.com/jeremyrsmith</url>
    </developer>
  </developers>
}

libraryDependencies ++= Seq(
  "com.typesafe" %% "mima-core" % "1.1.0"
)

scalacOptions ++= Seq(
  "-deprecation"
)