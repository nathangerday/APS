package aps.ast;
public interface IASTLval extends Ast{
    public Value eval(Environment env, Memory mem);
}