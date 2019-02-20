typeExpr(_, true, bool).
typeExpr(_, false, bool).

typeExpr(_, X, int) :- integer(X).
typeExpr(G, X, T) :- assoc(X, G, T).

%% EXAMPLE TO USE exprs IN PRIM
% typeExpr(G, not(exprs(X)), bool) :-
%     typeExpr(G, X, bool).

% typeExpr(G, and(exprs(X, Y)), bool) :-
%     typeExpr(G, X, bool),
%     typeExpr(G, Y, bool).


typeExpr(G, not(X), bool) :-
    typeExpr(G, X, bool).

typeExpr(G, and(X, Y), bool) :-
    typeExpr(G, X, bool),
    typeExpr(G, Y, bool).

typeExpr(G, or(X, Y), bool) :-
    typeExpr(G, X, bool),
    typeExpr(G, Y, bool).

typeExpr(G, eq(X, Y), bool) :-
    typeExpr(G, X, int),
    typeExpr(G, Y, int).    

typeExpr(G, lt(X, Y), bool) :-
    typeExpr(G, X, int),
    typeExpr(G, Y, int).    

typeExpr(G, add(X, Y), int) :-
    typeExpr(G, X, int),
    typeExpr(G, Y, int).    

typeExpr(G, sub(X, Y), int) :-
    typeExpr(G, X, int),
    typeExpr(G, Y, int).    

typeExpr(G, mul(X, Y), int) :-
    typeExpr(G, X, int),
    typeExpr(G, Y, int).    

typeExpr(G, div(X,Y), int) :-
    typeExpr(G, X, int),
    typeExpr(G, Y, int).    

typeExpr(G, if(X, Y, Z), T) :-
    typeExpr(G, X, bool),
    typeExpr(G, Y, T),
    typeExpr(G, Z, T).


% //TODO Uniquement le contexte defini dans [...]e ou aussi le contexte general ?
% //TODO Comment retourner le type "t1 * . . . * tn -> t" en Prolog ?
typeExpr(G, block(A, Y), _) :-
    typeArgs(G, A, NG),
    typeExprs(NG, Y, _).

typeExpr(G, invoc(X, Y), R) :-
    typeExprs(G, Y, [T | R]),
    typeExpr(G, X, T).

typeExprs(G, exprs(X, Y), _) :-
    typeExpr(G, X, _),
    typeExprs(G, Y, _).

typeExprs(G, exprs(X, Y), _) :-
    typeExpr(G, X,_ ),
    typeExpr(G, Y,_ ).

typeArgs(G, args(A, B), NNG) :-
    typeArg(G, A, NG),
    typeArg(NG, B, NNG).

typeArgs(G, args(A, As), NNG):-
    typeArg(G, A, NG),
    typeArgs(NG, As, NNG).

typeArg(G, arg(X, Y), NG) :-
    typeExpr(_, X, ident),
    typeType(Y),
    add(G,  (X, Y), NG).

typeType(X) :-
    X = bool.

typeType(X) :-
    X = int.



main_stdin :-
    read(user_input, T),
    typeExpr(_, T, R),
    print(R),
    nl. 


assoc(X, [(X, V)|_], V).
assoc(X, [_|XS], V) :-
    assoc(X, XS, V).

add([], (X,Y), [(X,Y)]).
add([L], (X,Y), [(X,Y) | L]).


 last(X, [X]).
 last(X, [_|Z]) :-
    last(X, Z).



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