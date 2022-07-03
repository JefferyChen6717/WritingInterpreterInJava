package tokenizer;

import java.util.Map;

public class TokenDataStructure {

  public static Map<String, TokenType> keywords =
      Map.of("fn", TokenType.FUNCTION, "let", TokenType.LET);

  public static enum TokenType {
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
    FUNCTION("fn"),
    LET("let");

    private final String value;

    TokenType(String value) {
      this.value = value;
    }
  }

  private TokenDataStructure() {}
}
