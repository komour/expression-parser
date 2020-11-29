package visitor

import token.Brace
import token.NumberToken
import token.Operation
import java.util.*

class CalcVisitor : TokenVisitor {
    private val stack = Stack<Double>()

    override fun visit(token: NumberToken) {
        stack.push(token.value.toDouble())
    }

    override fun visit(token: Brace) {
        throw CalcException("Invalid token BRACE in Reverse Polish Notation")
    }

    override fun visit(token: Operation) {
        if (stack.size < 2) {
            throw CalcException("Invalid expression in Reverse Polish Notation")
        }
        val y = stack.pop()
        val x = stack.pop()
        when {
            token.isPlus -> stack.add(x + y)
            token.isMul -> stack.add(x * y)
            token.isMinus -> stack.add(x - y)
            token.isDiv -> stack.add(x / y)
        }
    }

    fun finishCalc(): Double {
        if (stack.size != 1) {
            throw CalcException("Invalid expression in Reverse Polish Notation")
        }
        return stack.pop()
    }
}