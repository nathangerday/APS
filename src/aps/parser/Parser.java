//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package aps.parser;



//#line 2 "parser.y"
import java.io.*;
import aps.ast.*;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short NL=257;
public final static short NUM=258;
public final static short IDENT=259;
public final static short ADD=260;
public final static short SUB=261;
public final static short MUL=262;
public final static short DIV=263;
public final static short LEN=264;
public final static short NTH=265;
public final static short ALLOC=266;
public final static short LPAR=267;
public final static short RPAR=268;
public final static short LBRA=269;
public final static short RBRA=270;
public final static short COLON=271;
public final static short SEMICOLON=272;
public final static short STAR=273;
public final static short ARROW=274;
public final static short COMMA=275;
public final static short CONST=276;
public final static short FUN=277;
public final static short REC=278;
public final static short ECHO=279;
public final static short INT=280;
public final static short BOOL=281;
public final static short VEC=282;
public final static short TRUE=283;
public final static short FALSE=284;
public final static short VOID=285;
public final static short NOT=286;
public final static short AND=287;
public final static short OR=288;
public final static short EQ=289;
public final static short LT=290;
public final static short IF=291;
public final static short VAR=292;
public final static short PROC=293;
public final static short SET=294;
public final static short WHILE=295;
public final static short CALL=296;
public final static short MINUS=297;
public final static short PLUS=298;
public final static short TIMES=299;
public final static short NEG=300;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,   10,    9,    9,    9,    8,    8,    8,    8,    8,
    8,    7,    7,    7,    7,    7,   11,   11,    5,    6,
    6,    3,    3,    3,    3,    3,    4,    4,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    1,    1,
};
final static short yylen[] = {                            2,
    1,    3,    1,    3,    3,    4,    7,    8,    3,    6,
    7,    2,    3,    4,    3,    3,    1,    5,    3,    1,
    3,    1,    1,    1,    4,    5,    1,    3,    1,    1,
    1,    1,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    4,    6,    1,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   31,   32,
    0,    0,   29,   30,   12,    0,    0,    0,    0,   17,
    0,    0,    0,    0,    0,    0,    2,    0,   22,   23,
   24,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    9,    0,    0,    0,   13,   15,   16,    0,
    5,    4,    0,    0,    0,    6,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   14,    0,    0,    0,   49,
    0,    0,    0,    0,    0,   38,   39,   40,   41,   42,
   44,   43,   33,   34,   35,   36,   37,    0,   46,   19,
   21,   45,    0,    0,    0,   25,   28,    0,    0,    0,
    0,   10,    0,   18,   26,    7,    0,   47,   11,    8,
};
final static short yydgoto[] = {                          2,
   69,   70,   74,   75,   60,   61,   13,   14,   15,    3,
   32,
};
final static short yysindex[] = {                      -252,
 -237,    0,    0, -238, -253, -240, -240, -226, -248, -251,
 -240, -225, -250, -223, -218, -257, -257, -224,    0,    0,
 -145, -206,    0,    0,    0, -252, -257, -193, -181,    0,
 -186, -240, -252, -240, -237, -237,    0, -235,    0,    0,
    0, -240, -188, -257, -240, -240, -240, -240, -240, -240,
 -240, -240, -240, -240, -240, -240, -240, -240, -189, -192,
 -183, -252,    0, -206, -185, -251,    0,    0,    0, -240,
    0,    0, -257, -182, -180,    0, -206, -177, -173, -172,
 -171, -170, -168, -167, -166, -165, -164, -163, -162, -161,
 -240, -160, -257, -206, -240,    0, -159, -206, -240,    0,
 -158, -257, -257, -144, -206,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -240,    0,    0,
    0,    0, -252, -143, -140,    0,    0, -139, -240, -138,
 -135,    0, -252,    0,    0,    0, -240,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -136,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -134,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -195,
    0,    0,    0, -149,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   16,   -6,  -13,  -14,    0,  -57,    0,    0,  -23,  -24,
   64,
};
final static int YYTABLESIZE=146;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         25,
   26,   62,   42,   43,   33,   17,   97,   30,   68,   38,
   28,   71,   72,   63,   58,   31,    1,   19,   20,  104,
   16,   35,   39,   40,   18,   67,   21,   41,   22,   29,
   78,   38,   27,   34,   44,   76,  121,   96,    4,    5,
  124,    6,   23,   24,   39,   40,   73,  130,   36,   41,
   91,   37,   59,    7,    8,    9,   10,   11,   12,  101,
   79,   80,   81,   82,   83,   84,   85,   86,   87,   88,
   89,   90,   48,   92,   48,   64,   48,   65,   66,  120,
   77,   93,   94,   98,  118,  100,   95,  127,  122,  128,
  102,  105,  125,  103,  106,  107,  108,  109,  132,  110,
  111,  112,  113,  114,  115,  116,  117,  119,  139,  126,
  123,  131,   19,   20,   45,   46,   47,   48,   49,   50,
   51,   21,  136,   22,   27,  129,  133,  134,  135,   99,
  140,  137,  138,    3,    0,   20,    0,   23,   24,    0,
   52,   53,   54,   55,   56,   57,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          6,
    7,   26,   16,   17,   11,  259,   64,  259,   33,  267,
  259,   35,   36,   27,   21,  267,  269,  258,  259,   77,
  259,  272,  280,  281,  278,   32,  267,  285,  269,  278,
   44,  267,  259,  259,  259,   42,   94,   62,  276,  277,
   98,  279,  283,  284,  280,  281,  282,  105,  272,  285,
   57,  270,  259,  291,  292,  293,  294,  295,  296,   73,
   45,   46,   47,   48,   49,   50,   51,   52,   53,   54,
   55,   56,  268,   58,  270,  269,  272,  259,  265,   93,
  269,  271,  275,  269,   91,   70,  270,  102,   95,  103,
  273,  269,   99,  274,  268,  268,  268,  268,  123,  268,
  268,  268,  268,  268,  268,  268,  268,  268,  133,  268,
  270,  118,  258,  259,  260,  261,  262,  263,  264,  265,
  266,  267,  129,  269,  274,  270,  270,  268,  268,   66,
  137,  270,  268,  270,   -1,  270,   -1,  283,  284,   -1,
  286,  287,  288,  289,  290,  291,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=300;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"NL","NUM","IDENT","ADD","SUB","MUL","DIV","LEN","NTH","ALLOC",
