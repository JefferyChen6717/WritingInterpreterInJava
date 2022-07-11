package parser;

import tokenizer.Token;

public class Nodes {
  public static class Identifier implements Expression {
    /** the 'IDENT' token */
    Token token;

    /** identifier name */
    String value;

    public Identifier(Token token) {
      this.token = token;
    }

    public Identifier(Token token, String value) {
      this.token = token;
      this.value = value;
    }

    @Override
    public String tokenLiteral() {
      return this.value;
    }

    @Override
    public String toString() {
      return null;
    }

    @Override
    public void expressionNode() {}
  }

  public static class LetStatement implements Statement {
    /** the 'let' token */
    public Token token;

    public Identifier name;
    public Expression value;

    public LetStatement() {}

    public LetStatement(Token token) {
      this.token = token;
    }

    @Override
    public String tokenLiteral() {
      return token.toString();
    }

    @Override
    public String toString() {
      return String.format(
          "%s %s = %s;",
          this.token.literal, this.name.value, this.value == null ? "" : this.value.toString());
    }

    @Override
    public void statementNode() {}
  }

  public static class ReturnStatement implements Statement {
    public Token token;
    public Expression returnValue;

    public ReturnStatement(Token token) {
      this.token = token;
    }

    public ReturnStatement(Token token, Expression returnValue) {
      this.token = token;
      this.returnValue = returnValue;
    }

    @Override
    public String tokenLiteral() {
      return token.literal;
    }

    @Override
    public String toString() {
      return String.format("%s %s;", this.token.literal, this.returnValue.toString());
    }

    @Override
    public void statementNode() {}
  }

  /** wrap expression into statement */
  public static class ExpressionStatement implements Statement {
    /** first token of the expression */
    public Token token;
    /** hold whole expression */
    public Expression expression;

    public ExpressionStatement(Token token) {
      this.token = token;
    }

    public ExpressionStatement(Token token, Expression expression) {
      this.token = token;
      this.expression = expression;
    }

    @Override
    public String tokenLiteral() {
      return this.token.literal;
    }

    @Override
    public String toString() {
      return this.expression.toString();
    }

    @Override
    public void statementNode() {}
  }
}
