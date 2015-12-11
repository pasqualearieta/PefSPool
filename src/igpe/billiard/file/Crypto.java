package igpe.billiard.file;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;

public class Crypto {

	private static final String ALGORITHM = "AES";
	private static final byte[] KEY_VALUE = new byte[] { 'p', '&', 'f', 's',
			'p', 'o', 'o', 'l', '2', '0', '1', '5', 'f', 'r', 'p', 'a' };

	public static String encrypt(String Data) throws Exception {
		final Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		final byte[] encVal = c.doFinal(Data.getBytes());
		@SuppressWarnings("restriction")
		String encryptedValue = new BASE64Encoder().encode(encVal);
		return encryptedValue;
	}

	public static String decrypt(String encryptedData) throws Exception {
		final Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		@SuppressWarnings("restriction")
		final byte[] decordedValue = new BASE64Decoder()
				.decodeBuffer(encryptedData);
		final byte[] decValue = c.doFinal(decordedValue);
		final String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey() throws Exception {
		final Key key = new SecretKeySpec(KEY_VALUE, ALGORITHM);
		return key;
	}

}
