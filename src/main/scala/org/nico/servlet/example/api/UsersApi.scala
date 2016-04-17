package org.nico.servlet.example.api

import org.nico.servlet.example.domain.{LoginUser, _}
import org.nico.servlet.toolkit.ActionController
import play.api.libs.json.Json

/**
  * Created by nicolasaubry on 17/04/16.
  */
object UsersApi extends ActionController {

  // Json formatters
  implicit val registerUserReads = Json.reads[RegisterUser]
  implicit val loginUserReads = Json.reads[LoginUser]

  implicit val userTokenWrites = Json.writes[UserToken]

  /**
    * @return Ok if creation is successful, BadRequest otherwise
    */
  def registerUser = Action { implicit request =>
    bodyAs[RegisterUser].fold(badRequest => badRequest, { body =>
      val userToken = Users.create(body)
      Ok(userToken)
    })
  }

  def loginUser = Action { implicit request =>
    bodyAs[LoginUser].fold(badRequest => badRequest, { body =>
      Users.login(body) match {
        case Some(userToken) => Ok(userToken)
        case None => Unauthorized()
      }
    })
  }

}
