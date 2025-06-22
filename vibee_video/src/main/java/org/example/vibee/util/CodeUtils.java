package com.neuedu.xinxin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CodeUtils {
    public static String randomNum(){
        //随机编号
        StringBuffer strBuf=new StringBuffer();
        StringBuffer str=new StringBuffer();
        long time=new Date().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
        strBuf.append(sdf.format(time));
        for(int i=1;i<=4;i++){
            str.append(Math.round(Math.random()*10));
        }
        strBuf.append(str);
        return strBuf.toString();
    }
}
