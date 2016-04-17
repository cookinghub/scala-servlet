package org.nico.servlet.toolkit.http.tools

/**
  * Created by nicolasaubry on 16/04/16.
  */
sealed trait ContentType {
  def value: String
}

object ContentType {

  case object JsonContentType extends ContentType {
    val value = "application/json"
  }

  case object XmlContentType extends ContentType {
    val value = "application/xml"
  }

}
