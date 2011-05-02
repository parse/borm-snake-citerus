package se.citerus.crazysnake.brain

import se.citerus.crazysnake._

class ScalaBrain extends BaseBrain {

    override def getNextMove(state: GameState) = Movement.FORWARD

    override def getName = "ScalaBrain"

}