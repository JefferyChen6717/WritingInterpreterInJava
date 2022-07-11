package parser;

/** node in n AST */
public interface Node {
  /** return the literal value associated with this node, only for debugging */
  String tokenLiteral();

  String toString();
}
