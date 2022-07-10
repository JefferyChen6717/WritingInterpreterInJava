package parser;

import java.util.ArrayList;
import java.util.List;
import parser.Nodes.Identifier;
import parser.Nodes.LetStatement;
import parser.Nodes.ReturnStatement;
import tokenizer.Token;
import tokenizer.TokenDataStructure.TokenType;
import tokenizer.Tokenizer;

public class Parser {
  private Tokenizer tokenizer;
  private Token curToken;
  private Token peekToken;
  private List<String> errors;

  public Parser(Tokenizer tokenizer) {
    this.tokenizer = tokenizer;
    this.curToken = tokenizer.nextToken();
    this.peekToken = tokenizer.nextToken();
    this.errors = new ArrayList<>();
  }

  private void nextToken() {
    this.curToken = this.peekToken;
    this.peekToken = tokenizer.nextToken();
  }

  public ProgramNode parseProgram() {
    ProgramNode programNode = new ProgramNode();
    while (!this.curTokenIs(TokenType.EOF)) {
      Statement statement = parseStatement();
      if (statement != null) {
        programNode.statements.add(statement);
      }
      this.nextToken();
    }
    return programNode;
  }

  private Statement parseStatement() {
    switch (this.curToken.tokenType) {
      case LET:
        return parseLetStatement();
      case RETURN:
        return parseReturnStatement();
      default:
        return null;
    }
  }

  private LetStatement parseLetStatement() {
    LetStatement letStatement = new LetStatement(this.curToken);
    if (!this.expectPeek(TokenType.IDENT)) {
      return null;
    }
    letStatement.name = new Identifier(this.curToken, this.curToken.literal);
    if (!this.expectPeek(TokenType.ASSIGN)) {
      return null;
    }
    while (!expectPeek(TokenType.SEMICOLON)) {
      this.nextToken();
    }
    return letStatement;
  }

  private ReturnStatement parseReturnStatement() {
    ReturnStatement returnStatement = new ReturnStatement(this.curToken);
    this.nextToken();
    while (!this.curTokenIs(TokenType.SEMICOLON)) {
      this.nextToken();
    }
    return returnStatement;
  }

  private boolean curTokenIs(TokenType tokenType) {
    return this.curToken.tokenType == tokenType;
  }

  private boolean peekTokenIs(TokenType tokenType) {
    return this.peekToken.tokenType == tokenType;
  }

  private boolean expectPeek(TokenType tokenType) {
    if (peekTokenIs(tokenType)) {
      this.nextToken();
      return true;
    } else {
      this.peekError(tokenType);
      return false;
    }
  }

  private void peekError(TokenType tokenType) {
    String errorMsg =
        String.format("Expect \"%s\", but found \"%s\"", tokenType, this.peekToken.tokenType);
    errors.add(errorMsg);
    System.out.println(errorMsg);
  }

  private List<String> getErrors() {
    return this.errors;
  }
}
