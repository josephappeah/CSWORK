
public class AlarmClock extends clock {
	
	int A_hour, A_min;
	int temp;
	String Alarm_AMPM; 
	int timer; 

	public AlarmClock(int h, int m1, int m2, String AM_PM, int h2, int M1, String AM_PM2) {

		setTime(h,m1,m2, AM_PM);
		//setting alarm
		A_hour = h2; 
		A_min = M1; 
		Alarm_AMPM = AM_PM2;
		
	}
	public void checkAlarm(){
		String test = A_hour + ":" + 0 + A_min + AMPM;
		if (showTime().equals(test) & seconds==0)
			System.out.println("Buzz Buzz Buzz");
	}
	public void AlarmOff(){
		A_min = temp;
		System.out.println("The alarm was shut off");
	}
	public void snooze(){
		temp = A_min;
		A_min +=9; 
		System.out.println("Snooze was pressed");
		
	}
	

}
