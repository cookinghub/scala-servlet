package org.nico.servlet.example.domain

import java.util.concurrent.ConcurrentHashMap

import scala.collection.concurrent
import scala.collection.convert.decorateAsScala._

/**
  * Created by nicolasaubry on 13/04/16.
  */
case class User(id: String, username: String, password: String, name: Option[String])
case class Users(users: Seq[User])

case class RegisterUser(username: String, password: String, name: Option[String])
case class LoginUser(username: String, password: String)
case class UserToken(token: String)

object Users {

  private val users: concurrent.Map[String, User] = new ConcurrentHashMap[String, User]().asScala
  private val tokens: concurrent.Map[String, String] = new ConcurrentHashMap[String, String]().asScala

  def create(registerUser: RegisterUser): UserToken = {
    val user = User(java.util.UUID.randomUUID.toString, registerUser.username, registerUser.password, registerUser.name)
    users.put(user.id, user)
    val token = java.util.UUID.randomUUID.toString
    tokens.put(token, user.id)
    UserToken(token)
  }

  def login(loginUser: LoginUser): Option[UserToken] = {
    users.values.find(u => u.username == loginUser.username && u.password == loginUser.password) map { u =>
      val token = java.util.UUID.randomUUID.toString
      tokens.put(token, u.id)
      UserToken(token)
    }
  }

  def authenticate(token: String): Option[User] = tokens.get(token).flatMap(users.get(_))

  def save(userId: String, user: User) = users.put(userId, user)

}
