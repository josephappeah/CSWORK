
public class radio {
	
	String radio,volume;
	
	public radio(){
		
	}
	public void setRadio(String string){
		radio = string;
		System.out.println("The radio was turned on and is playing "+ string);
	}
	public String getRadio(){
		return radio;
	}
	public void setVolume(String string){
		volume = string;
		System.out.println("The radio volume is set to "+ string);
	}
	public void onRadio(){
		System.out.println("Radio on");
	}
	public void offRadio(){
		System.out.println("Radio off");
	}
}
