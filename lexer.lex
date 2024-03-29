package aps.parser;

%%

%byaccj

%{

    private Parser yyparser;
        public Yylex(java.io.Reader r, Parser yyparser) {
            this(r);
            this.yyparser = yyparser;
    }
%}


nums = -?[0-9]+
ident = [a-z][a-zA-Z0-9]*
sep = [\n | \t | ' ']

%%

/* operators */
"add" { return Parser.ADD; }
"sub" { return Parser.SUB; }
"mul" { return Parser.MUL; }
"div" { return Parser.DIV; }

"len" { return Parser.LEN; } 
"nth" { return Parser.NTH; }
"alloc" { return Parser.ALLOC; }


/* parenthesis */
"(" { return Parser.LPAR; }
")" { return Parser.RPAR; }

/* brackets */
"[" { return Parser.LBRA; }
"]" { return Parser.RBRA; }

/* Symbols */
":" {return Parser.COLON;}
";" {return Parser.SEMICOLON;}
"," {return Parser.COMMA;}
"*" {return Parser.STAR;}
"->" {return Parser.ARROW;}

/* Keywords */
"CONST" {return Parser.CONST;}
"FUN" {return Parser.FUN;}
"REC" {return Parser.REC;}
"ECHO" {return Parser.ECHO;}

"int" {return Parser.INT;}
"bool" {return Parser.BOOL;}
"vec" {return Parser.VEC;}
"true" {return Parser.TRUE;}
"false" {return Parser.FALSE;}
"not" {return Parser.NOT;}
"and" {return Parser.AND;}
"or" {return Parser.OR;}
"eq" {return Parser.EQ;}
"lt" {return Parser.LT;}
"if" {return Parser.IF;}

"void" {return Parser.VOID;} /* //TODO Le sujet ne demande pas de voir ici */
"VAR"  {return Parser.VAR;}
"PROC" {return Parser.PROC;}
"SET" {return Parser.SET;}
"IF" {return Parser.IF;}
"WHILE" {return Parser.WHILE;}
"CALL" {return Parser.CALL;}


/* newline */
{sep} { }


{nums} { yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));
         return Parser.NUM; }

{ident} { yyparser.yylval = new ParserVal(yytext());
          return Parser.IDENT;
}

\b { System.err.println("Sorry, backspace doesn’t work"); }

/* error fallback */
[^] { System.err.println("Error: unexpected character ’"+yytext()+"’");             return -1; }