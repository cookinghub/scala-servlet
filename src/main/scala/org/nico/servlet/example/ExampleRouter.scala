package org.nico.servlet.example

import org.nico.servlet.example.api.{GeoPlacesApi, UsersApi}
import org.nico.servlet.toolkit.ServletRouter
import org.nico.servlet.toolkit.http.HttpRoute
import org.nico.servlet.toolkit.http.tools.Method.{GET, POST}

/**
  * Created by nicolasaubry on 17/04/16.
  */
class ExampleRouter extends ServletRouter(

  // Users routes
  HttpRoute(POST, "/example/v1/user", UsersApi.registerUser),
  HttpRoute(POST, "/example/v1/user/login", UsersApi.loginUser),

  // GeoPlaces routes
  HttpRoute(POST, "/example/v1/geo/place", GeoPlacesApi.createPlace),
  HttpRoute(GET, "/example/v1/geo/place", GeoPlacesApi.getPlace)
)