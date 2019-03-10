package aps.ast;
public interface IASTDec extends Ast{
    public Environment eval(Environment env);
}