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






//#line 2 "parser.y"
import java.io.*;
//#line 19 "Parser.java"




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
public final static short NOT=281;
public final static short AND=282;
public final static short OR=283;
public final static short EQ=284;
public final static short LT=285;
public final static short IF=286;
public final static short MINUS=287;
public final static short PLUS=288;
public final static short TIMES=289;
public final static short NEG=290;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    9,    9,    9,    8,    8,    8,    7,    5,    6,
    6,    3,    3,    3,    4,    4,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    1,    1,
};
final static short yylen[] = {                            2,
    3,    1,    3,    3,    4,    7,    8,    2,    3,    1,
    3,    1,    1,    5,    1,    3,    1,    1,    1,    1,
    4,    5,    5,    5,    5,    5,    5,    5,    5,    4,
    4,    6,    1,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   19,   20,    0,    0,   17,   18,    8,    0,    0,
    1,    0,   12,   13,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    4,    3,    0,    0,    5,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   21,    0,    0,    0,    0,    0,   31,   34,    9,
   11,   30,   16,    0,    0,    0,   26,   27,   28,   29,
   22,   23,   24,   25,    0,   14,    6,    0,   32,    7,
};
final static short yydgoto[] = {                          2,
   59,   60,   44,   45,   40,   41,    6,    7,    8,
};
final static short yysindex[] = {                      -259,
 -268,    0, -247, -241, -223, -256, -253, -248, -263, -263,
 -226,    0,    0, -198, -222,    0,    0,    0, -268, -268,
    0, -263,    0,    0, -223, -228, -263, -223, -223, -223,
 -223, -223, -223, -223, -223, -223, -223, -223, -229, -232,
 -225,    0,    0, -216, -204,    0, -222, -218, -223, -223,
 -223, -223, -196, -223, -223, -223, -223, -223, -195, -223,
 -263, -222, -223, -263, -263, -194, -222, -191, -190, -189,
 -188,    0, -187, -186, -176, -175, -223,    0,    0,    0,
    0,    0,    0, -174, -223, -173,    0,    0,    0,    0,
    0,    0,    0,    0, -170,    0,    0, -223,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0, -171,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -169,
    0,    0,    0, -200,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -168,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   32,   -5,   -6,   35,    0,  -45,    0,    0,   -9,
};
final static int YYTABLESIZE=99;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         18,
   22,   66,   25,   26,    3,    4,    1,    5,   38,   42,
   43,    9,   19,   23,   24,   20,   81,   10,   21,   46,
   48,   86,   49,   50,   51,   52,   53,   54,   55,   56,
   57,   58,   27,   11,   12,   13,   39,   47,   61,   62,
   14,   63,   15,   68,   69,   70,   71,   67,   73,   74,
   75,   76,   77,   64,   80,   16,   17,   82,   84,   12,
   13,   28,   29,   30,   31,   14,   65,   15,   72,   78,
   15,   95,   85,   87,   88,   89,   90,   91,   92,   97,
   16,   17,   32,   33,   34,   35,   36,   37,   93,   94,
   96,   79,  100,   98,   99,    2,   33,   10,   83,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          5,
  264,   47,    9,   10,  273,  274,  266,  276,   14,   19,
   20,  259,  269,  277,  278,  269,   62,  259,  267,   25,
   27,   67,   28,   29,   30,   31,   32,   33,   34,   35,
   36,   37,  259,  275,  258,  259,  259,  266,  268,  272,
  264,  267,  266,   49,   50,   51,   52,  266,   54,   55,
   56,   57,   58,  270,   61,  279,  280,   63,   65,  258,
  259,  260,  261,  262,  263,  264,  271,  266,  265,  265,
  271,   77,  267,  265,  265,  265,  265,  265,  265,   85,
  279,  280,  281,  282,  283,  284,  285,  286,  265,  265,
  265,   60,   98,  267,  265,  267,  265,  267,   64,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=290;
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
"INT","BOOL","TRUE","FALSE","NOT","AND","OR","EQ","LT","IF","MINUS","PLUS",
"TIMES","NEG",
};
final static String yyrule[] = {
"$accept : prog",
"prog : LBRA cmds RBRA",
"cmds : stat",
"cmds : dec SEMICOLON cmds",
"cmds : stat SEMICOLON cmds",
"dec : CONST IDENT type expr",
"dec : FUN IDENT type LBRA args RBRA expr",
"dec : FUN REC IDENT type LBRA args RBRA expr",
"stat : ECHO expr",
"arg : IDENT COLON type",
"args : arg",
"args : arg COMMA args",
"type : INT",
"type : BOOL",
"type : LPAR types ARROW type RPAR",
"types : type",
"types : type STAR types",
"expr : TRUE",
"expr : FALSE",
"expr : NUM",
"expr : IDENT",
"expr : LPAR NOT expr RPAR",
"expr : LPAR AND expr expr RPAR",
"expr : LPAR OR expr expr RPAR",
"expr : LPAR EQ expr expr RPAR",
"expr : LPAR LT expr expr RPAR",
"expr : LPAR ADD expr expr RPAR",
"expr : LPAR SUB expr expr RPAR",
"expr : LPAR MUL expr expr RPAR",
"expr : LPAR DIV expr expr RPAR",
"expr : LBRA args RBRA expr",
"expr : LPAR expr exprs RPAR",
"expr : LPAR IF expr expr expr RPAR",
"exprs : expr",
"exprs : expr exprs",
};

