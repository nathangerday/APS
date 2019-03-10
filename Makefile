LEX_J = java -jar jflex/jflex-full-1.7.0.jar
YACC_J = byacc/byacc -J -Jpackage="aps.parser"
JAVAC = javac

all: toProlog eval

toProlog: parser src/aps/parser/ToProlog.java
	$(JAVAC) src/aps/parser/ToProlog.java -d bin/ -sourcepath src

eval: parser src/aps/parser/Eval.java
	$(JAVAC) src/aps/parser/Eval.java -d bin/ -sourcepath src


parser: parser.y lexer.lex
	$(LEX_J) lexer.lex -d src/aps/parser/
	$(YACC_J) parser.y 
	# How to generate file with byacc directly in bin ?
	mv Parser.java src/aps/parser/Parser.java 
	mv ParserVal.java src/aps/parser/ParserVal.java
clean:
	rm -r bin/*
	rm src/aps/parser/Parser.java
	rm src/aps/parser/ParserVal.java
	rm src/aps/parser/Yylex.java
	rm src/aps/parser/Yylex.java~