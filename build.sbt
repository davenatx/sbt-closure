sbtPlugin := true

organization := "com.dmp-sbt"

name := "sbt-closure"

version := "0.1.5"

libraryDependencies += "com.google.javascript" % "closure-compiler" % "v20150609"

homepage := Some(url("https://github.com/davenatx/sbt-closure"))

publishMavenStyle := true

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2")))

publishArtifact in Test := false

licenses in GlobalScope += "Apache License 2.0" -> url("https://github.com/davenatx/sbt-closure/raw/master/LICENSE")

pomExtra := (
  <scm>
    <url>git@github.com:davenatx/sbt-closure.git</url>
    <connection>scm:git:git@github.com:davenatx/sbt-closure.git</connection>
  </scm>
  <developers>
    <developer>
      <id>davenatx</id>
      <name>David Price</name>
      <url>http://github.com/davenatx</url>
    </developer>
  </developers>
)

scalacOptions := Seq("-deprecation", "-unchecked", "-encoding", "utf8")
