/* The following code was generated by JFlex 1.7.0 */

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>lexer.lex</tt>
 */
class Yylex {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\10\0\1\51\2\5\25\0\1\5\6\0\1\5\1\17\1\20\1\26"+
    "\1\0\1\25\1\1\2\0\12\2\1\23\1\24\2\0\1\27\2\0"+
    "\2\4\1\30\1\4\1\40\1\35\1\4\1\41\5\4\1\32\1\31"+
    "\2\4\1\37\1\33\1\34\1\36\5\4\1\21\1\0\1\22\3\0"+
    "\1\6\1\12\1\3\1\7\1\46\1\47\2\3\1\15\2\3\1\14"+
    "\1\13\1\42\1\44\1\3\1\50\1\45\1\10\1\43\1\11\1\16"+
    "\4\3\1\0\1\5\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uff93\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\2\1\1\2\1\3\1\4\7\3\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\4\1\5\3"+
    "\1\15\1\16\6\3\1\17\1\3\4\0\2\3\1\20"+
    "\1\21\1\3\1\22\1\23\1\24\1\25\1\3\1\26"+
    "\1\27\1\0\1\30\1\31\1\0\1\32\2\3\1\33"+
    "\1\0\1\34\1\35\1\3\1\36\1\37";

  private static int [] zzUnpackAction() {
    int [] result = new int[70];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\52\0\124\0\176\0\250\0\52\0\322\0\374"+
    "\0\u0126\0\u0150\0\u017a\0\u01a4\0\u01ce\0\52\0\52\0\52"+
    "\0\52\0\52\0\52\0\52\0\52\0\u01f8\0\u0222\0\u024c"+
    "\0\u0276\0\u02a0\0\u02ca\0\u02f4\0\u031e\0\u0348\0\52\0\52"+
    "\0\u0372\0\u039c\0\u03c6\0\u03f0\0\u041a\0\u0444\0\250\0\u046e"+
    "\0\u0498\0\u04c2\0\u04ec\0\u0516\0\u0540\0\u056a\0\250\0\250"+
    "\0\u0594\0\250\0\250\0\250\0\250\0\u05be\0\250\0\250"+
    "\0\u05e8\0\52\0\52\0\u0612\0\250\0\u063c\0\u0666\0\250"+
    "\0\u0690\0\52\0\250\0\u06ba\0\52\0\250";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[70];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\2\1\6\1\7\1\10"+
    "\1\11\1\5\1\12\1\13\1\14\1\15\1\5\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\2"+
    "\1\26\4\2\1\27\1\2\1\30\1\31\1\2\1\32"+
    "\1\33\1\34\1\5\1\35\1\36\1\5\1\37\54\0"+
    "\1\4\24\0\1\40\24\0\1\4\51\0\3\5\1\0"+
    "\11\5\11\0\21\5\3\0\3\5\1\0\1\5\1\41"+
    "\7\5\11\0\12\5\1\42\6\5\3\0\3\5\1\0"+
    "\7\5\1\43\1\5\11\0\21\5\3\0\3\5\1\0"+
    "\3\5\1\44\5\5\11\0\21\5\3\0\3\5\1\0"+
    "\11\5\11\0\14\5\1\45\4\5\3\0\3\5\1\0"+
    "\3\5\1\46\5\5\11\0\21\5\3\0\3\5\1\0"+
    "\11\5\11\0\13\5\1\47\5\5\3\0\3\5\1\0"+
    "\11\5\11\0\12\5\1\50\6\5\32\0\1\51\56\0"+
    "\1\52\53\0\1\53\41\0\1\54\23\0\3\5\1\0"+
    "\11\5\11\0\14\5\1\55\4\5\3\0\3\5\1\0"+
    "\11\5\11\0\15\5\1\56\3\5\3\0\3\5\1\0"+
    "\11\5\11\0\15\5\1\57\3\5\3\0\3\5\1\0"+
    "\11\5\11\0\20\5\1\60\3\0\3\5\1\0\1\61"+
    "\10\5\11\0\21\5\3\0\3\5\1\0\1\5\1\62"+
    "\7\5\11\0\21\5\3\0\3\5\1\0\1\5\1\63"+
    "\7\5\11\0\21\5\3\0\3\5\1\0\10\5\1\64"+
    "\11\0\21\5\3\0\3\5\1\0\4\5\1\65\4\5"+
    "\11\0\21\5\3\0\3\5\1\0\11\5\11\0\14\5"+
    "\1\66\4\5\3\0\3\5\1\0\6\5\1\67\2\5"+
    "\11\0\21\5\3\0\3\5\1\0\11\5\11\0\13\5"+
    "\1\70\5\5\33\0\1\71\51\0\1\72\47\0\1\73"+
    "\62\0\1\74\12\0\3\5\1\0\11\5\11\0\13\5"+
    "\1\75\5\5\3\0\3\5\1\0\3\5\1\76\5\5"+
    "\11\0\21\5\3\0\3\5\1\0\6\5\1\77\2\5"+
    "\11\0\21\5\3\0\3\5\1\0\6\5\1\100\2\5"+
    "\11\0\21\5\34\0\1\101\47\0\1\102\22\0\3\5"+
    "\1\0\11\5\11\0\16\5\1\103\2\5\3\0\3\5"+
    "\1\0\2\5\1\104\6\5\11\0\21\5\35\0\1\105"+
    "\17\0\3\5\1\0\11\5\11\0\16\5\1\106\2\5"+
    "\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1764];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\3\1\1\11\7\1\10\11\11\1\2\11"+
    "\10\1\4\0\14\1\1\0\2\11\1\0\4\1\1\0"+
    "\1\11\2\1\1\11\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[70];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true iff the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true iff the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    private Parser yyparser;
        public Yylex(java.io.Reader r, Parser yyparser) {
            this(r);
            this.yyparser = yyparser;
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Yylex(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 160) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return 0; }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { System.err.println("Error: unexpected character ’"+yytext()+"’");             return -1;
            } 
            // fall through
          case 32: break;
          case 2: 
            { yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));
         return Parser.NUM;
            } 
            // fall through
          case 33: break;
          case 3: 
            { yyparser.yylval = new ParserVal(yytext());
          return Parser.IDENT;
            } 
            // fall through
          case 34: break;
          case 4: 
            { 
            } 
            // fall through
          case 35: break;
          case 5: 
            { return Parser.LPAR;
            } 
            // fall through
          case 36: break;
          case 6: 
            { return Parser.RPAR;
            } 
            // fall through
          case 37: break;
          case 7: 
            { return Parser.LBRA;
            } 
            // fall through
          case 38: break;
          case 8: 
            { return Parser.RBRA;
            } 
            // fall through
          case 39: break;
          case 9: 
            { return Parser.COLON;
            } 
            // fall through
          case 40: break;
          case 10: 
            { return Parser.SEMICOLON;
            } 
            // fall through
          case 41: break;
          case 11: 
            { return Parser.COMMA;
            } 
            // fall through
          case 42: break;
          case 12: 
            { return Parser.STAR;
            } 
            // fall through
          case 43: break;
          case 13: 
            { System.err.println("Sorry, backspace doesn’t work");
            } 
            // fall through
          case 44: break;
          case 14: 
            { return Parser.ARROW;
            } 
            // fall through
          case 45: break;
          case 15: 
            { return Parser.LT;
            } 
            // fall through
          case 46: break;
          case 16: 
            { return Parser.OR;
            } 
            // fall through
          case 47: break;
          case 17: 
            { return Parser.EQ;
            } 
            // fall through
          case 48: break;
          case 18: 
            { return Parser.PLUS;
            } 
            // fall through
          case 49: break;
          case 19: 
            { return Parser.AND;
            } 
            // fall through
          case 50: break;
          case 20: 
            { return Parser.DIV;
            } 
            // fall through
          case 51: break;
          case 21: 
            { return Parser.MINUS;
            } 
            // fall through
          case 52: break;
          case 22: 
            { return Parser.TIMES;
            } 
            // fall through
          case 53: break;
          case 23: 
            { return Parser.INT;
            } 
            // fall through
          case 54: break;
          case 24: 
            { return Parser.FUN;
            } 
            // fall through
          case 55: break;
          case 25: 
            { return Parser.REC;
            } 
            // fall through
          case 56: break;
          case 26: 
            { return Parser.NOT;
            } 
            // fall through
          case 57: break;
          case 27: 
            { return Parser.BOOL;
            } 
            // fall through
          case 58: break;
          case 28: 
            { return Parser.ECHO;
            } 
            // fall through
          case 59: break;
          case 29: 
            { return Parser.TRUE;
            } 
            // fall through
          case 60: break;
          case 30: 
            { return Parser.CONST;
            } 
            // fall through
          case 61: break;
          case 31: 
            { return Parser.FALSE;
            } 
            // fall through
          case 62: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
