typeExpr(_, true, bool).
typeExpr(_, false, bool).

typeExpr(_, X, int) :- integer(X).
typeExpr(_, X, ident) :- string(X).

typeExpr(_, not(X), bool) :- 
    typeExpr(_, X, bool).


typeExpr(_, and(X, Y), bool) :-
    typeExpr(_, X, bool),
    typeExpr(_, Y, bool).

typeExpr(_, and(X, Y), bool) :-
    typeExpr(_, X, bool),
    typeExpr(_, Y, bool).

typeExpr(_, or(X, Y), bool) :-
    typeExpr(_, X, bool),
    typeExpr(_, Y, bool).

typeExpr(_, eq(X,Y), bool) :-
    typeExpr(_, X, int),
    typeExpr(_, Y, int).    

typeExpr(_, lt(X, Y), bool) :-
    typeExpr(_, X, int),
    typeExpr(_, Y, int).    

typeExpr(_,  add(X,Y), int) :-
    typeExpr(_, X, int),
    typeExpr(_, Y, int).    

typeExpr(_,  sub(X,Y), int) :-
    typeExpr(_, X, int),
    typeExpr(_, Y, int).    

typeExpr(_,  mul(X,Y), int) :-
    typeExpr(_, X, int),
    typeExpr(_, Y, int).    

typeExpr(_,  div(X,Y), int) :-
    typeExpr(_, X, int),
    typeExpr(_, Y, int).    

typeExpr(_, if(X,Y,Z), T) :-
    typeExpr(_, X, bool),
    typeExpr(_, Y, T),
    typeExpr(_, Z, T).

typeExpr(_, block(_, Y), _) :-
    % //TODO Mettre args dans contexe
    typeExprs(_, Y, _).

typeExpr(_, invoc(X, Y), _) :-
    typeExpr(_, X, ident),
    typeExprs(_, Y, _).

typeExprs(_, exprs(X, Y), _) :-
    typeExpr(_, X, _),
    typeExprs(_, Y, _).

typeExprs(_, exprs(X, Y), _) :-
    typeExpr(_, X,_ ),
    typeExpr(_, Y,_ ).




main_stdin :-
    read(user_input, T),
    typeExpr(_, T, R),
    print(R),
    nl. 








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
% mul(X, Y) :- integer(X), integer(Y).
% div(X, Y) :- integer(X), integer(Y).