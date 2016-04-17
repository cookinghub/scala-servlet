package org.nico.servlet.toolkit.http.tools

/**
  * Created by nicolasaubry on 13/04/16.
  */
trait BodyContent {

  def contentType: String

  def content: String

}

object BodyContent {

  final case class Empty() extends BodyContent {
    val contentType = ""
    val content = ""
  }

  final case class Strict(content: String, contentType: String) extends BodyContent

}
