import token.Tokenizer
import visitor.*

fun main() {
    print("Enter expression: ")
    // (23 + 10) * 5 - 3 * (32 + 5) * (10 - 4 * 5) + 8 / 2
    val input = readLine()
    if (input == null) {
        print("null input")
        return
    } else {
        try {
            val tokenizer = Tokenizer(input)
            var tokens = tokenizer.tokenize()
            val parserVisitor = ParserVisitor()
            for (token in tokens) {
                token.accept(parserVisitor)
            }
            tokens = parserVisitor.finishParsing()
            val printVisitor = PrintVisitor()
            for (token in tokens) {
                token.accept(printVisitor)
            }
            val calcVisitor = CalcVisitor()
            for (token in tokens) {
                token.accept(calcVisitor)
            }
            println("\nResult is ${calcVisitor.finishCalc()}")
        } catch (e: CalcException) {
            System.err.println("\nCalculating error: ${e.message}")
        } catch (e: ParserException) {
            System.err.println("\nParsing error: ${e.message}")
        }
    }
}