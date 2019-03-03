public interface IASTExpr extends Ast{
    public Value eval(Environment env);
}