public class Main {
    public static void main(String[] args) {
        if(args == null || args.length != 2){
            throw new IllegalArgumentException("Args don't have correct length");
        }
        System.out.println("");
    }
    // args: Reductions to perform (1,2,3,4) filepath to input, filepath to output?
}
