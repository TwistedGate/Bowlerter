package twistedgate.bowlerter;

public class Main{
	public static void main(String[] args){
		if(args.length <= 1){
			System.out.println("Help Message");
			System.out.println("bowlerter -b Message");
			System.out.println("bowlerter -m Bowl");
			return;
		}
		
		switch(args[0]){
			case "-b":{
				String message = "";
				int j = args.length - 1;
				for(int i = 1;i < args.length;i++){
					message += args[i];
					if(i < j)
						message += " ";
				}
				
				System.out.println(Bowlerter.toBowl(message));
				return;
			}
			case "-m":{
				String bowl = "";
				int j = args.length - 1;
				for(int i = 1;i < args.length;i++){
					bowl += args[i];
					if(i < j)
						bowl += " ";
				}
				
				System.out.println(Bowlerter.toString(bowl));
				return;
			}
		}
		
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
