
public class Axioms {

    public static boolean equivalence (Node expr1, Node expr2) {
        return (!(((expr1.l != null && expr2.l == null) || (expr1.l == null && expr2.l != null))
                || ((expr1.r != null && expr2.r == null) || (expr1.r == null && expr2.r != null))
                || (expr1.l != null /*&& expr2.l != null*/ && !equivalence(expr1.l, expr2.l))
                || (expr1.r != null && expr2.r != null && !equivalence(expr1.r, expr2.r)))
                && expr1.s.equals(expr2.s));
    }

    private static boolean cond(Node expr) {
        return (expr != null
                && expr.s.equals("->"));
    }

    private static boolean condR(Node expr,String c) {
        return (expr.r != null
                && expr.r.s.equals(c));
    }

    private static boolean condL(Node expr,String c) {
        return (expr.l != null
                && expr.l.s.equals(c));
    }

    private static boolean condLR(Node expr) {
        return (expr.l.r != null
                && expr.l.l != null);
    }

    private static boolean condRL(Node expr) {
        return (expr.r.l != null
                && expr.r.r != null);
    }


    public static boolean axiomOne(Node expr) {
        return (cond(expr)
                && condR(expr,"->")
                && (expr.r.r != null && expr.l != null &&  equivalence(expr.l, expr.r.r)));
    }

    public static boolean axiomTwo(Node expr) {
        if (cond(expr)
                && condL(expr,"->")
                && condR(expr,"->")
                && condLR(expr)
                && condRL(expr)
                && expr.r.l.s.equals("->")
                && expr.r.r.s.equals("->")
                && expr.r.l.r.s.equals("->")
                && expr.r.r.l != null
                && expr.r.r.r != null
                && expr.r.l.l != null
                && expr.r.l.r.l != null
                && expr.r.l.r.r != null) {
            return ((equivalence(expr.l.l, expr.r.l.l)
                    && equivalence(expr.r.r.l, expr.l.l))
                    && equivalence(expr.l.r, expr.r.l.r.l)
                    && equivalence(expr.r.r.r, expr.r.l.r.r));

        }
        return false;
    }

    public static boolean axiomThree(Node expr) {
        if (cond(expr)
                && condRL(expr)
                && condR(expr,"->")
                && expr.r.r.s.equals("&")
                && expr.l != null
                && expr.r.r.l != null
                && expr.r.r.r != null) {
            return (equivalence(expr.l, expr.r.r.l)
                    && equivalence(expr.r.l, expr.r.r.r));
        }
        return false;
    }

    public static boolean axiomFour(Node expr) {
        if (cond(expr)
                && condL(expr,"&")
                && condLR(expr)
                && expr.r != null) {
            return (equivalence(expr.l.l, expr.r));
        }
        return false;
    }

    public static boolean axiomFive(Node expr) {
        if (cond(expr)
                &&condL(expr,"&")
                && condLR(expr)
                && expr.r != null) {
            return (equivalence(expr.l.r, expr.r));
        }
        return false;
    }

    public static boolean axiomSix(Node expr) {
        if (cond(expr)
                && condR(expr,"|")
                && condRL(expr)
                && expr.l != null) {
            return (equivalence(expr.l, expr.r.l));
        }
        return false;
    }

    public static boolean axiomSeven(Node expr) {
        if (cond(expr)
                && condR(expr,"|")
                && condRL(expr)
                && expr.l != null) {
            return (equivalence(expr.l, expr.r.r));
        }
        return false;
    }

    public static boolean axiomEight(Node expr) {
        if (cond(expr)
                && condL(expr,"->")
                && condR(expr,"->")
                && condLR(expr)
                && condRL(expr)
                && expr.r.r.l != null
                && expr.r.l.s.equals("->")
                && expr.r.r.s.equals("->")
                && expr.r.r.l.s.equals("|")
                && expr.r.l.l != null
                && expr.r.l.r != null
                && expr.r.r.r != null
                && expr.r.r.l.l != null
                && expr.r.r.l.r != null) {
            return (equivalence(expr.l.l, expr.r.r.l.l)
                    && equivalence(expr.r.l.l, expr.r.r.l.r)
                    && equivalence(expr.l.r, expr.r.l.r));
        }
        return false;
    }

    public static boolean axiomNine(Node expr) {
        if (cond(expr)
                && expr.l != null
                && condR(expr,"->")
                && condLR(expr)
                && condRL(expr)
                && expr.r.l.s.equals("->")
                && expr.r.l.r != null
                && expr.r.l.r.s.equals("!")
                && expr.r.r.s.equals("!")
                && expr.r.l.l != null
                && expr.r.r.r != null
                && expr.r.l.r.r != null) {
                    return (equivalence(expr.l.l, expr.r.l.l)
                            && equivalence(expr.l.l, expr.r.r.r)
                            && equivalence(expr.l.r, expr.r.l.r.r));
        }
        return false;
    }

    public static boolean axiomTen(Node expr) {
        if (cond(expr)
                && expr.l.r != null
                && condL(expr,"!")
                && expr.l.r.s.equals("!")
                && expr.r != null
                && expr.l.r.r != null) {
                return (equivalence(expr.l.r.r, expr.r));
        }
        return false;
    }

}
