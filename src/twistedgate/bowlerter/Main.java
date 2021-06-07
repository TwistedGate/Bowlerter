package twistedgate.bowlerter;

public class Main{
	public static void main(String[] args){
		// Sanity Check
		{
			String input = "Hello World!";
			
			String bowl = Bowlerter.toBowl(input);
			System.out.println(input + " -> \"" + bowl + "\"");
			
			String str = Bowlerter.toString(bowl);
			System.out.println("\"" + bowl + "\" -> " + str);
		}
		
		System.out.println();
		
		// Whatever
		{
			String input = "Bowl";
			System.out.println(Bowlerter.toBowl(input));
			
			String bowl = "boWl bOwl bOWl BOWL BOWl BOWl bOWl boWL";
			System.out.println(Bowlerter.toString(bowl));
		}
	}
}
