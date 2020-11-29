package state

import token.NumberToken
import token.Token

class Number(input: String, pos: Int) : State(input, pos) {
    private var value = 0

    override fun nextState(tokens: MutableList<Token>): State {
        val checked: State? = checkSpaceAndEnd()
        when {
            checked != null -> {
                tokens.add(NumberToken(value))
                return checked
            }
            !Character.isDigit(inputString[pos]) -> {
                tokens.add(NumberToken(value))
                return Start(inputString, pos)
            }
            else -> {
                value = value * 10 + Character.getNumericValue(inputString[pos])
                val res = Number(inputString, pos + 1)
                res.value = value
                return res
            }
        }
    }
}