//#line 98 "parser.y"

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
//#line 295 "Parser.java"
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
//#line 34 "parser.y"
{ prog=(AstCmds)val_peek(1).obj; yyval.obj=(AstCmds)val_peek(1).obj; }
break;
case 2:
//#line 38 "parser.y"
{ yyval.obj = new AstCmds((Ast)val_peek(0).obj); }
break;
case 3:
//#line 39 "parser.y"
{ yyval.obj = new AstCmds((Ast)val_peek(2).obj, (Ast)val_peek(0).obj);}
break;
case 4:
//#line 40 "parser.y"
{ yyval.obj = new AstCmds((Ast)val_peek(2).obj, (Ast)val_peek(0).obj);}
break;
case 5:
//#line 43 "parser.y"
{ yyval.obj = new AstConst(new AstIdent(val_peek(2).sval), (Ast)val_peek(1).obj, (IASTExpr)val_peek(0).obj);}
break;
case 6:
//#line 44 "parser.y"
{ yyval.obj = new AstFun(new AstIdent(val_peek(5).sval), (Ast)val_peek(4).obj, (Ast)val_peek(2).obj, (IASTExpr)val_peek(0).obj);}
break;
case 7:
//#line 45 "parser.y"
{ yyval.obj = new AstFunRec(new AstIdent(val_peek(5).sval), (Ast)val_peek(4).obj, (Ast)val_peek(2).obj, (IASTExpr)val_peek(0).obj);}
break;
case 8:
//#line 49 "parser.y"
{ yyval.obj = new AstEcho((IASTExpr)val_peek(0).obj); }
break;
case 9:
//#line 54 "parser.y"
{ yyval.obj = new AstArg(new AstIdent(val_peek(2).sval), (Ast)val_peek(0).obj);}
break;
case 10:
//#line 59 "parser.y"
{yyval.obj = new AstArgs((Ast)val_peek(0).obj);}
break;
case 11:
//#line 60 "parser.y"
{ yyval.obj = new AstArgs((Ast)val_peek(2).obj, (Ast)val_peek(0).obj);}
break;
case 12:
//#line 64 "parser.y"
{ yyval.obj = new AstType(Type.INT); }
break;
case 13:
//#line 65 "parser.y"
{ yyval.obj = new AstType(Type.BOOL); }
break;
case 14:
//#line 66 "parser.y"
{ yyval.obj = new AstArrow((Ast)val_peek(3).obj, (Ast)val_peek(1).obj); }
break;
case 15:
//#line 70 "parser.y"
{ yyval.obj = val_peek(0).obj ;}
break;
case 16:
//#line 71 "parser.y"
{ yyval.obj = new AstStar((Ast)val_peek(2).obj, (Ast)val_peek(0).obj); }
break;
case 17:
//#line 75 "parser.y"
{ yyval.obj = new AstTrue(); }
break;
case 18:
//#line 76 "parser.y"
{ yyval.obj = new AstFalse();}
break;
case 19:
//#line 77 "parser.y"
{ yyval.obj = new AstNum(val_peek(0).ival);}
break;
case 20:
//#line 78 "parser.y"
{yyval.obj = new AstIdent(val_peek(0).sval);}
break;
case 21:
//#line 79 "parser.y"
{ yyval.obj = new AstPrim(Op.NOT, (IASTExpr)val_peek(1).obj, null);}
break;
case 22:
//#line 80 "parser.y"
{ yyval.obj = new AstPrim(Op.AND ,(IASTExpr)val_peek(2).obj, (IASTExpr)val_peek(1).obj);}
break;
case 23:
//#line 81 "parser.y"
{ yyval.obj = new AstPrim(Op.OR ,(IASTExpr)val_peek(2).obj, (IASTExpr)val_peek(1).obj);}
break;
case 24:
//#line 82 "parser.y"
{ yyval.obj = new AstPrim(Op.EQ ,(IASTExpr)val_peek(2).obj, (IASTExpr)val_peek(1).obj);}
break;
case 25:
//#line 83 "parser.y"
{ yyval.obj = new AstPrim(Op.LT ,(IASTExpr)val_peek(2).obj, (IASTExpr)val_peek(1).obj);}
break;
case 26:
//#line 84 "parser.y"
{ yyval.obj = new AstPrim(Op.ADD ,(IASTExpr)val_peek(2).obj, (IASTExpr)val_peek(1).obj);}
break;
case 27:
//#line 85 "parser.y"
{ yyval.obj = new AstPrim(Op.SUB ,(IASTExpr)val_peek(2).obj, (IASTExpr)val_peek(1).obj);}
break;
case 28:
//#line 86 "parser.y"
{ yyval.obj = new AstPrim(Op.MUL ,(IASTExpr)val_peek(2).obj, (IASTExpr)val_peek(1).obj);}
break;
case 29:
//#line 87 "parser.y"
{ yyval.obj = new AstPrim(Op.DIV ,(IASTExpr)val_peek(2).obj, (IASTExpr)val_peek(1).obj);}
break;
case 30:
//#line 88 "parser.y"
{ yyval.obj = new AstBlock((Ast)val_peek(2).obj, (IASTExpr)val_peek(0).obj);}
break;
case 31:
//#line 89 "parser.y"
{ yyval.obj = new AstInvoc((IASTExpr)val_peek(2).obj, (Ast)val_peek(1).obj);}
break;
case 32:
//#line 90 "parser.y"
{ yyval.obj = new AstIf((IASTExpr)val_peek(3).obj, (IASTExpr)val_peek(2).obj, (IASTExpr)val_peek(1).obj);}
break;
case 33:
//#line 94 "parser.y"
{ yyval.obj = new AstExprs((IASTExpr)val_peek(0).obj); }
break;
case 34:
//#line 95 "parser.y"
{ yyval.obj = new AstExprs((IASTExpr)val_peek(1).obj, (Ast)val_peek(0).obj);}
break;
//#line 580 "Parser.java"
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
