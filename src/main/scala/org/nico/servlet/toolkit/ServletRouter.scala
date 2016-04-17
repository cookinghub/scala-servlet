package org.nico.servlet.toolkit

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import com.typesafe.scalalogging.LazyLogging
import org.nico.servlet.toolkit.http.HttpRoute
import org.nico.servlet.toolkit.http.tools.{Method, StatusCode}

/**
  * Created by nicolasaubry on 17/04/16.
  */
abstract class ServletRouter(routes: HttpRoute*) extends LazyLogging {

  val routesMap: Map[String, HttpRoute] = routes.map(r => (r.method.value + r.uri, r)).toMap

  def dispatch(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    val key = method.value+request.getRequestURI
    logger.debug(s"Dispatching to $key")
    routesMap.get(key) match {
      case Some(route) => {
        if(route.async) route.action.executeAsync(request, response)
        else route.action.execute(request, response)
      }
      case None => response.setStatus(StatusCode.NOT_FOUND)
    }
  }
}
