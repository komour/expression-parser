package visitor

import token.Brace
import token.NumberToken
import token.Operation

class PrintVisitor : TokenVisitor {
    override fun visit(token: NumberToken) {
        print("NUMBER(" + token.value.toString() + ") ")
    }

    override fun visit(token: Brace) {
        when {
            token.isOpen -> {
                print("OPEN ")
            }
            token.isClosed -> {
                print("CLOSE ")
            }
        }
    }

    override fun visit(token: Operation) {
        when {
            token.isPlus -> {
                print("PLUS ")
            }
            token.isMinus -> {
                print("MINUS ")
            }
            token.isMul -> {
                print("MUL ")
            }
            token.isDiv -> {
                print("DIV ")
            }
        }
    }
}