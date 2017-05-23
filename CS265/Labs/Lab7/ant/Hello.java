public class Hello {

    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("ERROR: no argument provided");
            return;
        }
        System.out.println("Hello " + args[0] + "!");
    }
}
