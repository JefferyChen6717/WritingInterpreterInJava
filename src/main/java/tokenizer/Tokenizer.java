package tokenizer;

public class Tokenizer {

  String input;

  // index of current char
  int position;

  // index of the char next to the current char
  // readPosition = position + 1
  int readPosition;

  // current char
  char ch;

  public Tokenizer(String input) {
    this.input = input;
    this.position = 0;
    this.readPosition = 0;
    this.ch = 0;
    readChar();
  }

  /**
   * assign next char to ch
   */
  public void readChar() {
    if (readPosition >= input.length()) {
      ch = 0;
    } else {
      ch = input.charAt(readPosition);
    }
    position = readPosition;
    readPosition++;
  }

  /**
   * evaluate TokenType by ch
   * @return TokenType
   */
  public TokenType nextToken() {
    return switch (ch) {
      case '=' -> TokenType.ASSIGN;
      case ';' -> TokenType.SEMICOLON;
      case '(' -> TokenType.LPAREN;
      case ')' -> TokenType.RPAREN;
      case ',' -> TokenType.COMMA;
      case '+' -> TokenType.PLUS;
      case '{' -> TokenType.LBRACE;
      case '}' -> TokenType.RBRACE;
      case 0 -> TokenType.EOF;
      default -> TokenType.ILLEGAL;
    };
  }

  /**
   * generate a new token
   * @param tokenType
   * @param ch
   * @return new token
   */
  public Token newToken(TokenType tokenType, char ch) {
    return new Token(tokenType, String.valueOf(ch));
  }
}
