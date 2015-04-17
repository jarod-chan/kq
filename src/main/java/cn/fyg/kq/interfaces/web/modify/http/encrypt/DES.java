package cn.fyg.kq.interfaces.web.modify.http.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DES {
	private static boolean isNeedEncrypt = true;
	public static final String key = "fyg168la";
    private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };

    public static String encryptDES(String encryptString, String encryptKey)
            throws Exception {
    	if (isNeedEncrypt) {
    		  IvParameterSpec zeroIv = new IvParameterSpec(iv);
    	        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
    	        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    	        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
    	        byte[] encryptedData = cipher.doFinal(encryptString.getBytes("UTF-8"));
    	        return Base64.encode(encryptedData);
		}else{
			return encryptString;
		}
    }
    
    public static String decryptDES(String decryptString, String decryptKey)
              throws Exception {
    	if (isNeedEncrypt) {
			byte[] byteMi = Base64.decode(decryptString);
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte decryptedData[] = cipher.doFinal(byteMi);

			return new String(decryptedData,"UTF-8");
		}else{
			return decryptString;
		}
      }
    

}

