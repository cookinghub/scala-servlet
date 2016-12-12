package org.nico.servlet.example.api

import org.nico.servlet.example.domain._
import org.nico.servlet.toolkit.ActionController
import play.api.libs.json.Json

/**
  * Created by nicolasaubry on 13/04/16.
  */
object GeoPlacesApi extends ActionController {

  // Json formatters
  implicit val createPlaceReads = Json.reads[CreatePlace]

  implicit val placeCreatedWrites = Json.writes[PlaceCreated]
  implicit val locationWrites = Json.writes[Location]
  implicit val placeWrites = Json.writes[Place]

  /**
    * @return Ok if creation is successful, BadRequest otherwise
    */
  def createPlace = Action { implicit request =>
    bodyAs[CreatePlace].fold(badRequest => badRequest, { body =>
      val created = GeoPlaces.create(body)
      Ok(created)
    })
  }

  /**
    * @return Ok if matching place, NotFound if no match, BadRequest otherwise
    */
  def getPlace = Action { implicit request =>
    paramAs[String]("placeId").map { placeId =>
      GeoPlaces.findOne(placeId).map(p => Ok(p)).getOrElse(NotFound())
    }.getOrElse(BadRequest())
  }

}
