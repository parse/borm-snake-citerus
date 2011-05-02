package se.citerus.crazysnake.brain

import se.citerus.crazysnake.*

class GroovyBrain extends BaseBrain {

    @Override
    Movement getNextMove(GameState state) {
        Movement.FORWARD
    }

    @Override
    String getName() {
        "GroovyBrain"
    }

}
