package LibKeyword;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {

    //LibUtilz
    public static String generateRandomString(int len){
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder builder = new StringBuilder();
        Random rd = new Random();
        for (int x=0; x<len; x++){
            builder.append(alpha.charAt(rd.nextInt(alpha.length())));
        }
        return String.valueOf(builder);
    }
    public static String generateRandomNum(int len){
        StringBuilder builder = new StringBuilder();
        Random rd = new Random();
        for (int i = 1; i<=len; i++){
            int n = rd.nextInt(9);
            builder.append(n);
        }
        return String.valueOf(builder);
    }
    public static String generateDateTime(String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date();
        return format.format(date);
    }
}
