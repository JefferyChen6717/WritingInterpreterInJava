package parser;

import tokenizer.Token;

/** nodes in ast */
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

    public LetStatement token(Token token) {
      this.token = token;
      return this;
    }

    public LetStatement name(Identifier name) {
      this.name = name;
      return this;
    }

    public LetStatement value(Expression value) {
      this.value = value;
      return this;
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

    public ReturnStatement token(Token token) {
      this.token = token;
      return this;
    }

    public ReturnStatement expression(Expression returnValue) {
      this.returnValue = returnValue;
      return this;
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

    public ExpressionStatement token(Token token) {
      this.token = token;
      return this;
    }

    public ExpressionStatement expression(Expression expression) {
      this.expression = expression;
      return this;
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

  public static class IntegerLiteral implements Expression {
    private Token token;
    private int value;

    public IntegerLiteral token(Token token) {
      this.token = token;
      return this;
    }

    public IntegerLiteral value(int value) {
      this.value = value;
      return this;
    }

    public Token getToken() {
      return this.token;
    }

    public int getValue() {
      return this.value;
    }

    @Override
    public void expressionNode() {}

    @Override
    public String tokenLiteral() {
      return this.token.literal;
    }
  }

  public static class PrefixExpression implements Expression {
    private Token token;
    private String operator;
    private Expression right;

    public PrefixExpression token(Token token) {
      this.token = token;
      return this;
    }

    public PrefixExpression operator(String operator) {
      this.operator = operator;
      return this;
    }

    public PrefixExpression right(Expression right) {
      this.right = right;
      return this;
    }

    public Token getToken() {
      return this.token;
    }

    /** contains prefix operator, "!" or "-" */
    public String getOperator() {
      return this.operator;
    }

    public Expression getRight() {
      return this.right;
    }

    @Override
    public void expressionNode() {}

    @Override
    public String tokenLiteral() {
      return this.token.literal;
    }

    @Override
    public String toString() {
      return String.format("(%s%s)", this.operator, this.right.toString());
    }
  }
}
