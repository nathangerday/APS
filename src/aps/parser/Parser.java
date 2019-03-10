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
public final static short LPAR=264;
public final static short RPAR=265;
public final static short LBRA=266;
public final static short RBRA=267;
public final static short COLON=268;
public final static short SEMICOLON=269;
public final static short STAR=270;
public final static short ARROW=271;
public final static short COMMA=272;
public final static short CONST=273;
public final static short FUN=274;
public final static short REC=275;
public final static short ECHO=276;
public final static short INT=277;
public final static short BOOL=278;
public final static short TRUE=279;
public final static short FALSE=280;
public final static short VOID=281;
public final static short NOT=282;
public final static short AND=283;
public final static short OR=284;
public final static short EQ=285;
public final static short LT=286;
public final static short IF=287;
public final static short VAR=288;
public final static short PROC=289;
public final static short SET=290;
public final static short WHILE=291;
public final static short CALL=292;
public final static short MINUS=293;
public final static short PLUS=294;
public final static short TIMES=295;
public final static short NEG=296;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,   10,    9,    9,    9,    8,    8,    8,    8,    8,
    8,    7,    7,    7,    7,    7,    5,    6,    6,    3,
    3,    3,    3,    4,    4,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    1,    1,
};
final static short yylen[] = {                            2,
    1,    3,    1,    3,    3,    4,    7,    8,    3,    6,
    7,    2,    3,    4,    3,    3,    3,    1,    3,    1,
    1,    1,    5,    1,    3,    1,    1,    1,    1,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    6,    1,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   28,   29,
    0,    0,   26,   27,   12,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    2,    0,   20,   21,   22,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    9,    0,    0,
   13,   15,   16,    0,    5,    4,    0,    0,    6,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   14,    0,    0,   43,    0,
    0,    0,    0,   35,   36,   37,   38,   30,   31,   32,
   33,   34,    0,   40,   17,   19,   39,    0,    0,   25,
    0,    0,    0,    0,   10,    0,   23,    7,    0,   41,
   11,    8,
};
final static short yydgoto[] = {                          2,
   63,   64,   67,   68,   55,   56,   13,   14,   15,    3,
};
final static short yysindex[] = {                      -253,
 -180,    0,    0, -243, -250, -238, -238, -237, -248, -236,
 -238, -229, -233, -232, -235, -205, -205, -221,    0,    0,
 -197, -220,    0,    0,    0, -253, -205, -223, -215, -238,
 -253, -238, -180, -180,    0, -205,    0,    0,    0, -238,
 -210, -205, -238, -238, -238, -238, -238, -238, -238, -238,
 -238, -238, -238, -208, -227, -209, -253,    0, -220, -195,
    0,    0,    0, -238,    0,    0, -196, -194,    0, -220,
 -186, -184, -174, -170, -167, -166, -165, -164, -163, -162,
 -238, -161, -205, -220, -238,    0, -153, -220,    0, -205,
 -205, -152, -220,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -238,    0,    0,    0,    0, -253, -151,    0,
 -160, -238, -150, -147,    0, -253,    0,    0, -238,    0,
    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -148,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -146,    0,    0,    0,    0,    0,
    0,    0,    0, -257,    0,    0, -149,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    4,   -6,  -13,   30,    0,  -53,    0,    0,  -15,  -24,
};
final static int YYTABLESIZE=122;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         25,
   26,   57,   40,   41,   31,   87,   62,   42,   17,   42,
   28,   42,    1,   58,   53,   16,   92,   65,   66,   19,
   20,   27,   30,   61,   18,   21,   29,   22,   71,   32,
  106,   35,   86,   69,  109,   33,   34,   42,   54,  113,
   23,   24,   59,   60,   84,   81,   72,   73,   74,   75,
   76,   77,   78,   79,   80,   70,   82,   85,   36,   83,
   19,   20,   43,   44,   45,   46,   21,   89,   22,  105,
   88,   37,   38,   90,  103,   39,   91,  111,  107,   93,
   94,   23,   24,  115,   47,   48,   49,   50,   51,   52,
   95,  121,    4,    5,   96,    6,  114,   97,   98,   99,
  100,  101,  102,  104,  117,  118,    7,    8,    9,   10,
   11,   12,  122,  108,  112,  116,  119,  120,    3,  110,
   18,   24,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          6,
    7,   26,   16,   17,   11,   59,   31,  265,  259,  267,
  259,  269,  266,   27,   21,  259,   70,   33,   34,  258,
  259,  259,  259,   30,  275,  264,  275,  266,   42,  259,
   84,  267,   57,   40,   88,  269,  269,  259,  259,   93,
  279,  280,  266,  259,  272,   52,   43,   44,   45,   46,
   47,   48,   49,   50,   51,  266,   53,  267,  264,  268,
  258,  259,  260,  261,  262,  263,  264,   64,  266,   83,
  266,  277,  278,  270,   81,  281,  271,   91,   85,  266,
  265,  279,  280,  108,  282,  283,  284,  285,  286,  287,
  265,  116,  273,  274,  265,  276,  103,  265,  265,  265,
  265,  265,  265,  265,  265,  112,  287,  288,  289,  290,
  291,  292,  119,  267,  267,  267,  267,  265,  267,   90,
  267,  271,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=296;
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
null,null,null,"NL","NUM","IDENT","ADD","SUB","MUL","DIV","LPAR","RPAR","LBRA",
"RBRA","COLON","SEMICOLON","STAR","ARROW","COMMA","CONST","FUN","REC","ECHO",
"INT","BOOL","TRUE","FALSE","VOID","NOT","AND","OR","EQ","LT","IF","VAR","PROC",
"SET","WHILE","CALL","MINUS","PLUS","TIMES","NEG",
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
"stat : SET IDENT expr",
"stat : IF expr block block",
"stat : WHILE expr block",
"stat : CALL IDENT exprs",
"arg : IDENT COLON type",
"args : arg",
"args : arg COMMA args",
"type : INT",
"type : BOOL",
"type : VOID",
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
"expr : LBRA args RBRA expr",
"expr : LPAR expr exprs RPAR",
"expr : LPAR IF expr expr expr RPAR",
"exprs : expr",
"exprs : expr exprs",
};

