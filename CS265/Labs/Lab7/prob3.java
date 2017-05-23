public class prob3
{
	public static void main(  String[] args )
	{
		 String s = args[0];
		 int i = Integer.parseInt( s );
		 if( ( i % 4 ) == 0 || ( i % 100 ) ==0 || ( i % 400 ) == 0)
		 {
			 System.out.print("leap year!");
		 }
		 else
		 {
			 System.out.print("not leap year!");
		 }
	}
}