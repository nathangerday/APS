package aps.ast;
public interface IASTCmds extends Ast{
    public OutStream eval(Environment env, OutStream o);
}