//#line 111 "parser.y"

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
//#line 328 "Parser.java"
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
//#line 36 "parser.y"
{ prog=(AstCmds)val_peek(0).obj; }
break;
case 2:
//#line 40 "parser.y"
{ yyval.obj = (AstCmds)val_peek(1).obj; }
break;
case 3:
//#line 43 "parser.y"
{ yyval.obj = new AstCmds((Ast)val_peek(0).obj); }
break;
case 4:
//#line 44 "parser.y"
{ yyval.obj = new AstCmds((Ast)val_peek(2).obj, (AstCmds)val_peek(0).obj);}
break;
case 5:
//#line 45 "parser.y"
{ yyval.obj = new AstCmds((Ast)val_peek(2).obj, (AstCmds)val_peek(0).obj);}
break;
case 6:
//#line 48 "parser.y"
{ yyval.obj = new AstConst(new AstIdent(val_peek(2).sval), (Ast)val_peek(1).obj, (IASTExpr)val_peek(0).obj);}
break;
case 7:
//#line 49 "parser.y"
{ yyval.obj = new AstFun(new AstIdent(val_peek(5).sval), (Ast)val_peek(4).obj, (AstArgs)val_peek(2).obj, (IASTExpr)val_peek(0).obj);}
break;
case 8:
//#line 50 "parser.y"
{ yyval.obj = new AstFunRec(new AstIdent(val_peek(5).sval), (Ast)val_peek(4).obj, (AstArgs)val_peek(2).obj, (IASTExpr)val_peek(0).obj);}
break;
case 9:
//#line 51 "parser.y"
{yyval.obj = new AstVar(new AstIdent(val_peek(1).sval), (Ast)val_peek(0).obj); }
break;
case 10:
//#line 52 "parser.y"
{yyval.obj = new AstProc(new AstIdent(val_peek(4).sval), (AstArgs)val_peek(2).obj, (AstCmds)val_peek(0).obj );}
break;
case 11:
//#line 53 "parser.y"
{yyval.obj = new AstProcrec(new AstIdent(val_peek(4).sval), (AstArgs)val_peek(2).obj, (AstCmds)val_peek(0).obj );}
break;
case 12:
//#line 57 "parser.y"
{ yyval.obj = new AstEcho((IASTExpr)val_peek(0).obj); }
break;
case 13:
//#line 58 "parser.y"
{ yyval.obj = new AstSet(new AstIdent(val_peek(1).sval), (IASTExpr)val_peek(0).obj);}
break;
case 14:
//#line 59 "parser.y"
{ yyval.obj = new AstAlternative((IASTExpr)val_peek(2).obj, (AstCmds)val_peek(1).obj, (AstCmds)val_peek(0).obj);}
break;
case 15:
//#line 60 "parser.y"
{ yyval.obj = new AstWhile((IASTExpr)val_peek(1).obj, (AstCmds)val_peek(0).obj);}
break;
case 16:
//#line 61 "parser.y"
{ yyval.obj = new AstCall(new AstIdent(val_peek(1).sval), (AstExprs)val_peek(0).obj);}
break;
case 17:
//#line 66 "parser.y"
{ yyval.obj = new AstArg(new AstIdent(val_peek(2).sval), (Ast)val_peek(0).obj);}
break;
case 18:
//#line 71 "parser.y"
{yyval.obj = new AstArgs((AstArg)val_peek(0).obj);}
break;
case 19:
//#line 72 "parser.y"
{ yyval.obj = new AstArgs((AstArg)val_peek(2).obj, (AstArgs)val_peek(0).obj);}
break;
case 20:
//#line 76 "parser.y"
{ yyval.obj = new AstType(Type.INT); }
break;
case 21:
//#line 77 "parser.y"
{ yyval.obj = new AstType(Type.BOOL); }
break;
case 22:
//#line 78 "parser.y"
{ yyval.obj = new AstType(Type.VOID); }
break;
case 23:
//#line 79 "parser.y"
{ yyval.obj = new AstArrow((Ast)val_peek(3).obj, (Ast)val_peek(1).obj); }
break;
case 24:
//#line 83 "parser.y"
{ yyval.obj = val_peek(0).obj ;}
break;
case 25:
//#line 84 "parser.y"
{ yyval.obj = new AstStar((Ast)val_peek(2).obj, (Ast)val_peek(0).obj); }
break;
case 26:
//#line 88 "parser.y"
{ yyval.obj = new AstTrue(); }
break;
case 27:
//#line 89 "parser.y"
{ yyval.obj = new AstFalse();}
break;
case 28:
//#line 90 "parser.y"
{ yyval.obj = new AstNum(val_peek(0).ival);}
break;
case 29:
//#line 91 "parser.y"
{yyval.obj = new AstIdent(val_peek(0).sval);}
break;
case 30:
//#line 92 "parser.y"
{ yyval.obj = new AstPrim(Op.NOT, (AstExprs)val_peek(1).obj);}
break;
case 31:
//#line 93 "parser.y"
{ yyval.obj = new AstPrim(Op.AND , (AstExprs)val_peek(1).obj);}
break;
case 32:
//#line 94 "parser.y"
{ yyval.obj = new AstPrim(Op.OR , (AstExprs)val_peek(1).obj);}
break;
case 33:
//#line 95 "parser.y"
{ yyval.obj = new AstPrim(Op.EQ , (AstExprs)val_peek(1).obj);}
break;
case 34:
//#line 96 "parser.y"
{ yyval.obj = new AstPrim(Op.LT , (AstExprs)val_peek(1).obj);}
break;
case 35:
//#line 97 "parser.y"
{ yyval.obj = new AstPrim(Op.ADD , (AstExprs)val_peek(1).obj);}
break;
case 36:
//#line 98 "parser.y"
{ yyval.obj = new AstPrim(Op.SUB , (AstExprs)val_peek(1).obj);}
break;
case 37:
//#line 99 "parser.y"
{ yyval.obj = new AstPrim(Op.MUL , (AstExprs)val_peek(1).obj);}
break;
case 38:
//#line 100 "parser.y"
{ yyval.obj = new AstPrim(Op.DIV , (AstExprs)val_peek(1).obj);}
break;
case 39:
//#line 101 "parser.y"
{ yyval.obj = new AstAbs((AstArgs)val_peek(2).obj, (IASTExpr)val_peek(0).obj);}
break;
case 40:
//#line 102 "parser.y"
{ yyval.obj = new AstApp((IASTExpr)val_peek(2).obj, (AstExprs)val_peek(1).obj);}
break;
case 41:
//#line 103 "parser.y"
{ yyval.obj = new AstIf((IASTExpr)val_peek(3).obj, (IASTExpr)val_peek(2).obj, (IASTExpr)val_peek(1).obj);}
break;
case 42:
//#line 107 "parser.y"
{ yyval.obj = new AstExprs((IASTExpr)val_peek(0).obj); }
break;
case 43:
//#line 108 "parser.y"
{ yyval.obj = new AstExprs((IASTExpr)val_peek(1).obj, (AstExprs)val_peek(0).obj);}
break;
//#line 649 "Parser.java"
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
