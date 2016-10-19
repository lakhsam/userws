package me.ahmed.projects.jersey.utils;

import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;
import java.util.Properties;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HmacSignature {

	@Value("${hach.otp.algo}")
	private String algo;
	@Value("${hach.otp.key}")
	private String key;

	private String toHexString(byte[] bytes) {
		Formatter formatter = new Formatter();
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}

	public String calculateHMAC(String data) throws SignatureException,
			NoSuchAlgorithmException, InvalidKeyException {
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), algo);
		Mac mac = Mac.getInstance(algo);
		mac.init(signingKey);
		return toHexString(mac.doFinal(data.getBytes()));
	}

	public String getPropertyValue(String key) {
		Properties defaultProps = new Properties();
		InputStream in;
		String value = "";
		try {
			in = getClass().getClassLoader().getResourceAsStream(
					"params.properties");
			defaultProps.load(in);
			value = defaultProps.getProperty(key);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	// public static String calculateHash(String string) throws
	// InvalidKeyException, SignatureException, NoSuchAlgorithmException
	// {
	// String result = calculateHMAC("data") ;
	// System.out.println(result);
	// ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
	// String hashOutput = encoder.encodePassword(string,null);
	// return hashOutput;
	// }
}