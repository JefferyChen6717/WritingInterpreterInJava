package tokenizer;

import tokenizer.TokenDataStructure.TokenType;

public class Token {

  TokenDataStructure.TokenType tokenType;

  String literal;

  public Token(TokenType tokenType, String literal) {
    this.tokenType = tokenType;
    this.literal = literal;
  }
}
