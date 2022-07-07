package tokenizer;

import org.testng.annotations.Test;
import tokenizer.TokenDataStructure.TokenType;

public class ReplTests {
  @Test
  void testStart() {
    Repl repl = new Repl();
    String code = repl.start();
    Tokenizer tokenizer = new Tokenizer(code);
    Token token;
    do {
      token = tokenizer.nextToken();
      System.out.println(token.tokenType + " " + token.literal);
    } while (token.tokenType != TokenType.EOF);
  }
}
