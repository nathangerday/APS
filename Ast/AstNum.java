public class AstNum implements Ast {
    Integer val;

    AstNum(Integer n) {
        val = n;
    }

    @Override
    public String toPrologString() {
        return ("" + val);
    }
}