package org.nico.servlet.toolkit.http.tools

import javax.servlet.http.HttpServletResponse

/**
  * Created by nicolasaubry on 16/04/16.
  */
object StatusCode {

  val OK = HttpServletResponse.SC_OK
  val BAD_REQUEST = HttpServletResponse.SC_BAD_REQUEST
  val UNAUTHORIZED = HttpServletResponse.SC_UNAUTHORIZED
  val NOT_FOUND = HttpServletResponse.SC_NOT_FOUND
  val UNSUPPORTED_MEDIA_TYPE = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE

}
