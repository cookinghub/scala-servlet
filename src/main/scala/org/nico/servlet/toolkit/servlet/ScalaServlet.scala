package org.nico.servlet.toolkit.servlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}
import org.nico.servlet.toolkit.http.tools.Method

/**
  * Created by nicolasaubry on 17/03/16.
  */
@WebServlet(urlPatterns = Array("/*"), asyncSupported = true)
class ScalaServlet extends HttpServlet {

  val router = ScalaServletSettings.appRouter

  override def doGet(request: HttpServletRequest, response: HttpServletResponse) = router.dispatch(Method.GET, request, response)

  override def doPost(request: HttpServletRequest, response: HttpServletResponse) = router.dispatch(Method.POST, request, response)

  override def doPut(request: HttpServletRequest, response: HttpServletResponse) = router.dispatch(Method.PUT, request, response)

  override def doDelete(request: HttpServletRequest, response: HttpServletResponse) = router.dispatch(Method.DELETE, request, response)


}
