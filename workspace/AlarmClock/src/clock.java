
public class clock {

	int hours, min1, min2; 
	int seconds = 0; 
	String AMPM;
	String time = hours + ":" + min1 + min2 + AMPM;
	public clock() {
		// TODO Auto-generated constructor stub
	}
	
	public void setTime(int h, int m1, int m2, String AMPM2){
		hours = h; 
		min1 = m1; 
		min2 = m2; 
		AMPM = AMPM2;
	}
	public String showTime(){
		

		time = hours + ":" + min1 + min2 + AMPM;
		return time;
		/*
		if(min2 < 10 ){
			System.out.println(hours + ":" + min1 + min2 + AMPM);
			 time = hours + ":" + min1 + min2 + AMPM;
		}
		else{
			System.out.println(hours + ":" + min2 + AMPM);
			 time = hours + ":" + min1 + min2 + AMPM;
		}*/
	
	}
	public void tick(){
		
		if(seconds < 60)
			seconds++; 
		else if ( seconds >= 60) { 
			seconds=0;
			min2++;
			if(min2 < 10 ){
				System.out.println(hours + ":" + min1 + min2 + AMPM);
				 time = hours + ":" + min1 + min2 + AMPM;
			}
			else{
				System.out.println(hours + ":" + min2 + AMPM);
				 time = hours + ":" + min1 + min2 + AMPM;
			}
			//System.out.println(hours + ":" + min1 + min2 + AMPM);
		}
	 
			
		
		
	}

	
}
