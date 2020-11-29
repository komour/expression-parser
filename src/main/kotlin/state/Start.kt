package state

import token.Brace
import token.Operation
import token.Token
import token.TokenizerException

class Start(input: String, pos: Int) : State(input, pos) {

    override fun nextState(tokens: MutableList<Token>): State {
        val checked: State? = checkSpaceAndEnd()
        if (checked != null) {
            return checked
        }
        val curChar = inputString[pos]
        if (Character.isDigit(curChar)) {
            return Number(inputString, pos)
        }
        val token = when (curChar) {
            '(', ')' -> {
                Brace(curChar)
            }
            '+', '-', '*', '/' -> {
                Operation(curChar)
            }
            else -> {
                throw TokenizerException("Invalid char `$curChar` in Start.nextState() method")
            }
        }
        tokens.add(token)
        pos++
        return this
    }
}

