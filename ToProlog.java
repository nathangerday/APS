import java.io.*;

class ToProlog {
    public static void main(String args[]) throws IOException {
        Parser yyparser;
        Ast prog;
        yyparser = new Parser(new InputStreamReader(System.in));
        yyparser.yyparse();
        prog = (Ast) yyparser.yyval.obj;
        if (prog != null)
            System.out.println("Resultat: " + prog.toPrologString());
        else
            System.out.println("Null");
    }
}