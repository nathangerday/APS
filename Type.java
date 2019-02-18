public enum Type {
    INT("Int"), BOOL("Bool");
    private String str;

    Type(String str) {
        this.str = str;
    }

    public String toString() {
        return this.str;
    }
}