import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "test"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "org.neo4j" % "neo4j" % "1.8"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here

      libraryDependencies ++= Seq(
        "org.neo4j.app" % "neo4j-server" % "1.8" classifier "static-web" classifier "",
        "com.sun.jersey" % "jersey-core" % "1.9"
      ),

      resolvers ++= Seq(
        //"maven-central" at "http://repo1.maven.org/maven2",
        "neo4j-public-repository" at "http://m2.neo4j.org/content/groups/public"
      )
    )

}
