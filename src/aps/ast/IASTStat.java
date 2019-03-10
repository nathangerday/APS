package aps.ast;
public interface IASTStat extends Ast{
    public OutStream eval(Environment env, OutStream o);
}