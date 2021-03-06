package code
package snippet

import net.liftweb._
import http._
import js._
import JsCmds._
import JE._
import common.{Box,Full,Empty,Failure,ParamFailure}

import comet.{ChatServer,MovieBot,ChatMessage}
import scala.xml.Text

/**
 * Snippet to set Username
 */
object SetUsername {

  object username extends SessionVar[Box[String]](Empty)
  
  def render = SHtml.onSubmit(s => {
    username.set(Full(s))
    SetHtml("currentUsername", Text("You may not always be: " + s))
  })
}
