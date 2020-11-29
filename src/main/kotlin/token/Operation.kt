package token

import visitor.TokenVisitor

class Operation(curChar: Char) : Token {

    private var type: OperationType = when (curChar) {
        '+' -> OperationType.PLUS
        '-' -> OperationType.MINUS
        '*' -> OperationType.MUL
        '/' -> OperationType.DIV
        else -> throw TokenizerException("Invalid char '$curChar'")
    }

    val isPlus: Boolean
        get() = type == OperationType.PLUS

    val isMinus: Boolean
        get() = type == OperationType.MINUS

    val isMul: Boolean
        get() = type == OperationType.MUL

    val isDiv: Boolean
        get() = type == OperationType.DIV

    val priority: Int
        get() = if (isPlus || isMinus) {
            1
        } else 2

    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}

