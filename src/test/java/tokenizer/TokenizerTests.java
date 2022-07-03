package tokenizer;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import tokenizer.TokenDataStructure.TokenType;

public class TokenizerTests {

  @Test
  void testNextToken() {
    Tokenizer tokenizer = new Tokenizer("+{}()");
    Token actualToken;
    Token expectedToken;

    List<TokenType> tokenTypes =
        List.of(
            TokenType.PLUS,
            TokenType.LBRACE,
            TokenType.RBRACE,
            TokenType.LPAREN,
            TokenType.RPAREN,
            TokenType.EOF);
    List<String> tokenLiterals = List.of("+", "{", "}", "(", ")", "EOF");
    for (int i = 0; i < tokenTypes.size(); i++) {
      actualToken = tokenizer.nextToken();
      expectedToken = new Token(tokenTypes.get(i), tokenLiterals.get(i));
      Assert.assertEquals(actualToken.tokenType, expectedToken.tokenType);
      Assert.assertEquals(actualToken.literal, expectedToken.literal);
    }

    tokenizer = new Tokenizer("let var = 5");
    tokenTypes = List.of(TokenType.LET, TokenType.ITENT, TokenType.ASSIGN, TokenType.INT, TokenType.EOF);
    tokenLiterals = List.of("let", "var", "=", "5", "EOF");
    for (int i = 0; i < tokenTypes.size(); i++) {
      actualToken = tokenizer.nextToken();
      expectedToken = new Token(tokenTypes.get(i), tokenLiterals.get(i));
      Assert.assertEquals(actualToken.tokenType, expectedToken.tokenType);
      Assert.assertEquals(actualToken.literal, expectedToken.literal);
    }
  }
}
