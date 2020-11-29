package state

import token.Token

class Space(input: String, pos: Int) : State(input, pos) {
    override fun nextState(tokens: MutableList<Token>): State {
        return when {
            pos >= inputString.length -> {
                End(inputString, pos)
            }
            Character.isWhitespace(inputString[pos]) -> {
                pos++
                this
            }
            else -> if (Character.isDigit(inputString[pos])) {
                Number(inputString, pos)
            } else Start(inputString, pos)
        }
    }
}
