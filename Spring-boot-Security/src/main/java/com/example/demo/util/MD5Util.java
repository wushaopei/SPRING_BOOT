
package com.example.demo.util;

import java.security.MessageDigest;

public class MD5Util {
	
	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5","6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	private static final char[] hexChar = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

	public static String byteArrayToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; ++i) {
			sb.append(byteToHexString(bytes[i]));
		}
		return sb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String str,Object encoder) {
		String rsStr = null;
		try {
			rsStr = new String(str+encoder);
			MessageDigest md = MessageDigest.getInstance("MD5");
			rsStr = byteArrayToHexString(md.digest(rsStr.getBytes()));
		} catch (Exception e) {
		}
		return rsStr;
	}

	public static  boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		String pass1 = "" + encPass;
		String pass2 = MD5Encode(rawPass, salt);

		return pass1.equals(pass2);
	}

	protected static String toHexString(byte[] b){
        StringBuilder sb = new StringBuilder(b.length*2);
        for(int i=0;i<b.length;i++){
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }
}