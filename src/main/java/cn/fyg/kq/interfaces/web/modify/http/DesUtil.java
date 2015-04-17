package cn.fyg.kq.interfaces.web.modify.http;

import cn.fyg.kq.interfaces.web.modify.http.encrypt.DES;

public class DesUtil {

    public static String encryptDES(String encryptString)throws Exception {
    	return DES.encryptDES(encryptString, DES.key);
    }
    
    public static String decryptDES(String decryptString)
            throws Exception {
    	return DES.decryptDES(decryptString, DES.key);
    }

}
