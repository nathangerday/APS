package aps.ast;
public interface IASTLval extends Ast{
    public MemVal evalleftval(Environment env, Memory mem);
}