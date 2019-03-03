public class AstTrue implements IASTExpr {

    public String toPrologString() {
        return "true";
    }


    public Value eval(Environment env){
        return null;
    }
}