"LPAR","RPAR","LBRA","RBRA","COLON","SEMICOLON","STAR","ARROW","COMMA","CONST",
"FUN","REC","ECHO","INT","BOOL","VEC","TRUE","FALSE","VOID","NOT","AND","OR",
"EQ","LT","IF","VAR","PROC","SET","WHILE","CALL","MINUS","PLUS","TIMES","NEG",
};
final static String yyrule[] = {
"$accept : prog",
"prog : block",
"block : LBRA cmds RBRA",
"cmds : stat",
"cmds : dec SEMICOLON cmds",
"cmds : stat SEMICOLON cmds",
"dec : CONST IDENT type expr",
"dec : FUN IDENT type LBRA args RBRA expr",
"dec : FUN REC IDENT type LBRA args RBRA expr",
"dec : VAR IDENT type",
"dec : PROC IDENT LBRA args RBRA block",
"dec : PROC REC IDENT LBRA args RBRA block",
"stat : ECHO expr",
"stat : SET lval expr",
"stat : IF expr block block",
"stat : WHILE expr block",
"stat : CALL IDENT exprs",
"lval : IDENT",
"lval : LPAR NTH lval expr RPAR",
"arg : IDENT COLON type",
"args : arg",
"args : arg COMMA args",
"type : INT",
"type : BOOL",
"type : VOID",
"type : LPAR VEC type RPAR",
"type : LPAR types ARROW type RPAR",
"types : type",
"types : type STAR types",
"expr : TRUE",
"expr : FALSE",
"expr : NUM",
"expr : IDENT",
"expr : LPAR NOT exprs RPAR",
"expr : LPAR AND exprs RPAR",
"expr : LPAR OR exprs RPAR",
"expr : LPAR EQ exprs RPAR",
"expr : LPAR LT exprs RPAR",
"expr : LPAR ADD exprs RPAR",
"expr : LPAR SUB exprs RPAR",
"expr : LPAR MUL exprs RPAR",
"expr : LPAR DIV exprs RPAR",
"expr : LPAR LEN exprs RPAR",
"expr : LPAR ALLOC exprs RPAR",
"expr : LPAR NTH exprs RPAR",
"expr : LBRA args RBRA expr",
"expr : LPAR expr exprs RPAR",
"expr : LPAR IF expr expr expr RPAR",
"exprs : expr",
"exprs : expr exprs",
};

