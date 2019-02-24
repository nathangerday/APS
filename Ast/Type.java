public enum Type {
    INT("int"), BOOL("bool");
    private String str;

    Type(String str) {
        this.str = str;
    }

    public String toString() {
        return this.str;
    }
}