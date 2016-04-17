package org.nico.servlet.toolkit.http

import org.nico.servlet.toolkit.http.tools.Method

/**
  * Created by nicolasaubry on 16/04/16.
  */
case class HttpRoute(method: Method, uri: String, action: HttpAction, async: Boolean)

object HttpRoute {
  def apply(method: Method, uri: String, action: HttpAction): HttpRoute = HttpRoute(method, uri, action, false)
}
