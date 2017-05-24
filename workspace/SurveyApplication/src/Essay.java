import java.util.Scanner;

public class Essay extends Question implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Essay(){
		
	}
	/*Get the Prompt from the user for the Essay*/
	public void getPrompt() {
		
		System.out.println("Enter the prompt or your Essay question:");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String i = sc.nextLine();
		SetPrompt(i +"\n");
	}
	/*Set the question for the essay question*/
	public void setQuestion() {
		getPrompt(); 
	}
	
	/*Display out to the console*/
	public void display(Output O) {
		O.display(prompt);
		O.display("\n");
	}
	public void Modify() {
		//check to see if it catches errors 
		System.out.println(this.prompt);
		String prompt = null;
		
		System.out.println("Do you wish to modify the prompt?");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		
		String prompt1 = sc.nextLine();
		if(prompt1.toLowerCase().equals("yes")){
			System.out.println("Enter a new Prompt");
			@SuppressWarnings("resource")
			Scanner sc1 = new Scanner(System.in);
			prompt = sc1.nextLine();
			this.prompt = prompt;
		}
	}
}
