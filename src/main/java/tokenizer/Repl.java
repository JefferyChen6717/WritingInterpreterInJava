package tokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Repl {
  private static final String prompt = ">> ";

  public String start() {
    StringBuilder code = new StringBuilder();
    String buffer;
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      System.out.print(prompt);
      try {
        buffer = bufferedReader.readLine();
        if ("quit()".equals(buffer) || "exit()".equals(buffer)) {
          break;
        }
        code.append(buffer).append("\n");
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
    return code.toString();
  }
}
