package org.nico.servlet.toolkit.http

import org.nico.servlet.example.domain.User
import org.nico.servlet.toolkit.http.tools.BodyContent

/**
  * Created by nicolasaubry on 13/04/16.
  */
case class HttpRequest(user: Option[User],
                       headers: Map[String, Option[String]],
                       queryParams: Map[String, Option[String]],
                       contentType: String,
                       body: BodyContent)
