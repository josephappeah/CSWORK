
public class AlarmClockRadio extends AlarmClock {

	 static radio Radio = new radio();
	
	public AlarmClockRadio(int h, int m1, int m2, String AM_PM, int h2, int M1, String AM_PM2) {
		super(h, m1, m2, AM_PM, h2, M1, AM_PM2);
		// TODO Auto-generated constructor stub
	}
	public void checkAlarm(){
		String test = A_hour + ":" + 0 + A_min + AMPM;
		if (showTime().equals(test) & seconds==0)
			System.out.println("Buzz Buzz Buzz");
			//System.out.println("PLaying " + Radio.getRadio());
		
	}

}
