//set the alarm to radio 
//and make sure that when snooze is pressed the alarm does not change the alarm that was set 
public class AlarmClock extends clock {

	int A_hour, A_min;
	String Alarm_AMPM; 

	public AlarmClock(int h, int m1, int m2, String AM_PM, int h2, int M1, String AM_PM2) {

		
		//setting alarm
		A_hour = h2; 
		A_min = M1; 
		Alarm_AMPM = AM_PM2; 
	}
	public void checkAlarm(){
		
		System.out.println();
		
	}
	public void snooze(){
		int Min = Min1 + Min2 ;
		Min += 9; 
		
		System.out.println("Snooze was pressed");
		
	}
	public void AlarmOff(){
		System.out.println("The alarm was shut off");
	}
	
	public void setRadio(String string , String n) {
		Radio radio = new Radio();  
        radio.setRadio(string, n); 
		// TODO Auto-generated method stub
		
	}
	public String showTime() {
		clock clock = new clock();  
		return clock.showTime(); 
		// TODO Auto-generated method stub
	
	}
	public void tick() {
		clock clock = new clock();  
		clock.tick();
		// TODO Auto-generated method stub
		
	}
	

	
}
