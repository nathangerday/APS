import java.io.*;

public class Eval{
    public static void main(String args[]) throws IOException{
        Parser yyparser;
        Ast prog;
        if(args.length == 1){
            yyparser = new Parser(new FileReader(new File(args[0])));
        }else{
            yyparser = new Parser(new InputStreamReader(System.in));
        }
        yyparser.yyparse();
        prog = (Ast) yyparser.yyval.obj;

        if (prog == null){
            System.out.println("Ast null");
            return;
        }

        Context c = new Context();
        prog.eval(c);
        
    }
}