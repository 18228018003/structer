package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效的括号
 */
public class _20_isValid {
    private Map<Character,Character> map = new HashMap<>();

    public boolean isValid(String s){
        map.put('{','}');
        map.put('(',')');
        map.put('[',']');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)){
                stack.push(c);
            }else {
                if (stack.isEmpty()) return false;
                char left = stack.pop();
                if (map.get(left) != c) return false;

            }
        }
        return stack.isEmpty();
    }
    public boolean method_1(String s){
        while (s.contains("{}")||s.contains("()")|| s.contains("[]")){
            s = s.replace("()","");
            s = s.replace("[]","");
            s = s.replace("{}","");
        }
        return s.isEmpty();
    }
    public boolean isValid2(String s){
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else {
                if (stack.isEmpty()) return false;
                Character left = stack.pop();
                if ((left == '(' && c != ')') || (left == '[' && c != ']') || (left == '{' && c != '}'))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        _20_isValid isValid = new _20_isValid();
        boolean valid = isValid.isValid("{[[]{}()]}");
        System.out.println(valid );
    }
}
