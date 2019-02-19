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
    LBRA cmds RBRA { prog=(Ast)$2; $$=(Ast)$2; }
;

cmds:
    stat { $$ = $1; }
    | dec SEMICOLON cmds { $$ = new AstCmds((Ast)$1, (Ast)$3);}
    | stat SEMICOLON cmds { $$ = new AstCmds((Ast)$1, (Ast)$3);}
;
dec:
    CONST IDENT type expr { $$ = new AstConst($2, (Ast)$3, (Ast)$4);}
    |   FUN IDENT type LBRA args RBRA expr { $$ = new AstFun($2, (Ast)$3, (Ast)$5, (Ast)$7);}
    |   FUN REC IDENT type LBRA args RBRA expr { $$ = new AstFunRec($3, (Ast)$4, (Ast)$6, (Ast)$8);}
;

stat: 
    ECHO expr { $$ = new AstEcho((Ast)$2); }
;


arg:
    IDENT COLON type { $$ = new AstArg($1, (Ast)$3);}
;

args:
    arg { $$ = $1 ;}
    | arg COMMA args {$$ = new AstArgs((Ast)$1, (Ast)$3); }
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
    |   LPAR NOT exprs RPAR { $$ = new AstPrim(Op.NOT, (Ast)$3);}
    |   LPAR AND exprs RPAR { $$ = new AstPrim(Op.AND ,(Ast)$3);}
    |   LPAR OR exprs RPAR { $$ = new AstPrim(Op.OR ,(Ast)$3);}
    |   LPAR EQ exprs RPAR { $$ = new AstPrim(Op.EQ ,(Ast)$3);}
    |   LPAR LT exprs RPAR { $$ = new AstPrim(Op.LT ,(Ast)$3);}
    |   LPAR ADD exprs RPAR { $$ = new AstPrim(Op.ADD ,(Ast)$3);}
    |   LPAR SUB exprs RPAR { $$ = new AstPrim(Op.SUB ,(Ast)$3);}
    |   LPAR MUL exprs RPAR { $$ = new AstPrim(Op.MUL ,(Ast)$3);}
    |   LPAR DIV exprs RPAR { $$ = new AstPrim(Op.DIV ,(Ast)$3);}
    |   LBRA args RBRA expr { $$ = new AstBlock((Ast)$2, (Ast)$4);}
    |   LPAR expr exprs RPAR { $$ = new AstInvoc((Ast)$2, (Ast)$3);}
    |   LPAR IF expr expr expr RPAR { $$ = new AstIf((Ast)$3, (Ast)$4, (Ast)$5);}
;

exprs:
    expr { $$ = new AstExpr((Ast)$1); }
    | expr exprs { $$ = new AstExprs((Ast)$1, (Ast)$2);}
;
%%

public Ast prog;
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