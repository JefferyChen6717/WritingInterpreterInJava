package parser;

public enum OperatorPrecedence {
  LOWEST(1),
  EQUALS(2),
  LESS_GREATER(3),
  SUM(4),
  PRODUCT(5),
  PREFIX(6), // -x or !x
  CALL(7);

  public final int value;

  OperatorPrecedence(int value) {
    this.value = value;
  }
}
