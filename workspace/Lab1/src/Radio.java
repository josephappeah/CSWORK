
public class Radio {
	
	String volume; 
	
	public Radio(){
			
	}
	public void setRadio(String string , String n) {
		
		volume = n;
		System.out.println("The radio was turned on and is playing" + string + "Volume set to:" + n );
	}
	
}
