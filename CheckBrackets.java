import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class CheckBrackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        int error_pos = 0;

        Stack<Bracket> bracketStack = new Stack<>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                Bracket b = new Bracket(next, position + 1);
                bracketStack.push(b);
            }

            if (next == ')' || next == ']' || next == '}') {
                if (bracketStack.empty()) {
                    error_pos = position + 1;
                    break;
                }
                Bracket top = bracketStack.pop();
                if (!top.Match(next)) {
                    error_pos = position + 1;
                    break;
                }
            }
        }

        if (error_pos == 0 && bracketStack.empty())
            System.out.println("Success");
        else {
            if (error_pos == 0) {
                while (bracketStack.size() > 1)
                    bracketStack.pop();
                error_pos = bracketStack.peek().position;
            }
            System.out.println(error_pos);
        }
    }
}
