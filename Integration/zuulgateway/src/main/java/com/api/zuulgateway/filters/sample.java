package com.api.zuulgateway.filters;
import org.apache.commons.codec.binary.Base64;
public class sample {
	 
	    public static void main(String[] args) {
	 
	        final String username = "Aladdin";
	        final String password = "open sesame";
	 
	        System.out.println("Input\t: username [" + username + "]," +
	                " password [" + password + "]");
	 
	        final String encodedText = createEncodedText(username, password);
	        System.out.println("Encoded Text : " + encodedText);
	 
	        final String[] userDetails = decode(encodedText);
	        System.out.println("Decoded\t: username [" + userDetails[0] + 
	                "], password [" +  userDetails[1] + "]");
	 
	    }
	 
	    private static String[] decode(final String encoded) {
	        final byte[] decodedBytes 
	                = Base64.decodeBase64(encoded.getBytes());
	        final String pair = new String(decodedBytes);
	        final String[] userDetails = pair.split(":", 2);
	        return userDetails;
	    }
	 
	    private static String createEncodedText(final String username, 
	                                            final String password) {
	        final String pair = username + ":" + password;
	        final byte[] encodedBytes = Base64.encodeBase64(pair.getBytes());
	        return new String(encodedBytes);
	    }

}
