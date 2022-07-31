package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import parser.Nodes.ExpressionStatement;
import parser.Nodes.Identifier;
import parser.Nodes.IntegerLiteral;
import parser.Nodes.LetStatement;
import parser.Nodes.PrefixExpression;
import parser.Nodes.ReturnStatement;
import tokenizer.Token;
import tokenizer.TokenDataStructure.TokenType;
import tokenizer.Tokenizer;

public class Parser {
  private Tokenizer tokenizer;
  private Token curToken;
  private Token peekToken;
  private List<String> errors;
  private Map<TokenType, Supplier<Expression>> prefixParseFns;
  private Map<TokenType, Function<Expression, Expression>> infixParseFns;

  public Parser(Tokenizer tokenizer) {
    this.tokenizer = tokenizer;
    this.curToken = tokenizer.nextToken();
    this.peekToken = tokenizer.nextToken();
    this.errors = new ArrayList<>();
    this.initializeParseFns();
  }

  private void initializeParseFns() {
    this.prefixParseFns = new HashMap<>();
    prefixParseFns.put(TokenType.IDENT, this::parseIdentifier);
    prefixParseFns.put(TokenType.INT, this::parseIntegerLiteral);
    prefixParseFns.put(TokenType.BANG, this::parsePrefixExpression);
  }

  public void registerPrefixFn(TokenType tokenType, Supplier<Expression> supplier) {
    this.prefixParseFns.put(tokenType, supplier);
  }

  public void registerInfixFn(TokenType tokenType, Function<Expression, Expression> function) {
    this.infixParseFns.put(tokenType, function);
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
        return this.parseLetStatement();
      case RETURN:
        return this.parseReturnStatement();
      default:
        return this.parseExpressionStatement();
    }
  }

  private LetStatement parseLetStatement() {
    LetStatement letStatement = new LetStatement().token(this.curToken);
    if (!this.expectPeek(TokenType.IDENT)) {
      return null;
    }
    letStatement.name = new Identifier(this.curToken, this.curToken.literal);
    if (!this.expectPeek(TokenType.ASSIGN)) {
      return null;
    }
    while (!curTokenIs(TokenType.SEMICOLON)) {
      this.nextToken();
    }
    return letStatement;
  }

  private ReturnStatement parseReturnStatement() {
    ReturnStatement returnStatement = new ReturnStatement().token(this.curToken);
    this.nextToken();
    while (!this.curTokenIs(TokenType.SEMICOLON)) {
      this.nextToken();
    }
    return returnStatement;
  }

  private ExpressionStatement parseExpressionStatement() {
    ExpressionStatement expressionStatement = new ExpressionStatement().token(this.curToken);

    expressionStatement.expression = this.parseExpression(OperatorPrecedence.LOWEST);

    if (this.peekTokenIs(TokenType.SEMICOLON)) {
      this.nextToken();
    }
    return expressionStatement;
  }

  public Expression parseExpression(OperatorPrecedence precedence) {
    Supplier<Expression> prefixFn = this.prefixParseFns.get(this.curToken.tokenType);
    if (prefixFn == null) {
      this.noPrefixParseFnError(this.curToken.tokenType);
      return null;
    }
    return prefixFn.get();
  }

  private Expression parseIdentifier() {
    return new Nodes.Identifier(this.curToken, this.curToken.literal);
  }

  private Expression parseIntegerLiteral() {
    IntegerLiteral integerLiteral = new IntegerLiteral().token(this.curToken);
    Integer value = null;
    try {
      value = Integer.parseInt(this.curToken.literal);
    } catch (NumberFormatException e) {
      String errorMsg =
          String.format("cannot parse %s to Integer due to wrong format", this.curToken.literal);
      this.errors.add(errorMsg);
      System.out.println(errorMsg);
    }
    return integerLiteral.value(value);
  }

  private Expression parsePrefixExpression() {
    PrefixExpression expression =
        new PrefixExpression().token(this.curToken).operator(this.curToken.literal);
    this.nextToken();
    return expression.right(this.parseExpression(OperatorPrecedence.PREFIX));
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

  private void noPrefixParseFnError(TokenType tokenType) {
    this.errors.add(String.format("no prefix parse function for %s found", tokenType));
  }

  private List<String> getErrors() {
    return this.errors;
  }
}
