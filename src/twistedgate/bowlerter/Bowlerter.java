package twistedgate.bowlerter;

import java.nio.charset.Charset;

public class Bowlerter{
	static final Charset CHARSET = Charset.forName("ASCII");
	
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
	 * @param message
	 * @return bowl
	 */
	public static String toBowl(String message){
		String bowl = "";
		
		if(!message.isEmpty()){
			byte[] bytes = message.getBytes(CHARSET);
			
			int j = bytes.length - 1;
			for(int i = 0;i < bytes.length;i++){
				byte b = bytes[i];
				
				String tmp = "";
				
				tmp += (b & MASK_H_B) == 0 ? 'b' : 'B';
				tmp += (b & MASK_H_O) == 0 ? 'o' : 'O';
				tmp += (b & MASK_H_W) == 0 ? 'w' : 'W';
				tmp += (b & MASK_H_L) == 0 ? 'l' : 'L';
				tmp += ' ';
				tmp += (b & MASK_L_B) == 0 ? 'b' : 'B';
				tmp += (b & MASK_L_O) == 0 ? 'o' : 'O';
				tmp += (b & MASK_L_W) == 0 ? 'w' : 'W';
				tmp += (b & MASK_L_L) == 0 ? 'l' : 'L';
				
				if(i < j){
					tmp += ' ';
				}
				
				bowl += tmp;
			}
		}
		
		return bowl;
	}
	
	/**
	 * Decode bowl
	 * 
	 * @param bowl
	 * @return string
	 */
	public static String toString(String bowl){
		if(!bowl.isEmpty()){
			String[] array = bowl.split(" {1,}");
			
			if(array.length % 2 == 1){
				throw new RuntimeException("Missing or one too many bowl.");
			}
			
			byte[] bytes = new byte[array.length / 2];
			for(int i = 1,j = 0;i < array.length;i += 2,j++){
				String h = array[i - 1];
				String l = array[i];
				
				bytes[j] = (byte) (nibble(h, 4) | nibble(l, 0));
			}
			
			String message = new String(bytes, CHARSET);
			return message;
		}
		
		return "";
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
