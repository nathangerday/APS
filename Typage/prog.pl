typeExpr(_, true, bool).
typeExpr(_, false, bool).

typeExpr(_, X, int) :- integer(X).
typeExpr(G, X, T) :- string(X), assoc(X, G, T).

typeExpr(G, not(X), bool) :- typeExpr(G, X, bool).

typeExpr(G, and(X, Y), bool) :- typeExpr(G, X, bool), typeExpr(G, Y, bool).

typeExpr(G, or(X, Y), bool) :- typeExpr(G, X, bool), typeExpr(G, Y, bool).

typeExpr(G, eq(X, Y), bool) :- typeExpr(G, X, int), typeExpr(G, Y, int).    

typeExpr(G, lt(X, Y), bool) :- typeExpr(G, X, int), typeExpr(G, Y, int).    

typeExpr(G, add(X, Y), int) :- typeExpr(G, X, int), typeExpr(G, Y, int).    

typeExpr(G, sub(X, Y), int) :- typeExpr(G, X, int), typeExpr(G, Y, int).    

typeExpr(G, mul(X, Y), int) :- typeExpr(G, X, int), typeExpr(G, Y, int).    

typeExpr(G, div(X,Y), int) :- typeExpr(G, X, int), typeExpr(G, Y, int).    

typeExpr(G, if(X, Y, Z), T) :- typeExpr(G, X, bool), typeExpr(G, Y, T), typeExpr(G, Z, T).

typeExpr(G, block(A, Y), [TS | [T]]) :-
    typeArgs([], A, NG), % Cree le nouveau contexte en fonction des args
    extract_types(NG, TS), % Extraie les différents types des args pour le obtenir le type de retour
    append(NG, G, NNG), % Ajout du contexte courant au contexte des args 
    typeExpr(NNG, Y, T).

typeExpr(G, invoc(X, Y), S) :-
    typeExprs(G, Y, TE),
    typeExpr(G, X, [T | [S]]),
    compare(=, T, TE).





typeExprs(G, exprs(X, Y), [T | TE]) :-
    typeExpr(G, X, T),
    typeExprs(G, Y, TE).

typeExprs(_, exprs(), []).

% typeExprs(G, exprs(X, Y), [T | [T2]]) :-
%     typeExpr(G, X, T),
%     typeExpr(G, Y, T2).

% typeExprs(G, exprs(X), [T]) :-
%     typeExpr(G, X, T).




% typeArgs(G, args(A, B), NNG) :-
%     typeArg(G, A, NG),
%     typeArg(NG, B, NNG).

typeArgs(G, args(A, As), NNG):-
    typeArg(G, A, NG),
    typeArgs(NG, As, NNG).

typeArgs(G, args(), G).

typeArg(G, arg(X, Y), NG) :-
    string(X), 
    typeType(Y),
    convertTypeToProlog(Y, NY),
    add(G,  (X, NY), NG).




typeType(X) :-
    X = bool.

typeType(X) :-
    X = int.

typeType(arrow(X, Y)) :-
    typeTypes(X),
    typeType(Y).

typeTypes(X) :-
    typeType(X).

typeTypes(star(X, Y)) :-
    typeType(X),
    typeTypes(Y).


% INSTRUCTION

typeInst(G, echo(X), void) :-
    typeExpr(G, X, int).

% Declaration

typeDec(G, const(Name, Type, Expr), NG) :-
    typeType(Type),
    convertTypeToProlog(Type, NType),
    typeExpr(G, Expr, NType),
    append([(Name, NType)], G, NG).

typeDec(G, fun(Name, Type, Args, Expr), GP) :-
    typeType(Type),
    convertTypeToProlog(Type, NType),
    typeArgs([], Args, NG), % Cree le nouveau contexte en fonction des args
    extract_types(NG, TS), % Extraie les différents types des args pour le obtenir le type de retour
    append(NG, G, NNG), % Ajout du contexte courant au contexte des args 
    typeExpr(NNG, Expr, NType),
    append([(Name, [TS | [NType]])], G, GP).


