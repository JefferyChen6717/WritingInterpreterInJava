package tokenizer;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TokenizerTests {

  @Test
  void testNextToken() {
    Tokenizer tokenizer = new Tokenizer("+{}()");
    Token token = tokenizer.nextToken();
    tokenizer.readChar();
    Assert.assertEquals(token, new Token(TokenType.PLUS, "+"));
    token = tokenizer.nextToken();
    tokenizer.readChar();
    Assert.assertEquals(token, new Token(TokenType.LBRACE, "{"));
    token = tokenizer.nextToken();
    tokenizer.readChar();
    Assert.assertEquals(token, new Token(TokenType.RBRACE, "}"));
    token = tokenizer.nextToken();
    tokenizer.readChar();
    Assert.assertEquals(token, new Token(TokenType.LPAREN, "("));
    token = tokenizer.nextToken();
    tokenizer.readChar();
    Assert.assertEquals(token, new Token(TokenType.RPAREN, ")"));
  }
}
