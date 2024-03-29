package aps.ast;
public enum Type {
    INT("int"), BOOL("bool"), VOID("void"), VEC("vec");
    private String str;

    Type(String str) {
        this.str = str;
    }

    public String toString() {
        return this.str;
    }
}