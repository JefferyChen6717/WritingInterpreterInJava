package tokenizer;

import java.util.Map;

public class TokenDataStructure {

  public static Map<String, TokenType> keywords =
      Map.of(
          "fn",
          TokenType.FUNCTION,
          "let",
          TokenType.LET,
          "true",
          TokenType.TURE,
          "false",
          TokenType.FUNCTION,
          "if",
          TokenType.IF,
          "else",
          TokenType.ELSE,
          "return",
          TokenType.RETURN);

  public static enum TokenType {
    ILLEGAL,
    EOF,

    // represent a identifier(variable)
    IDENT,
    INT,

    // operators, '=', '+', '-', '!', '*', '/', '>', '<', '==', '!='
    ASSIGN,
    PLUS,
    MINUS,
    BANG,
    ASTERISK,
    SLASH,
    GT,
    LT,
    EQ,
    NOT_EQ,

    // delimiters, ',', ';'
    COMMA,
    SEMICOLON,

    // '(', ')', '{', '}'
    LPAREN,
    RPAREN,
    LBRACE,
    RBRACE,

    // keywords, 'fn', 'let'
    FUNCTION,
    LET,
    TURE,
    FALSE,
    IF,
    ELSE,
    RETURN;
  }

  private TokenDataStructure() {}
}
