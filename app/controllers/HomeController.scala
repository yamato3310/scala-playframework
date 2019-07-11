package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import akka.util._
import play.api.http._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index(id:Int, name:Option[String]) = Action { request =>
    val param:String = name.getOrElse("")
    var message = "<p>nameはありません</p>"
    if (param != "") {
      message = "<p>nameが送られました</p>"
    }
    val session = request.session.get("name")
    val sessionvalue = session.getOrElse("no-session")
    message += "<p>session:" + sessionvalue + "</p>"
    val res = Ok("<title>Hello!</title><h1>Hello!</h1>" + message).as("text/html")
    if (param != "") {
      res.withSession(request.session + ("name" -> param))
    }  else {
      res
    }
  }
}
