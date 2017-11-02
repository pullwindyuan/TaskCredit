package cn.cvte.util;

import org.springframework.util.DigestUtils;

public class VarifyUtil {

    private static final String KEY ;

    static {
        KEY = "qwer#$%wqet@52345" + System.currentTimeMillis();
    }

    public static String getMD5(String msg){
        String base = msg + KEY;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
