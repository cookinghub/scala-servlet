package org.nico.servlet.toolkit

import org.nico.servlet.toolkit.http.tools.ContentType._
import org.nico.servlet.toolkit.http.tools.{Results, Security}
import org.nico.servlet.toolkit.http.{HttpAction, HttpRequest, HttpResult}
import org.slf4j.Logger
import play.api.libs.json._


/**
  * Created by nicolasaubry on 13/04/16.
  */

trait ActionController extends Results with Security {

  // Actions
  def Action(actionBlock: HttpRequest => HttpResult): HttpAction = {
    new HttpAction(noAuthentication, actionBlock)
  }

  def SecuredAction(actionBlock: HttpRequest => HttpResult): HttpAction = {
    new HttpAction(tokenAuthentication, actionBlock)
  }

  // Utilities
  def paramAs[T](key: String)(implicit httpRequest: HttpRequest): Option[T] = {
    httpRequest.queryParams.get(key).flatten.map(_.asInstanceOf[T])
  }

  def bodyAs[T](implicit httpRequest: HttpRequest, reads: Reads[T]): Either[HttpResult, T] = {
    httpRequest.contentType match {
      case XmlContentType.value => null // TODO xml support
      case JsonContentType.value | _ => fromJson[T]
    }
  }

  private def fromJson[T](implicit httpRequest: HttpRequest, reads: Reads[T]): Either[HttpResult, T] = {
    Json.parse(httpRequest.body.content).validate[T] match {
      case JsSuccess(obj, _) => Right(obj)
      case JsError(err) => Left(BadRequest(ErrorInvalidJson(s"$err")))
    }
  }

}

