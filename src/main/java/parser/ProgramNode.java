package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/** Program node is the root of every AST */
public class ProgramNode implements Node {
  public List<Statement> statements;

  public ProgramNode() {
    this(10);
  }

  public ProgramNode(int size) {
    this.statements = new ArrayList<>(size);
  }

  @Override
  public String tokenLiteral() {
    return this.statements.isEmpty() ? "" : statements.get(0).tokenLiteral();
  }

  @Override
  public String toString() {
    StringJoiner joiner = new StringJoiner("");
    for (Statement statement : statements) {
      joiner.add(statement.toString());
    }
    return joiner.toString();
  }
}
