package org.nico.servlet.toolkit.http.tools

import org.nico.servlet.toolkit.http.{HttpRequest, HttpResult}
import play.api.libs.json.{JsArray, JsNumber, Json, Writes}

/**
  * Created by nicolasaubry on 16/04/16.
  */
trait Results {

  // Error
  case class Error(code: Int, messages: Seq[String])
  implicit val errorWrites = Writes((error: Error) => Json.obj(
    "code" -> JsNumber(error.code),
    "messages" -> JsArray(error.messages.map(Json.toJson(_)))
  ))

  // Errors
  def ErrorInvalidJson(messages: String*) = Error(4001, messages)

  // Results
  def Ok(headers: (String, String)*): HttpResult = HttpResult(StatusCode.OK, headers)
  def Ok[T](body: T, headers: (String, String)*)(implicit httpRequest: HttpRequest, writes: Writes[T]): HttpResult = {
    HttpResult[T](StatusCode.OK, body, httpRequest.contentType, headers)
  }
  def BadRequest(headers: (String, String)*): HttpResult = HttpResult(StatusCode.BAD_REQUEST, headers)
  def BadRequest(body: Error, headers: (String, String)*)(implicit httpRequest: HttpRequest): HttpResult = {
    HttpResult[Error](StatusCode.OK, body, httpRequest.contentType, headers)
  }
  def NotFound(headers: (String, String)*): HttpResult = HttpResult(StatusCode.NOT_FOUND, headers)
  def Unauthorized(headers: (String, String)*): HttpResult = HttpResult(StatusCode.UNAUTHORIZED, headers)

}
