interface Ast {
    public String toPrologString();
    public Context eval(Context c);
}