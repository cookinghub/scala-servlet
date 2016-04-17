package org.nico.servlet.toolkit.servlet

import javax.servlet.annotation.WebListener
import javax.servlet.{ServletContextEvent, ServletContextListener}

/**
  * Created by nicolasaubry on 17/04/16.
  */
@WebListener
class ScalaServletInit extends ServletContextListener {

  override def contextInitialized(sce: ServletContextEvent): Unit = {}

  override def contextDestroyed(sce: ServletContextEvent): Unit = {
    ScalaServletSettings.asyncPool.shutdown()
  }


}
