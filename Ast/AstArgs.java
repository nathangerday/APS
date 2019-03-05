import java.util.ArrayList;


public class AstArgs implements Ast {
    // ArrayList<Ast> args;

    // AstArgs(Ast arg) {
    //     this.args = new ArrayList<>();
    //     args.add(arg);
    // }

    // AstArgs(Ast arg, Ast args){
    //     this.args = new ArrayList<>();
    //     this.args.add(arg);
    //     if(args instanceof AstArgs){
    //         this.args.addAll(((AstArgs)args).args);
    //     }
    // }

    // public String toPrologString() {
    //     String res = "";
    //     res += "args(";
    //     for(int i=0; i<args.size(); i++){
    //         res+=args.get(i).toPrologString();
    //         if(i < args.size() - 1){
    //             res+=",";
    //         }
    //     }
    //     res+=")";
    //     return res;
    // }


    AstArg arg;
    AstArgs args;

    AstArgs(AstArg arg, AstArgs args) {
        this.arg = arg;
        this.args = args;
    }

    AstArgs(AstArg arg) {
        this.arg = arg;
        this.args = null;
    }

    public ArrayList<String> getAll(){
        ArrayList<String> res = new ArrayList<>();
        res.add(arg.getName());
        if(args != null){
            res.addAll(args.getAll());
        }
        return res;
    }

    public String toPrologString() {
        if(this.args != null){
            return "args("+arg.toPrologString()+","+ args.toPrologString()+")";
        }else{
            return "args("+arg.toPrologString()+",args())";
        }
    }
}