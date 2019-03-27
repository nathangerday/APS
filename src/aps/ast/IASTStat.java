package aps.ast;
public interface IASTStat extends Ast{
    public Memory eval(Environment env, Memory mem);
}