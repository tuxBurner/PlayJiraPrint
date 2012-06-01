import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "jiraprint"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "commons-io" % "commons-io" % "2.0.1",
      "commons-io" % "commons-io" % "1.5",
      "com.github.twitter" % "bootstrap" % "2.0.3",
      "com.jquery" % "jquery" % "1.7.1",
      "com.atlassian.jira" % "jira-rest-java-client" % "0.6-m4"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      resolvers += ("Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"),
      resolvers += ("webjars" at "http://webjars.github.com/m2"),
      resolvers += ("atlassian" at "https://maven.atlassian.com/repository/public")
      
    )

}
