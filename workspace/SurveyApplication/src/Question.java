

public class Question implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String prompt;
	protected Input I = new Input(); 
	protected Output O = new Output();
	
	public void SetPrompt(String Prompt ) {
		this.prompt = Prompt; 
	}
	public void getPrompt() {

	}
	public  void setQuestion() {
		
	}
	public void display(Output O){
		
	}
	public void dispalyPrompt(){
			System.out.println(this.prompt);
	}
	public void Modify(){
		
	}
 
}
