package twistedgate.bowlerter;

public class Bowlerter{
	static final int MASK_L_B = 0x01;
	static final int MASK_L_O = 0x02;
	static final int MASK_L_W = 0x04;
	static final int MASK_L_L = 0x08;
	
	static final int MASK_H_B = 0x10;
	static final int MASK_H_O = 0x20;
	static final int MASK_H_W = 0x40;
	static final int MASK_H_L = 0x80;
	
	public static String toBowl(String input){
		String out = "";
		
		if(!input.isEmpty()){
			byte[] bytes = input.getBytes();
			
			int j = bytes.length - 1;
			for(int i = 0;i < bytes.length;i++){
				byte b = bytes[i];
				
				String bowl = "";
				
				bowl += (b & MASK_H_B) == 0 ? 'b' : 'B';
				bowl += (b & MASK_H_O) == 0 ? 'o' : 'O';
				bowl += (b & MASK_H_W) == 0 ? 'w' : 'W';
				bowl += (b & MASK_H_L) == 0 ? 'l' : 'L';
				bowl += ' ';
				bowl += (b & MASK_L_B) == 0 ? 'b' : 'B';
				bowl += (b & MASK_L_O) == 0 ? 'o' : 'O';
				bowl += (b & MASK_L_W) == 0 ? 'w' : 'W';
				bowl += (b & MASK_L_L) == 0 ? 'l' : 'L';
				
				if(i < j){
					bowl += ' ';
				}
				
				out += bowl;
			}
		}
		
		return out;
	}
	
	static String bowl = "BOWL";
	
	public static String toString(String bowl){
		String out = "";
		
		if(!bowl.isEmpty()){
			String[] array = bowl.split(" {1,}");
			
			if(array.length / 2 % 2 == 1){
				// Missing a nibble
				System.err.println("Missing a nibble.");
			}
			
			for(int i = 1;i < array.length;i += 2){
				String h = array[i - 1];
				String l = array[i];
				
				out += new String(new byte[]{(byte) (nibble(h, 4) | nibble(l, 0))});
			}
		}
		
		return out;
	}
	
	static int nibble(String str, int shiftL){
		int nibble = 0;
		for(int i = 0;i < 4;i++){
			if(str.charAt(i) == bowl.charAt(i)){
				nibble |= 1 << i;
			}
		}
		return nibble << shiftL;
	}
	
	static String binary(byte b){
		String out = "";
		for(int i = 0;i < Byte.SIZE;i++){
			out += Integer.toString((b & 1 << i) > 0 ? 1 : 0);
		}
		return out;
	}
}
