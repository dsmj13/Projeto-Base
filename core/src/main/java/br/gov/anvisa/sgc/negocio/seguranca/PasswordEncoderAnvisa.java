package br.gov.anvisa.sgc.negocio.seguranca;

public abstract class PasswordEncoderAnvisa {
	private PasswordEncoderAnvisa(){}
	
	private static final String FKEY = "!@#$%^&*()";
	
	public static String encode(String password){
		String result = reverse(password.toUpperCase());
		result = binary(result);
		result = textToHex(result);
		return "Y" + result.toUpperCase();
	}

	private static String reverse(String text){
		return (new StringBuilder(text)).reverse().toString();
	}
	
	private static String binary(String text){
		StringBuilder result = new StringBuilder();
		for(int i = 1; i <= text.length(); i++){
			int bKey = FKEY.charAt(i % 10) + i;
			result.append((char)(text.charAt(i - 1) ^ bKey));
		}
		return result.toString();
	}
	
	private static String textToHex(String text){
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < text.length(); i++){
			String s = Integer.toHexString(text.charAt(i));
			if(s.length() == 1){
				s = "0" + s;
			}
			result.append(s);
		}
		return result.toString();
	}
	
	//Y7417161A227F78667C6A6A
	public static void main(String[] args) {
		
		System.out.println(encode("Anvisa3125"));
		System.out.println(encode("123456"));
	}
}