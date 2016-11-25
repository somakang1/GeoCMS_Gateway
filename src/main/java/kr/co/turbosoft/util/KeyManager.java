package kr.co.turbosoft.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

/*
 * id�� �����ϴ� session token�� �����ϴ� �������� AES �˰����� ������
 * �α��� �� session token�� �����ϰ� ����ð��� �α��� �ð� + 1�ð����� ������
 * ����ð� ���� session token���� api�� ��û�� ��� ���� �ð��� ���� (api ��û�ð� + 1�ð�)
 * ����� ���� session token���� ��û�� ��� error code�� �����ϸ� �� �α��� �� ���ο� session token�� ������  
 */
public class KeyManager {
	public String genKey(String id) throws Exception {
		//generate 128 bit secret key
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128);
		SecretKey skey = kgen.generateKey();
		
		//encode
		SecretKeySpec skeySpec = new SecretKeySpec(skey.getEncoded(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(id.getBytes());
		String encString = Hex.encodeHexString(encrypted);
		System.out.println(encString);
		return encString;
	}
}
