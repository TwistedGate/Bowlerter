package twistedgate.bowlerter;

public class Bowlerter{
	static String bowl = "BOWL";
	
	static final int MASK_L_B = 0x01;
	static final int MASK_L_O = 0x02;
	static final int MASK_L_W = 0x04;
	static final int MASK_L_L = 0x08;
	
	static final int MASK_H_B = 0x10;
	static final int MASK_H_O = 0x20;
	static final int MASK_H_W = 0x40;
	static final int MASK_H_L = 0x80;
	
	/**
	 * Encode bowl.
	 * 
	 * @param string
	 * @return bowl
	 */
	public static String toBowl(String string){
		String out = "";
		
		if(!string.isEmpty()){
			byte[] bytes = string.getBytes();
			
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
	
	/**
	 * Decode bowl
	 * 
	 * @param bowl
	 * @return string
	 */
	public static String toString(String bowl){
		String out = "";
		
		if(!bowl.isEmpty()){
			String[] array = bowl.split(" {1,}");
			
			if(array.length / 2 % 2 == 1){
				System.err.println("Missing a nibble.");
			}
			
			for(int i = 1;i < array.length;i += 2){
				String h = array[i - 1];
				String l = array[i];
				
				out += String.valueOf((char) (nibble(h, 4) | nibble(l, 0)));
			}
		}
		
		return out;
	}
	
	/**
	 * Converts a single "bowl" back into a 4-Bit Nibble
	 * 
	 * @param str bowl
	 * @param shiftL
	 * @return 4-Bit Nibble
	 */
	static int nibble(String str, int shiftL){
		int nibble = 0;
		for(int i = 0;i < 4;i++){
			if(str.charAt(i) == bowl.charAt(i)){
				nibble |= 1 << i;
			}
		}
		return nibble << shiftL;
	}
}
