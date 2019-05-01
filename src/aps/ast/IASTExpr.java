package aps.ast;
public interface IASTExpr extends Ast{
    public MemVal eval(Environment env, Memory mem);
}