//#line 122 "parser.y"

public AstCmds prog;
private Yylex lexer;
public int yylex () {
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
//#line 347 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 38 "parser.y"
{ prog=(AstCmds)val_peek(0).obj; }
break;
case 2:
//#line 42 "parser.y"
{ yyval.obj = (AstCmds)val_peek(1).obj; }
break;
case 3:
//#line 45 "parser.y"
{ yyval.obj = new AstCmds((Ast)val_peek(0).obj); }
break;
case 4:
//#line 46 "parser.y"
{ yyval.obj = new AstCmds((Ast)val_peek(2).obj, (AstCmds)val_peek(0).obj);}
break;
case 5:
//#line 47 "parser.y"
{ yyval.obj = new AstCmds((Ast)val_peek(2).obj, (AstCmds)val_peek(0).obj);}
break;
case 6:
//#line 50 "parser.y"
{ yyval.obj = new AstConst(new AstIdent(val_peek(2).sval), (Ast)val_peek(1).obj, (IASTExpr)val_peek(0).obj);}
break;
case 7:
//#line 51 "parser.y"
{ yyval.obj = new AstFun(new AstIdent(val_peek(5).sval), (Ast)val_peek(4).obj, (AstArgs)val_peek(2).obj, (IASTExpr)val_peek(0).obj);}
break;
case 8:
//#line 52 "parser.y"
{ yyval.obj = new AstFunRec(new AstIdent(val_peek(5).sval), (Ast)val_peek(4).obj, (AstArgs)val_peek(2).obj, (IASTExpr)val_peek(0).obj);}
break;
case 9:
//#line 53 "parser.y"
{yyval.obj = new AstVar(new AstIdent(val_peek(1).sval), (Ast)val_peek(0).obj); }
break;
case 10:
//#line 54 "parser.y"
{yyval.obj = new AstProc(new AstIdent(val_peek(4).sval), (AstArgs)val_peek(2).obj, (AstCmds)val_peek(0).obj );}
break;
case 11:
//#line 55 "parser.y"
{yyval.obj = new AstProcrec(new AstIdent(val_peek(4).sval), (AstArgs)val_peek(2).obj, (AstCmds)val_peek(0).obj );}
break;
case 12:
//#line 59 "parser.y"
{ yyval.obj = new AstEcho((IASTExpr)val_peek(0).obj); }
break;
case 13:
//#line 60 "parser.y"
{ yyval.obj = new AstSet((IASTLval)val_peek(1).obj, (IASTExpr)val_peek(0).obj);}
break;
case 14:
//#line 61 "parser.y"
{ yyval.obj = new AstAlternative((IASTExpr)val_peek(2).obj, (AstCmds)val_peek(1).obj, (AstCmds)val_peek(0).obj);}
break;
case 15:
//#line 62 "parser.y"
{ yyval.obj = new AstWhile((IASTExpr)val_peek(1).obj, (AstCmds)val_peek(0).obj);}
break;
case 16:
//#line 63 "parser.y"
{ yyval.obj = new AstCall(new AstIdent(val_peek(1).sval), (AstExprs)val_peek(0).obj);}
break;
case 17:
//#line 68 "parser.y"
{yyval.obj = new AstIdent(val_peek(0).sval);}
break;
case 18:
//#line 69 "parser.y"
{yyval.obj = new AstNth((IASTLval)val_peek(2).obj, (IASTExpr)val_peek(1).obj);}
break;
case 19:
//#line 73 "parser.y"
{ yyval.obj = new AstArg(new AstIdent(val_peek(2).sval), (Ast)val_peek(0).obj);}
break;
case 20:
//#line 78 "parser.y"
{yyval.obj = new AstArgs((AstArg)val_peek(0).obj);}
break;
case 21:
//#line 79 "parser.y"
{ yyval.obj = new AstArgs((AstArg)val_peek(2).obj, (AstArgs)val_peek(0).obj);}
break;
case 22:
//#line 83 "parser.y"
{ yyval.obj = new AstType(Type.INT); }
break;
case 23:
//#line 84 "parser.y"
{ yyval.obj = new AstType(Type.BOOL); }
break;
case 24:
//#line 85 "parser.y"
{ yyval.obj = new AstType(Type.VOID); }
break;
case 25:
//#line 86 "parser.y"
{ yyval.obj = new AstType(Type.VEC); }
break;
case 26:
//#line 87 "parser.y"
{ yyval.obj = new AstArrow((Ast)val_peek(3).obj, (Ast)val_peek(1).obj); }
break;
case 27:
//#line 91 "parser.y"
{ yyval.obj = val_peek(0).obj ;}
break;
case 28:
//#line 92 "parser.y"
{ yyval.obj = new AstStar((Ast)val_peek(2).obj, (Ast)val_peek(0).obj); }
break;
case 29:
//#line 96 "parser.y"
{ yyval.obj = new AstTrue(); }
break;
case 30:
//#line 97 "parser.y"
{ yyval.obj = new AstFalse();}
break;
case 31:
//#line 98 "parser.y"
{ yyval.obj = new AstNum(val_peek(0).ival);}
break;
case 32:
//#line 99 "parser.y"
{yyval.obj = new AstIdent(val_peek(0).sval);}
break;
case 33:
//#line 100 "parser.y"
{ yyval.obj = new AstPrim(Op.NOT, (AstExprs)val_peek(1).obj);}
break;
case 34:
//#line 101 "parser.y"
{ yyval.obj = new AstPrim(Op.AND , (AstExprs)val_peek(1).obj);}
break;
case 35:
//#line 102 "parser.y"
{ yyval.obj = new AstPrim(Op.OR , (AstExprs)val_peek(1).obj);}
break;
case 36:
//#line 103 "parser.y"
{ yyval.obj = new AstPrim(Op.EQ , (AstExprs)val_peek(1).obj);}
break;
case 37:
//#line 104 "parser.y"
{ yyval.obj = new AstPrim(Op.LT , (AstExprs)val_peek(1).obj);}
break;
case 38:
//#line 105 "parser.y"
{ yyval.obj = new AstPrim(Op.ADD , (AstExprs)val_peek(1).obj);}
break;
case 39:
//#line 106 "parser.y"
{ yyval.obj = new AstPrim(Op.SUB , (AstExprs)val_peek(1).obj);}
break;
case 40:
//#line 107 "parser.y"
{ yyval.obj = new AstPrim(Op.MUL , (AstExprs)val_peek(1).obj);}
break;
case 41:
//#line 108 "parser.y"
{ yyval.obj = new AstPrim(Op.DIV , (AstExprs)val_peek(1).obj);}
break;
case 42:
//#line 109 "parser.y"
{ yyval.obj = new AstPrim(Op.LEN , (AstExprs)val_peek(1).obj);}
break;
case 43:
//#line 110 "parser.y"
{ yyval.obj = new AstPrim(Op.ALLOC , (AstExprs)val_peek(1).obj);}
break;
case 44:
//#line 111 "parser.y"
{ yyval.obj = new AstPrim(Op.NTH , (AstExprs)val_peek(1).obj);}
break;
case 45:
//#line 112 "parser.y"
{ yyval.obj = new AstAbs((AstArgs)val_peek(2).obj, (IASTExpr)val_peek(0).obj);}
break;
case 46:
//#line 113 "parser.y"
{ yyval.obj = new AstApp((IASTExpr)val_peek(2).obj, (AstExprs)val_peek(1).obj);}
break;
case 47:
//#line 114 "parser.y"
{ yyval.obj = new AstIf((IASTExpr)val_peek(3).obj, (IASTExpr)val_peek(2).obj, (IASTExpr)val_peek(1).obj);}
break;
case 48:
//#line 118 "parser.y"
{ yyval.obj = new AstExprs((IASTExpr)val_peek(0).obj); }
break;
case 49:
//#line 119 "parser.y"
{ yyval.obj = new AstExprs((IASTExpr)val_peek(1).obj, (AstExprs)val_peek(0).obj);}
break;
//#line 692 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
