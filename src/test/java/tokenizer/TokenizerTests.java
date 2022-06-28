package tokenizer;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TokenizerTests {

  @Test
  void testNextToken() {
    Tokenizer tokenizer = new Tokenizer("+{}()");
    TokenType tokenType = tokenizer.nextToken();
    tokenizer.readChar();
    Assert.assertEquals(tokenType, TokenType.PLUS);
    tokenType = tokenizer.nextToken();
    tokenizer.readChar();
    Assert.assertEquals(tokenType, TokenType.LBRACE);
    tokenType = tokenizer.nextToken();
    tokenizer.readChar();
    Assert.assertEquals(tokenType, TokenType.RBRACE);
    tokenType = tokenizer.nextToken();
    tokenizer.readChar();
    Assert.assertEquals(tokenType, TokenType.LPAREN);
    tokenType = tokenizer.nextToken();
    tokenizer.readChar();
    Assert.assertEquals(tokenType, TokenType.RPAREN);
  }

}
