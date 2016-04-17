package org.nico.servlet.toolkit.servlet

import java.util.concurrent.{ExecutorService, Executors}

import com.typesafe.config.{Config, ConfigFactory}
import org.nico.servlet.toolkit.ServletRouter
import org.nico.servlet.toolkit.http.tools.ContentType._

/**
  * Created by nicolasaubry on 13/04/16.
  */
object ScalaServletSettings {

  import scala.reflect.runtime.{universe => ru}

  val defaultEncoding = "UTF-8"

  val defaultContentType = JsonContentType

  val acceptedContentTypes = Seq(JsonContentType, XmlContentType)

  val appConfig: Config = ConfigFactory.load()

  val appRouter: ServletRouter = Class.forName(appConfig.getString("servlet.router")).newInstance.asInstanceOf[ServletRouter]

  val asyncPool: ExecutorService = Executors.newFixedThreadPool(appConfig.getInt("servlet.async.poolSize"))
  val asyncTimeout = appConfig.getLong("servlet.async.timeout")

}
