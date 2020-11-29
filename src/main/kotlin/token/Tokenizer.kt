package token

import state.End
import state.Start
import state.State
import java.util.*

class Tokenizer(input: String) {
    private var state: State = Start(input, 0)

    fun tokenize(): List<Token> {
        val tokens: MutableList<Token> = ArrayList()
        while (state !is End) {
            state = state.nextState(tokens)
        }
        return tokens
    }
}
