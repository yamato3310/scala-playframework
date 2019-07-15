package controllers

import java.sql._
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.db._
import akka.util._
import play.api.http._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(db: Database, cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action {
    var msg = "database record:<br><ul>"
    try {
      db.withConnection { conn => 
        val stmt = conn.createStatement
        val rs = stmt.executeQuery("select * from people")
        while (rs.next) {
          msg += "<li>" + rs.getInt("id") + ":" + rs.getString("name") + "</li>"
        }
        msg += "</ul>"
      }
    } catch {
      case e:SQLException =>
        msg = "<li>no record...</li>"
    }
    Ok(views.html.index(
      msg
    ))
  }
}
