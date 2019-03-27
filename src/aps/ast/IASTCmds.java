package aps.ast;
public interface IASTCmds extends Ast{
    public Memory eval(Environment env, Memory mem);
}