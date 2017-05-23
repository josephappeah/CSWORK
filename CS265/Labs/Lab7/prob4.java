import java.util.Calendar;

public class prob4
{
	public static void main(  String[] args )
	{
		 String s = args[0];
		 int i = Integer.parseInt( s );
		 Calendar cal = Calendar.getInstance();
		 
		switch ( i )
		{
			case 0:
				System.out.println("Milliseconds since January 1, 1970: " + cal.getTimeInMillis());
				break;
			case 1:
				System.out.println("Seconds since January 1, 1970: " + cal.getTimeInMillis()/1000);
				break;
			case 2:
				System.out.println("Days since January 1, 1970: " + (cal.getTimeInMillis()/1000)/86400);
				break;
			case 3:
				System.out.println("Current Date: " + cal.getTime());
				break;
			default:
				System.out.println("Current Date: " + cal.getTime());
		}
		
	}
}