package aps.ast;
public interface IASTLval extends Ast{
    public Address evalleftval(Environment env, Memory mem);
}