%{
import java.io.*;
%}

%token NL /* newline */
%token <ival> NUM /* a number */
%token <sval> IDENT /* an identifier */
%token PLUS MINUS TIMES DIV /* operators */
%token LPAR RPAR /* parethesis */
%token LBRA RBRA /* brackets */
%token COLON SEMICOLON STAR ARROW COMMA /* Symbols */
%token CONST FUN REC ECHO INT BOOL TRUE FALSE NOT AND OR EQ LT /* Keywords */

%left MINUS PLUS
%left TIMES DIV
%left NEG /* negation--unary minus */

%type <obj> line
%type <obj> exp


%start line

%%
line: exp { prog=(Ast)$1; $$=$1; }

exp:
NUM { $$ = new AstNum($1); }
| IDENT { $$ = new AstId($1); }
| LPAR PLUS exp exp RPAR { $$ = new AstPrim(Op.ADD,(Ast)$3,(Ast)$4); }
| LPAR MINUS exp exp RPAR { $$ = new AstPrim(Op.SUB,(Ast)$3,(Ast)$4); }
| LPAR TIMES exp exp RPAR { $$ = new AstPrim(Op.MUL,(Ast)$3,(Ast)$4); }
| LPAR DIV exp exp RPAR { $$ = new AstPrim(Op.DIV,(Ast)$3,(Ast)$4); }
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