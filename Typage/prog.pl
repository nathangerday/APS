typeExpr(_, true, bool).
typeExpr(_, false, bool).

typeExpr(_, X, int) :- integer(X).
typeExpr(_, X, ident) :- string(X).

typeExpr(G, not(X), bool) :- 
    assoc(X, G, R),
    print(R),
    typeExpr(_, R, bool).

typeExpr(_, not(X), bool) :-
    typeExpr(_, X, bool).

typeExpr(G, and(X, Y), bool) :-
    typeExpr(G, X, bool),
    typeExpr(G, Y, bool).

typeExpr(G, and(X, Y), bool) :-
    typeExpr(G, X, bool),
    typeExpr(G, Y, bool).

typeExpr(G, or(X, Y), bool) :-
    typeExpr(G, X, bool),
    typeExpr(G, Y, bool).

typeExpr(G, eq(X,Y), bool) :-
    typeExpr(G, X, int),
    typeExpr(G, Y, int).    

typeExpr(G, lt(X, Y), bool) :-
    typeExpr(G, X, int),
    typeExpr(G, Y, int).    

typeExpr(G,  add(X,Y), int) :-
    typeExpr(G, X, int),
    typeExpr(G, Y, int).    

typeExpr(G,  sub(X,Y), int) :-
    typeExpr(G, X, int),
    typeExpr(G, Y, int).    

typeExpr(G,  mul(X,Y), int) :-
    typeExpr(G, X, int),
    typeExpr(G, Y, int).    

typeExpr(G,  div(X,Y), int) :-
    typeExpr(G, X, int),
    typeExpr(G, Y, int).    

typeExpr(G, if(X,Y,Z), T) :-
    typeExpr(G, X, bool),
    typeExpr(G, Y, T),
    typeExpr(G, Z, T).

typeExpr(G, block(A, Y), _) :-
    % //TODO Mettre args dans contexe
    typeExprs(G, Y, _).

typeExpr(G, invoc(X, Y), _) :-
    typeExpr(G, X, ident),
    typeExprs(G, Y, _).

typeExprs(G, exprs(X, Y), _) :-
    typeExpr(G, X, _),
    typeExprs(G, Y, _).

typeExprs(G, exprs(X, Y), _) :-
    typeExpr(G, X,_ ),
    typeExpr(G, Y,_ ).


main_stdin :-
    read(user_input, T),
    typeExpr(_, T, R),
    print(R),
    nl. 


assoc(X, [(X, V)|_], V).
assoc(X, [_|XS], V) :-
    assoc(X, XS, V).

add([], (X,Y), (X,Y)).
add([L], (X,Y), [(X,Y) | L]).




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