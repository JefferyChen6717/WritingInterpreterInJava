package parser;

import org.testng.annotations.Test;
import tokenizer.Tokenizer;

public class ParserTests {

  @Test
  void testPArseProgram() {
    String input = "let a  5;";
    Parser parser = new Parser(new Tokenizer(input));
    ProgramNode programNode = parser.parseProgram();
    System.out.println();
  }
}
