package tokenizer;

public class Token {

  TokenType tokenType;

  String literal;

  public Token(TokenType tokenType, String literal) {
    this.tokenType = tokenType;
    this.literal = literal;
  }
}
