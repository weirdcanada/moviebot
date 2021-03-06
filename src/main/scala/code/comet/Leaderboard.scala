package code
package comet

import net.liftweb._
import http._
import util._
import Helpers._

class Leaderboard extends CometActor with CometListener {
  // state
  private var msgs: Vector[LeaderboardMessage] = Vector[LeaderboardMessage]()

  private val totalLeaders = 5

  /**
   * When component is insantiated, register as a listener with teh chat server
   */
  def registerWith = LeaderboardServer

  /**
   * Process messages
   */
  override def lowPriority = {
    case v: Vector[LeaderboardMessage] => msgs = v; reRender()
  }

  /** 
   * put the messages in the li elements and clear elements that have clearable
   */
  def render = "name=repeater *" #> msgs.sortBy{ x => - x.correctGuesses }.take(totalLeaders).map { x =>
    ".username *" #> x.user &
    ".correctGuesses *" #> x.correctGuesses &
    ".incorrectGuesses *" #> x.incorrectGuesses 
  }  & ClearClearable
}
