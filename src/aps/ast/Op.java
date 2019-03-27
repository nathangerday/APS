package aps.ast;
public enum Op {
    ADD("add"), SUB("sub"), MUL("mul"), DIV("div"), NOT("not"), AND("and"), OR("or"), EQ("eq"), LT("lt"), LEN("len"), ALLOC("alloc"), NTH("nth");
    private String str;

    Op(String str) {
        this.str = str;
    }

    public String toString() {
        return this.str;
    }
}