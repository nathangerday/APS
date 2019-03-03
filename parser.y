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
    |   FUN IDENT type LBRA args RBRA expr { $$ = new AstFun(new AstIdent($2), (Ast)$3, (Ast)$5, (IASTExpr)$7);}
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
    arg {$$ = new AstArgs((Ast)$1);}
    | arg COMMA args { $$ = new AstArgs((Ast)$1, (Ast)$3);}
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
    |   LPAR NOT expr RPAR { $$ = new AstPrim(Op.NOT, (IASTExpr)$3, null);}
    |   LPAR AND expr expr RPAR { $$ = new AstPrim(Op.AND ,(IASTExpr)$3, (IASTExpr)$4);}
    |   LPAR OR expr expr RPAR { $$ = new AstPrim(Op.OR ,(IASTExpr)$3, (IASTExpr)$4);}
    |   LPAR EQ expr expr RPAR { $$ = new AstPrim(Op.EQ ,(IASTExpr)$3, (IASTExpr)$4);}
    |   LPAR LT expr expr RPAR { $$ = new AstPrim(Op.LT ,(IASTExpr)$3, (IASTExpr)$4);}
    |   LPAR ADD expr expr RPAR { $$ = new AstPrim(Op.ADD ,(IASTExpr)$3, (IASTExpr)$4);}
    |   LPAR SUB expr expr RPAR { $$ = new AstPrim(Op.SUB ,(IASTExpr)$3, (IASTExpr)$4);}
    |   LPAR MUL expr expr RPAR { $$ = new AstPrim(Op.MUL ,(IASTExpr)$3, (IASTExpr)$4);}
    |   LPAR DIV expr expr RPAR { $$ = new AstPrim(Op.DIV ,(IASTExpr)$3, (IASTExpr)$4);}
    |   LBRA args RBRA expr { $$ = new AstBlock((Ast)$2, (IASTExpr)$4);}
    |   LPAR expr exprs RPAR { $$ = new AstInvoc((IASTExpr)$2, (Ast)$3);}
    |   LPAR IF expr expr expr RPAR { $$ = new AstIf((IASTExpr)$3, (IASTExpr)$4, (IASTExpr)$5);}
;

exprs:
    expr { $$ = new AstExprs((IASTExpr)$1); }
    | expr exprs { $$ = new AstExprs((IASTExpr)$1, (Ast)$2);}
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