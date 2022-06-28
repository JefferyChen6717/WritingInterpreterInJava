package tokenizer;

public enum TokenType {
  ILLEGAL("ILLEGAL"),
  EOF("EOF"),

  // represent a identifier(variable)
  ITENT("ITENT"),
  INT("INT"),

  // operators
  ASSIGN("ASSIGN"),
  PLUS("PLUS"),

  // delimiters
  COMMA("COMMA"),
  SEMICOLON("SEMICOLON"),

  // '(', ')', '{', '}'
  LPAREN("LPAREN"),
  RPAREN("RPAREN"),
  LBRACE("LBRACE"),
  RBRACE("RBRACE"),

  // keywords
  FUNCTION("FUNCTION"),
  LET("LET");

  private final String value;

  TokenType(String value) {
    this.value = value;
  }
}
