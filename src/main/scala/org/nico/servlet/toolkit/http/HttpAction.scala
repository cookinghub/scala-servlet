package org.nico.servlet.toolkit.http

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import org.apache.commons.io.IOUtils
import org.nico.servlet.example.domain.User
import org.nico.servlet.toolkit.http.tools.BodyContent
import org.nico.servlet.toolkit.servlet.ScalaServletSettings

import scala.collection.JavaConversions._


/**
  * Created by nicolasaubry on 13/04/16.
  */
class HttpAction(authentication: HttpServletRequest => Either[HttpResult, Option[User]],
                 actionHandler: HttpRequest => HttpResult) {

  /**
    *
    * @param servletRequest
    * @param servletResponse
    */
  def execute(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse): Unit = {
    val result = handleRequest(servletRequest, authentication) match {
      case Left(unauthorized) => unauthorized
      case Right(action) => actionHandler(action)
    }
    handleResponse(servletResponse, result)
  }

  /**
    *
    * @param servletRequest
    * @param response
    */
  def executeAsync(servletRequest: HttpServletRequest,
                   response: HttpServletResponse): Unit = {
    val async = servletRequest.startAsync()
    async.setTimeout(ScalaServletSettings.asyncTimeout)
    ScalaServletSettings.asyncPool.execute(new Runnable {
      override def run(): Unit = {
        execute(async.getRequest.asInstanceOf[HttpServletRequest],
          async.getResponse.asInstanceOf[HttpServletResponse])
        async.complete()
      }
    })
  }

  /**
    * @param request
    * @param authentication
    * @return
    */
  private def handleRequest(request: HttpServletRequest,
                            authentication: HttpServletRequest => Either[HttpResult, Option[User]]): Either[HttpResult, HttpRequest] = {
    val user = authentication(request) match {
      case Left(errorResult) => return Left(errorResult)
      case Right(maybeUser) => maybeUser
    }
    val headers = request.getHeaderNames.map(s => (s, Option(request.getHeader(s)))).toMap

    val queryParams = request.getParameterMap.map(p => (p._1, Option(p._2.mkString("|")))).toMap[String, Option[String]]

    val contentType = Option(request.getContentType).getOrElse(ScalaServletSettings.defaultContentType.value)

    val body = Option(request.getInputStream) match {
      // scala.io.Source is slow and buggy -> using apache commons to read the inputstream
      case Some(is) => BodyContent.Strict(IOUtils.toString(request.getInputStream, ScalaServletSettings.defaultEncoding), contentType)
      case None => BodyContent.Empty()
    }
    Right(HttpRequest(user, headers, queryParams, contentType, body))
  }

  /**
    * @param response
    * @param result
    */
  private def handleResponse(response: HttpServletResponse, result: HttpResult): Unit = {
    response.setStatus(result.statusCode)
    if (!result.body.isInstanceOf[BodyContent.Empty]) {
      response.setCharacterEncoding(ScalaServletSettings.defaultEncoding)
      response.setContentType(result.body.contentType)
      response.getWriter.write(result.body.content)
    }
  }


}
