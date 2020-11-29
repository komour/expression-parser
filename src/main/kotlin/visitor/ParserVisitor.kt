package visitor

import token.Brace
import token.NumberToken
import token.Operation
import token.Token
import java.util.*

class ParserVisitor : TokenVisitor {

    private val tokensRPN = ArrayList<Token>()

    private val stack = Stack<Token>()

    override fun visit(token: NumberToken) {
        tokensRPN.add(token)
    }

    override fun visit(token: Brace) {
        if (token.isOpen) {
            stack.push(token)
            return
        }
        else {
            while (!stack.empty()) {
                val top: Token = stack.peek()
                if (top is Brace) {
                    stack.pop()
                    return
                }
                tokensRPN.add(stack.pop())
            }
            throw ParserException("Invalid expression")
        }
    }

    override fun visit(token: Operation) {
        while (!stack.empty()) {
            val top: Token = stack.peek()
            if (top !is Operation || top.priority < token.priority) {
                break
            }
            tokensRPN.add(stack.pop())
        }
        stack.push(token)
    }

    fun finishParsing(): ArrayList<Token> {
        while (!stack.empty()) {
            tokensRPN.add(stack.pop())
        }
        return tokensRPN
    }
}
