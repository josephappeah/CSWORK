
public class main {

	public static void main(String[] args) {
	    int i;

	    int seconds; 

	    AlarmClockRadio myClock = new AlarmClockRadio(8, 0, 0, "AM", 8, 5, "AM");
	    
	    AlarmClockRadio.Radio.onRadio();
	    AlarmClockRadio.Radio.setRadio("1060 AM");
	    AlarmClockRadio.Radio.setVolume("10");
	    System.out.println("Initial Time: " + myClock.showTime());
	    
	    for ( i = 0; i <= 5; i++)

	      {

	        for ( seconds = 0; seconds < 60; seconds++)

	        {

	         myClock.checkAlarm();

	         myClock.tick();

	        }

	      }
 
	    myClock.snooze();


	    for (i = 0; i < 9; i++)

	    {

	      for ( seconds = 0; seconds < 60; seconds++)

	      {

	        myClock.checkAlarm();

	        myClock.tick();

	      }

	    }


	   myClock.AlarmOff();

	  }

	}


