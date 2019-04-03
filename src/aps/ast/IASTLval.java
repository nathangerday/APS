package aps.ast;
public interface IASTLval extends Ast{
    public Value evalleftval(Environment env, Memory mem);
}