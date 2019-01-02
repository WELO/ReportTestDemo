package untitled.example.com.reporttestdemo.Utility;

import android.content.Context;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import untitled.example.com.reporttestdemo.exception.InvalidReportInputException;


public class Utility {

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
