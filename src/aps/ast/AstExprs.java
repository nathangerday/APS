package aps.ast;
import java.util.ArrayList;

public class AstExprs implements Ast {
    ArrayList<IASTExpr> exprs;

    public AstExprs(IASTExpr expr) {
        this.exprs = new ArrayList<>();
        exprs.add(expr);
    }

    public AstExprs(IASTExpr expr, AstExprs exprs){
        this.exprs = new ArrayList<>();
        this.exprs.add(expr);
        this.exprs.addAll(((AstExprs)exprs).exprs);
    }

    public String toPrologString() {
        String res = "";
        for(IASTExpr e : exprs){
            res+="exprs(";
            res+=e.toPrologString();
            res+=",";
        }
        res+="exprs()";
        for(int i=0; i<exprs.size();i++){
            res+=")";
        }
        return res;

    }
  
    public ArrayList<MemVal> eval(Environment env, Memory mem){
        ArrayList<MemVal> res = new ArrayList<>();
        Memory curMem = mem;
        for(IASTExpr e : exprs){
            MemVal evaluated = e.eval(env, curMem);
            curMem = evaluated.getMem();
            res.add(evaluated);
        }
        return res;
    }
}