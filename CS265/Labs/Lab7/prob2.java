public class prob2
{
	public static void main(  String[] args )
	{
		 String s = args[0];
		 int i = Integer.parseInt( s );
		 if( ( i % 2 ) == 0)
		 {
			 System.out.print("even");
		 }
		 else
		 {
			 System.out.print("odd");
		 }
	}
}