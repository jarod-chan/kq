package cn.fyg.easser.domain.encrypt;


public class DesUtil {

    public static String encryptDES(String encryptString)throws Exception {
    	return DES.encryptDES(encryptString, DES.key);
    }
    
    public static String decryptDES(String decryptString)
            throws Exception {
    	return DES.decryptDES(decryptString, DES.key);
    }

}
