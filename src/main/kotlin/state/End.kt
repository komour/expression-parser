package state

import token.Token

class End(input: String, pos: Int) : State(input, pos) {
    override fun nextState(tokens: MutableList<Token>): State {
        return this
    }
}