package state

import token.Token

abstract class State(val inputString: String, var pos: Int) {

    abstract fun nextState(tokens: MutableList<Token>): State

    open fun checkSpaceAndEnd(): State? {
        return when {
            pos >= inputString.length -> {
                End(inputString, pos)
            }
            Character.isWhitespace(inputString[pos]) -> {
                Space(inputString, pos)
            }
            else -> null
        }
    }
}