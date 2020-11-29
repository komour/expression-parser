package token

import visitor.TokenVisitor

class Brace(curChar: Char) : Token {
    private var type: BraceType = when (curChar) {
        '(' -> {
            BraceType.OPEN
        }
        ')' -> {
            BraceType.CLOSE
        }
        else -> {
            throw TokenizerException("Invalid brace char \'$curChar\'")
        }
    }
    val isOpen: Boolean
        get() = type == BraceType.OPEN

    val isClosed: Boolean
        get() = type == BraceType.CLOSE

    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}


