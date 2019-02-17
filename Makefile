LEX_J = jflex/bin/jflex
YACC_J = ./byacc -J 
JAVAC = javac

toProlog: parser Op.java ToProlog.java
	$(JAVAC) ToProlog.java

parser: parser.y lexer.lex
	$(LEX_J) lexer.lex
	$(YACC_J) parser.y
clean:
	rm Parser*.java
	rm Yylex.java
	rm *.class