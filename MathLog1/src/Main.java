import java.io.*;
import java.util.StringTokenizer;
import java.lang.*;

public class Main {

    FastScanner in;
    PrintWriter out;

    static int current;
    static int numberOfStatement = 1;
    static String s;
    static Node statement[] = new Node[5000];

    void solve() throws IOException {

        while (in.hasNext()) {
            s = in.next().replace(" ", "");
            current = 0;
            if (s.length() == 0) {
                break;
            }
            out.print("[" + numberOfStatement + "]   " + s);
            try {
                Node expr = Parse.conditional();
                statement[numberOfStatement - 1] = expr;
                if (Axioms.axiomOne(expr)) {
                    out.println(" (Сх. акс. 1)");
                } else if (Axioms.axiomTwo(expr)) {
                    out.println(" (Сх. акс. 2)");
                } else if (Axioms.axiomThree(expr)) {
                    out.println(" (Сх. акс. 3)");
                } else if (Axioms.axiomFour(expr)) {
                    out.println(" (Сх. акс. 4)");
                } else if (Axioms.axiomFive(expr)) {
                    out.println(" (Сх. акс. 5)");
                } else if (Axioms.axiomSix(expr)) {
                    out.println(" (Сх. акс. 6)");
                } else if (Axioms.axiomSeven(expr)) {
                    out.println(" (Сх. акс. 7)");
                } else if (Axioms.axiomEight(expr)) {
                    out.println(" (Сх. акс. 8)");
                } else if (Axioms.axiomNine(expr)) {
                    out.println(" (Сх. акс. 9)");
                } else if (Axioms.axiomTen(expr)) {
                    out.println(" (Сх. акс. 10)");
                } else {
                    int MP1 = -1;
                    boolean flag = false;
                    for (int i = numberOfStatement - 2; i >= 0; i--) {
                        Node AB = statement[i];
                        if (AB != null
                                && AB.s.equals("->")
                                && AB.r != null
                                && statement[numberOfStatement - 1] != null
                                && Axioms.equivalence(AB.r, statement[numberOfStatement - 1])) {
                            for (int j = 0; j < numberOfStatement - 1; j++) {
                                Node A = statement[j];
                                if (A != null && AB.l != null && Axioms.equivalence(A, AB.l)) {
                                    MP1 = j;
                                    int MP2 = i;
                                    out.println(" (M.P. " + (MP1 + 1) + ", " + (MP2 + 1) + ")");
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag) {
                                break;
                            }
                        }
                    }
                    if (MP1 == -1) {
                        out.println(" Не доказано");
                    }
                }

            } catch (Exception e) {
                out.println(e.getMessage() + " in " + s);
            }
            numberOfStatement++;
        }

    }

    public void run() {
        try {
            in = new FastScanner(new FileInputStream("input1.txt"));
            out = new PrintWriter(new File("output.txt"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is));
        }

        boolean hasNext() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    return false;
                }
            }
            return st.hasMoreTokens();
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

    }

    public static void main(String[] args) {
        new Main().run();
    }

}