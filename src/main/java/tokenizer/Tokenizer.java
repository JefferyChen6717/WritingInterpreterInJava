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

  /** assign next char to ch */
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
   *
   * @return TokenType
   */
  public Token nextToken() {

    Token token;
    switch (ch) {
      case '=':
        return new Token(TokenType.ASSIGN, String.valueOf(ch));
      case ';':
        return new Token(TokenType.SEMICOLON, String.valueOf(ch));
      case '(':
        return new Token(TokenType.LPAREN, String.valueOf(ch));
      case ')':
        return new Token(TokenType.RPAREN, String.valueOf(ch));
      case ',':
        return new Token(TokenType.COMMA, String.valueOf(ch));
      case '+':
        return new Token(TokenType.PLUS, String.valueOf(ch));
      case '{':
        return new Token(TokenType.LBRACE, String.valueOf(ch));
      case '}':
        return new Token(TokenType.RBRACE, String.valueOf(ch));
      case 0:
        return new Token(TokenType.EOF, String.valueOf(ch));
      default:
        return isIdentifierBeginChar(ch)
            ? new Token(TokenType.ITENT, readIdentifier())
            : new Token(TokenType.ILLEGAL, "");
    }
  }

  public String readIdentifier() {
    return null;
  }

  private boolean isIdentifierBeginChar(char c) {
    return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_';
  }
}
