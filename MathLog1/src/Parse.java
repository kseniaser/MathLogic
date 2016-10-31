
public class Parse extends Main {

    static Node conditional() throws Exception {
        Node expr = disjunction();
        String ss = "";
        if (current + 2 < s.length() + 1)
            ss = s.substring(current, current + 2);
        if (current < s.length() && ss.equals("->")) {
            current += 2;
            return new Node("->", expr, conditional());
        }
        return expr;
    }

    static Node negation() throws Exception {
        char c = s.charAt(current);
        current++;
        if (c == '!') {
            return new Node("!", null, negation());
        } else if (c >= 'A' && c <= 'Z') {
            String str = "" + c;
            if (current < s.length() && s.charAt(current)>=0 && s.charAt(current)<=9) {
                str += s.charAt(current++);
            }
            return new Node(str, null, null);
        } else if (c == '(') {
            Node exprToParse = conditional();
            if (current >= s.length() || s.charAt(current++) != ')') {
                throw new Exception(" no ')' ");
            }
            return exprToParse;
        }
        throw new Exception("incorrect");
    }

    static Node conjustion() throws Exception {
        Node exprToParse = negation();
        while (current < s.length() && s.charAt(current) == '&') {
            current++;
            exprToParse = new Node("&", exprToParse, negation());
        }
        return exprToParse;
    }

    static Node disjunction() throws Exception {
        Node exprToParse = conjustion();
        while (current < s.length() && s.charAt(current) == '|') {
            current++;
            exprToParse = new Node("|", exprToParse, conjustion());
        }
        return exprToParse;
    }
}
