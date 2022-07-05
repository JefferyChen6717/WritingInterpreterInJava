package tokenizer;

import java.util.function.Function;
import tokenizer.TokenDataStructure.TokenType;

public class Tokenizer {

  String input;

  // index of current char
  int position;

  // index of the char next to the current char
  // readPosition = position + 1
  int readPosition;

  // current char
  char curChar;

  public Tokenizer(String input) {
    this.input = input;
    this.position = -1;
    this.readPosition = 0;
    this.curChar = 0;
    readChar();
  }

  /** assign next char to ch */
  public void readChar() {
    if (readPosition >= input.length()) {
      curChar = 0;
    } else {
      curChar = input.charAt(readPosition);
    }
    position = readPosition;
    readPosition++;
  }

  /** evaluate TokenType by ch */
  public Token nextToken() {
    skipWhiteSpace();
    Token token;
    switch (curChar) {
      case '=':
        if (peekChar() == '=') {
          token = new Token(TokenType.EQ, curChar + String.valueOf(peekChar()));
          readChar();
        } else {
          token = new Token(TokenType.ASSIGN, String.valueOf(curChar));
        }
        break;
      case ';':
        token = new Token(TokenType.SEMICOLON, String.valueOf(curChar));
        break;
      case '(':
        token = new Token(TokenType.LPAREN, String.valueOf(curChar));
        break;
      case ')':
        token = new Token(TokenType.RPAREN, String.valueOf(curChar));
        break;
      case ',':
        token = new Token(TokenType.COMMA, String.valueOf(curChar));
        break;
      case '+':
        token = new Token(TokenType.PLUS, String.valueOf(curChar));
        break;
      case '-':
        token = new Token(TokenType.MINUS, String.valueOf(curChar));
        break;
      case '!':
        if (peekChar() == '=') {
          token = new Token(TokenType.NOT_EQ, curChar + String.valueOf(peekChar()));
          readChar();
        } else {
          token = new Token(TokenType.BANG, String.valueOf(curChar));
        }
        break;
      case '*':
        token = new Token(TokenType.ASTERISK, String.valueOf(curChar));
        break;
      case '/':
        token = new Token(TokenType.SLASH, String.valueOf(curChar));
        break;
      case '>':
        token = new Token(TokenType.GT, String.valueOf(curChar));
        break;
      case '<':
        token = new Token(TokenType.LT, String.valueOf(curChar));
        break;
      case '{':
        token = new Token(TokenType.LBRACE, String.valueOf(curChar));
        break;
      case '}':
        token = new Token(TokenType.RBRACE, String.valueOf(curChar));
        break;
      case 0:
        token = new Token(TokenType.EOF, String.valueOf(TokenType.EOF.toString()));
        break;
      default:
        // check the string is a keyword or a variable name
        if (isIdentifierBeginChar(curChar)) {
          String ident = readNext(this::isIdentifierChar);
          // readChar() has been executed in readNext() so just return
          return new Token(lookupIdent(ident), ident);
        } else if (isDigit(curChar)) {
          return new Token(TokenType.INT, readNext(this::isDigit));
        } else {
          token = new Token(TokenType.ILLEGAL, "");
        }
    }
    readChar();
    return token;
  }

  /** check ident is an identifier or a keyword and return corresponding TokenType */
  private TokenType lookupIdent(String ident) {
    return TokenDataStructure.keywords.getOrDefault(ident, TokenType.ITENT);
  }

  /**
   * readIdentifier() and readNumber() have almost same code logic except one predicate func so can
   * use readNext() with a parameter of predicate func instead
   */
  private String readNext(Function<Character, Boolean> func) {
    int startPosition = position;
    do {
      readChar();
    } while (func.apply(curChar));
    return input.substring(startPosition, position);
  }

  private boolean isIdentifierBeginChar(char c) {
    return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_';
  }

  private boolean isIdentifierChar(char c) {
    return isIdentifierBeginChar(c) || c >= '0' && c <= '9';
  }

  private boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  private void skipWhiteSpace() {
    while (curChar == ' ' || curChar == '\t' || curChar == '\n' || curChar == '\r') {
      readChar();
    }
  }

  private char peekChar() {
    return readPosition >= input.length() ? 0 : input.charAt(readPosition);
  }
}