typeDec(G, funrec(Name, Type, Args, Expr), GP) :-
    typeType(Type),
    convertTypeToProlog(Type, NType),
    typeArgs([], Args, NG), % Cree le nouveau contexte en fonction des args
    extract_types(NG, TS), % Extraie les différents types des args pour le obtenir le type de retour
    append(NG, G, NNG), % Ajout du contexte courant au contexte des args
    append([(Name, [TS | [NType]])], NNG, NNNG), % Ajout de la fonction actuellement définie dans le contexte pour verifier la recursion
    typeExpr(NNNG, Expr, Type),
    append([(Name, [TS | [NType]])], G, GP).


% Commands

typeCmds(G, cmds(D, Cs), void) :-
    typeDec(G, D, NG),
    typeCmds(NG, Cs, void).

typeCmds(G, cmds(S, Cs), void) :-
    typeInst(G, S, void),
    typeCmds(G, Cs, void).

typeCmds(_, cmds(), void).

% Program

typeProg(P, void) :-
    typeCmds([], P, void).

% ============= UTILS ============= %

main_stdin :-
    read(user_input, T),
    typeProg(T, R),
    print(R),
    nl. 

extract_types([(_, X)], [X]).

extract_types([(_, X) | Y], [X | R]) :-
    extract_types(Y, R).


assoc(X, [(X, V)|_], V).
assoc(X, [_|XS], V) :-
    assoc(X, XS, V).

add([], (X,Y), [(X,Y)]).
add(L, (X,Y), [(X,Y) | L]).

last(X, [X]).
last(X, [_|Z]) :-
    last(X, Z).


% Exemple de conversion de type :
% ?- convertTypeToProlog(arrow(star(int, star(bool, bool)), int), X).
% X = [[int, bool, bool], int] 

convertTypeToProlog(arrow(X, Y), [NX | [NY]]) :-
    convertTypesToProlog(X, NX),
    convertTypeToProlog(Y, NY).

convertTypeToProlog(X, X).

convertTypesToProlog(star(X, Y), [NX | NY]) :-
    convertTypeToProlog(X, NX),
    convertTypesToProlog(Y, NY).

convertTypesToProlog(X, [R]) :-
    convertTypeToProlog(X, R).

% test(X, Y, [X | [Y]]).
% not(X) :- bool(X).

% bool(_,true,bool).
% bool(_,false,bool).
% int(X) :- integer(X).

% and(X, Y) :- bool(X), bool(Y).
% or(X,Y) :- bool(X), bool(Y).
% eq(X,Y) :- int(X), int(Y).
% lt(X,Y) :- int(X), int(Y).
% add(X, Y) :- integer(X), integer(Y).
% sub(X, Y) :- integer(X), integer(Y).
% mul(X,int)], ("b",bool), X)., Y) :- integer(X), integer(Y).
% div(X, Y) :- integer(X), integer(Y).


% ================================
% Exemples
% ================================

% ?- typeExpr([("f", [[bool, int, int], bool])], invoc("f", exprs(and(true,false), exprs(add(1,2), div(4,5)))), X).
% X = [bool] .

% ?- typeArgs([], args(arg("name",arrow(star(int, bool), bool)),args(arg("val",int), arg("val3",bool))), X).
% X = [("val3", bool),  ("val", int),  ("name", [[int, bool], bool])] 

% ?- typeExpr([], block(args(arg("a", int), args(arg("b", bool), arg("c", int))), div(2,3)), X).
% X = [[int, bool, int], int]

% ?- typeExprs([], exprs(add(1,2), exprs(and(true, false), exprs(div(4, 2), exprs(if(true, not(false), and(true, false)), lt(2,4))))), X).
% X = [int, bool, int, bool, bool] 

% ?- typeDec([], fun("f", bool, args(arg("a", int), arg("b", int)), eq("a", "b")), T).
% T = [("f", [[int, int], bool])] .

% ?- typeDec([("a", int)], funrec("f", bool, args(arg("a", int), arg("b", int)), invoc("f", exprs("a", "b"))), G).
% G = [("f", [[int, int], bool]),  ("a", int)] .

% ================= ONE LINER =================
% java ToProlog < ../exemples_APS/prog005.aps | awk '{print $1"."}' | swipl -s Typage/prog.pl -g main_stdin
% =============================================

%% EXAMPLE TO USE exprs IN PRIM
% typeExpr(G, not(exprs(X)), bool) :-
%     typeExpr(G, X, bool).
% typeExpr(G, and(exprs(X, Y)), bool) :-
%     typeExpr(G, X, bool),
%     typeExpr(G, Y, bool).