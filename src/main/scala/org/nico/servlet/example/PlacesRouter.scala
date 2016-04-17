package org.nico.servlet.example

import org.nico.servlet.example.api.{PlacesApi, UsersApi}
import org.nico.servlet.toolkit.ServletRouter
import org.nico.servlet.toolkit.http.HttpRoute
import org.nico.servlet.toolkit.http.tools.Method.{GET, POST}

/**
  * Created by nicolasaubry on 17/04/16.
  */
class PlacesRouter extends ServletRouter(

  // Users routes
  HttpRoute(POST, "/myplaces/v1/user", UsersApi.registerUser),
  HttpRoute(POST, "/myplaces/v1/user/login", UsersApi.loginUser),

  // Places routes
  HttpRoute(POST, "/myplaces/v1/place", PlacesApi.createPlace),
  HttpRoute(GET, "/myplaces/v1/place", PlacesApi.getPlace)
)