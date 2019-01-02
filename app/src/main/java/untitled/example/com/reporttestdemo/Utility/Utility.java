package untitled.example.com.reporttestdemo.Utility;

import android.content.Context;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import untitled.example.com.reporttestdemo.exception.InvalidAccountException;
import untitled.example.com.reporttestdemo.exception.InvalidReportInputException;

/**
 * Created by Amber_Chen on 2018/10/1.
 */

public class Utility {

    public static boolean verifyUserName(final String name, Context context)
            throws InvalidAccountException {
        if (TextUtils.isEmpty(name)) {
            throw new InvalidAccountException();
        }

        String expression = "^[a-zA-Z0-9-_\\.]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name.trim());
        if (!matcher.matches()) {
            throw new InvalidAccountException();
        }
        if (name.length() < Define.MIN_NAME_LENGTH
                || name.length() > Define.MAX_NAME_LENGTH) {
            throw new InvalidAccountException();
        }
        return true;
    }

    public static boolean verifyEmail(final String email, Context context)
            throws InvalidAccountException {
        if (TextUtils.isEmpty(email)) {
            throw new InvalidAccountException();
        }
        String expression = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email.trim());
        if (!matcher.matches() || email.length() > 50) {
            throw new InvalidAccountException();
        }
        return true;
    }

    public static void verifyPassword(String password, Context context)
            throws InvalidAccountException {
        if (TextUtils.isEmpty(password)) {
            throw new InvalidAccountException();
        }
        String expression = "^[a-zA-Z0-9]*$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new InvalidAccountException();
        }
        if (password.length() < Define.MIN_PWD_LENGTH
                || password.length() > Define.MAX_PWD_LENGTH) {
            throw new InvalidAccountException();
        }
    }

    public static void verifyReportInput(String title,String content, Context context)
            throws InvalidReportInputException {
        if (TextUtils.isEmpty(title)) {
            throw new InvalidReportInputException("title is null");
        }
        if (TextUtils.isEmpty(content)) {
            throw new InvalidReportInputException("content is null");
        }
        if (title.length() > 10
                || title.length() < 1) {
            throw new InvalidReportInputException("title lenght need between 0 to 10");
        }
    }

    /**
     * Get date format is yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     *
     * @param timeStamp milliseconds unit.
     * @return yyyy/MM/dd HH:mm:ss
     */
    public static String getTimeFormat(long timeStamp) {
        String formatDate = "";
        if (timeStamp == 0)
            return formatDate;

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        format.setTimeZone(tz);
        formatDate = format.format(new Date(timeStamp));
        return formatDate;
    }
}
