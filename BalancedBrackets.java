import java.util.*;

public class BalancedBrackets {
  public static boolean isBalanced(String str)
  {
    Deque<Character> stack = new ArrayDeque<Character>();

    for (int i = 0; i < str.length(); ++i)
    {
      char c = str.charAt(i);
      if (!isBracket(c)) continue;

      if (c == '(' || c == '[' || c == '{')
      {
         // Push the element in the stack
         stack.push(c);
         continue;
      }

      // If current character is not opening
      // bracket, then it must be closing. So stack
      // cannot be empty at this point.
      if (stack.isEmpty())
         return false;
      char check = stack.pop();

      switch (c) {
         case ')':
         	if (check != '(') return false;
            break;

         case '}':
            if (check != '{') return false;
            break;

         case ']':
            if (check != '[') return false;
            break;
      }
    }
    // Check Empty Stack
    return (stack.isEmpty());
  }

  private static boolean isBracket(char c)
  {
    switch(c)
    {
      case '(' :
      case '{' :
      case '[' :
      case ')' :
      case '}' :
      case ']' :
        return true;
      default :
        return false;
    }
  }

  public static void main(String[] args) {
    String s = "This is bracket ( and ) test";
    String s1 = "a pair { and }, but only right )";
    String s2 = "abc{ vm [ jk ]}";
    String s3 = "abc";
    String s4 = "abc{ vm [ jk )]}";
    String s5 = "abc{ vm ([ jk ]}";
    System.out.println("s is balanced? " + isBalanced(s));
    System.out.println("s1 is balanced? " + isBalanced(s1));
    System.out.println("s2 is balanced? " + isBalanced(s2));
    System.out.println("s3 is balanced? " + isBalanced(s3));
    System.out.println("s4 is balanced? " + isBalanced(s4));
    System.out.println("s5 is balanced? " + isBalanced(s5));
  }
}
