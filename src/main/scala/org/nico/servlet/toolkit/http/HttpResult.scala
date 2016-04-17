package org.nico.servlet.toolkit.http

import org.nico.servlet.toolkit.http.tools.BodyContent
import org.nico.servlet.toolkit.http.tools.ContentType._
import play.api.libs.json.{Json, Writes}


/**
  * Created by nicolasaubry on 13/04/16.
  */
case class HttpResult(statusCode: Int, headers: Map[String, String], body: BodyContent)

object HttpResult {

  def apply(statusCode: Int, headers: Seq[(String, String)]): HttpResult = {
    new HttpResult(statusCode, headers.toMap[String, String], BodyContent.Empty())
  }

  def apply[T](statusCode: Int, body: T, contentType: String, headers: Seq[(String, String)])(implicit writes: Writes[T]): HttpResult = {
    val bodyContent = contentType match {
      case XmlContentType.value => BodyContent.Empty() // TODO xml support
      case JsonContentType.value | _ => BodyContent.Strict(Json.toJson(body).toString(), contentType) // we fall back to json as default
    }
    new HttpResult(statusCode, headers.toMap[String, String], bodyContent)
  }

}
