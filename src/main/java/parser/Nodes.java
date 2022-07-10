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
      return this.token.toString();
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
    public void statementNode() {}
  }
}
