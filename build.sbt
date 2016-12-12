name := "scala-servlet"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "javax.servlet"              %  "javax.servlet-api" % "3.1.0" % "provided",
  "com.typesafe.scala-logging" %% "scala-logging"     % "3.1.0",
  "com.typesafe"               %  "config"            % "1.3.0",
  "com.typesafe.play"          %% "play-json"         % "2.5.0",
  "commons-io"                 %  "commons-io"        % "2.4"
)

enablePlugins(JettyPlugin)
    