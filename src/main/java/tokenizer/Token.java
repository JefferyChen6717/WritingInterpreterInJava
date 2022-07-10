package tokenizer;

import tokenizer.TokenDataStructure.TokenType;

public class Token {

  public TokenDataStructure.TokenType tokenType;

  public String literal;

  public Token(TokenType tokenType, String literal) {
    this.tokenType = tokenType;
    this.literal = literal;
  }

  public String getLiteral() {
    return literal;
  }

  public void setLiteral(String literal) {
    this.literal = literal;
  }
}
