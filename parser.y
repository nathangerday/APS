%{
import java.io.*;
%}

%token NL /* newline */
%token <ival> NUM /* a number */
%token <sval> IDENT /* an identifier */
%token ADD SUB MUL DIV /* operators */
%token LPAR RPAR /* parethesis */
%token LBRA RBRA /* brackets */
%token COLON SEMICOLON STAR ARROW COMMA /* Symbols */
%token CONST FUN REC ECHO INT BOOL TRUE FALSE NOT AND OR EQ LT IF /* Keywords */

%left MINUS PLUS
%left TIMES DIV
%left NEG /* negation--unary minus */

%type <obj> exprs
%type <obj> expr
%type <obj> type
%type <obj> types
%type <obj> arg
%type <obj> args
%type <obj> stat
%type <obj> dec
%type <obj> cmds
%type <obj> prog

%start prog

%%

prog:
    LBRA cmds RBRA { prog=(AstCmds)$2; $$=(AstCmds)$2; }
;

cmds:
    stat { $$ = new AstCmds((Ast)$1); }
    | dec SEMICOLON cmds { $$ = new AstCmds((Ast)$1, (AstCmds)$3);}
    | stat SEMICOLON cmds { $$ = new AstCmds((Ast)$1, (AstCmds)$3);}
;
dec:
    CONST IDENT type expr { $$ = new AstConst(new AstIdent($2), (Ast)$3, (IASTExpr)$4);}
    |   FUN IDENT type LBRA args RBRA expr { $$ = new AstFun(new AstIdent($2), (Ast)$3, (AstArgs)$5, (IASTExpr)$7);}
    |   FUN REC IDENT type LBRA args RBRA expr { $$ = new AstFunRec(new AstIdent($3), (Ast)$4, (Ast)$6, (IASTExpr)$8);}
;

stat: 
    ECHO expr { $$ = new AstEcho((IASTExpr)$2); }
;


arg:
    IDENT COLON type { $$ = new AstArg(new AstIdent($1), (Ast)$3);}
;

args:
    // arg {$$ = new AstArgs((Ast)$1);}
    arg {$$ = new AstArgs((AstArg)$1);}
    | arg COMMA args { $$ = new AstArgs((AstArg)$1, (AstArgs)$3);}
;

type:
    INT { $$ = new AstType(Type.INT); }
    | BOOL { $$ = new AstType(Type.BOOL); }
    | LPAR types ARROW type RPAR { $$ = new AstArrow((Ast)$2, (Ast)$4); }
;

types:
    type { $$ = $1 ;}
    | type STAR types { $$ = new AstStar((Ast)$1, (Ast)$3); }
;

expr: 
        TRUE { $$ = new AstTrue(); }
    |   FALSE { $$ = new AstFalse();}
    |   NUM { $$ = new AstNum($1);}
    |   IDENT {$$ = new AstIdent($1);}
    |   LPAR NOT exprs RPAR { $$ = new AstPrim(Op.NOT, (AstExprs)$3);}
    |   LPAR AND exprs RPAR { $$ = new AstPrim(Op.AND , (AstExprs)$3);}
    |   LPAR OR exprs RPAR { $$ = new AstPrim(Op.OR , (AstExprs)$3);}
    |   LPAR EQ exprs RPAR { $$ = new AstPrim(Op.EQ , (AstExprs)$3);}
    |   LPAR LT exprs RPAR { $$ = new AstPrim(Op.LT , (AstExprs)$3);}
    |   LPAR ADD exprs RPAR { $$ = new AstPrim(Op.ADD , (AstExprs)$3);}
    |   LPAR SUB exprs RPAR { $$ = new AstPrim(Op.SUB , (AstExprs)$3);}
    |   LPAR MUL exprs RPAR { $$ = new AstPrim(Op.MUL , (AstExprs)$3);}
    |   LPAR DIV exprs RPAR { $$ = new AstPrim(Op.DIV , (AstExprs)$3);}
    |   LBRA args RBRA expr { $$ = new AstAbs((AstArgs)$2, (IASTExpr)$4);}
    |   LPAR expr exprs RPAR { $$ = new AstApp((IASTExpr)$2, (AstExprs)$3);}
    |   LPAR IF expr expr expr RPAR { $$ = new AstIf((IASTExpr)$3, (IASTExpr)$4, (IASTExpr)$5);}
;

exprs:
    expr { $$ = new AstExprs((IASTExpr)$1); }
    | expr exprs { $$ = new AstExprs((IASTExpr)$1, (AstExprs)$2);}
;
%%

public AstCmds prog;
private Yylex lexer;
private int yylex () {
    int yyl_return = -1;
    try {
        yylval = new ParserVal(0);
        yyl_return = lexer.yylex();
    }
    catch (IOException e) {
        System.err.println("IO error :"+e);
    }
    return yyl_return;
}

public void yyerror (String error) {
    System.err.println ("Error: " + error);
}

public Parser(Reader r) {
    lexer = new Yylex(r, this);
}