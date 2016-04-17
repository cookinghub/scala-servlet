package org.nico.servlet.toolkit.http.tools

import javax.servlet.http.HttpServletRequest

import org.nico.servlet.example.domain.{User, Users}
import org.nico.servlet.toolkit.http.HttpResult

/**
  * Created by nicolasaubry on 16/04/16.
  */
trait Security extends Results {

  def noAuthentication(request: HttpServletRequest): Either[HttpResult, Option[User]] = Right(None)

  def tokenAuthentication(request: HttpServletRequest): Either[HttpResult, Option[User]] = {
    val token = Option(request.getHeader("x-auth-token"))
    token.map(Users.authenticate).map(Right(_)).getOrElse(Left(BadRequest()))
  }

}
