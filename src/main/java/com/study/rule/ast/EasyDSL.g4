grammar EasyDSL;

/** PARSER */
line : expr EOF ;
expr
    : '(' expr ')'          # parenExpr
    | expr '&&' expr          # andEpr
    | expr '||' expr          # orEpr
    | ID                    # identifier
;

/** LEXER */
WS : [ \t\n\r]+ -> skip ;
ID : DIGIT+ ;
fragment DIGIT : '0'..'9';