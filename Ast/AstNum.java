public class AstNum implements IASTExpr {
    Integer val;

    AstNum(Integer n) {
        val = n;
    }

    @Override
    public String toPrologString() {
        return ("" + val);
    }


    public Value eval(Environment env){
        return null;
    }
    
}