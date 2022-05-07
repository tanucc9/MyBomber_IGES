package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashTool {
	private MessageDigest digest;

	public HashTool() throws NoSuchAlgorithmException {
		digest = MessageDigest.getInstance("SHA-256");
	}

	public String hashSHA256(String string) {
		byte[] encodedHash = digest.digest(string.getBytes(StandardCharsets.UTF_8));
		return bytesToHex(encodedHash);
	}

	private String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (byte b : hash) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
