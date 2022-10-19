package test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author WongChenHoll
 * @description
 * @date 2022-9-8 星期四 16:17
 **/
public class RegexMatches {


    public static void main(String args[]) {
        String str = "     :     ";
        String pattern = "\\s:\\s";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());

    }
}
