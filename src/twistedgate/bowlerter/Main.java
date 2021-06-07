package twistedgate.bowlerter;

public class Main{
	public static void main(String[] args){
		String input = "Hello World!";
		
		String bowl = Bowlerter.toBowl(input);
		
		String str = Bowlerter.toString(bowl);
		
		System.out.println(input + " -> \"" + bowl + "\"");
		System.out.println("\"" + bowl + "\" -> " + str);
	}
}
