public class AstNum implements Ast {
    Integer val;

    AstNum(Integer n) {
        val = n;
    }

    @Override
    public String toPrologString() {
        return ("" + val);
    }

    public Integer getVal(){
        return this.val;
    }

    public Context eval(Context c){
        return null;
    }
    
}