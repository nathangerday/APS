LEX_J = jflex/bin/jflex
YACC_J = byacc/byacc -J 
JAVAC = javac

toProlog: parser Ast/Op.java Ast/Type.java ToProlog.java
	$(JAVAC) ToProlog.java -d bin/ -sourcepath Ast/:bin/

parser: parser.y lexer.lex
	$(LEX_J) lexer.lex -d bin/
	$(YACC_J) parser.y 
	# How to generate file with byacc directly in bin ?
	mv Parser.java bin/Parser.java 
	mv ParserVal.java bin/ParserVal.java
clean:
	rm bin/Parser*.java
	rm bin/Yylex.java
	rm bin/*.class
	rm bin/Yylex.java~