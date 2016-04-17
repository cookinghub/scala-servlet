package org.nico.servlet.toolkit.http.tools

/**
  * Created by nicolasaubry on 17/04/16.
  */
sealed trait Method {
  def value: String
}
object Method {

  case object GET extends Method {
    val value = "GET"
  }
  case object POST extends Method {
    val value = "POST"
  }
  case object PUT extends Method {
    val value = "PUT"
  }
  case object DELETE extends Method {
    val value = "DELETE"
  }
}
