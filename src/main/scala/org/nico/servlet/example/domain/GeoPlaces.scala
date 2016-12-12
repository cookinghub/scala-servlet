package org.nico.servlet.example.domain

import java.util.concurrent.ConcurrentHashMap

import play.api.libs.json.Json

import scala.collection.concurrent
import scala.collection.convert.decorateAsScala._

/**
  * Created by nicolasaubry on 13/04/16.
  */
case class Location(lat: Double, lon: Double)
case class Place(id: String, location: Location, name: String, aliases: Seq[String], city: Option[String], country: Option[String], zOffsetMinutes: Option[Int])
case class GeoPlaces(places: Seq[Place])

case class CreatePlace(lat: Double, lon: Double, name: String)
case class PlaceCreated(placeId: String)

object GeoPlaces {

  private val places: concurrent.Map[String, Place] = new ConcurrentHashMap[String, Place]().asScala

  def create(createPlace: CreatePlace): PlaceCreated = {
    val place = Place(java.util.UUID.randomUUID.toString, Location(createPlace.lat, createPlace.lon), createPlace.name, Seq(), None, None, None)
    places.put(place.id, place)
    return PlaceCreated(place.id)
  }

  def findOne(id: String): Option[Place] = places.get(id)

  def delete(id: String) = places.remove(id)

  def all() = places.toList

  def clear() = places.clear()

}