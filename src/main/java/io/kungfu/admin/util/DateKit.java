package io.kungfu.admin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateKit {
	
	/**
     * 计算指定日期之前或者之后的多少分钟
     * @param date
     * @param minute
     * @return
     */
    public static String getTimeByMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }

    /**
     * 获取当前时间(14位)
     *
     * @return
     */
    public static String getCurrentTime() {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(now);
    }


    public static int getCurrent(int type) {

        Calendar calendar = Calendar.getInstance();

        switch (type) {
            case Calendar.YEAR :
                return calendar.get(Calendar.YEAR);
            case Calendar.MONTH:
                return calendar.get(Calendar.MONTH) + 1;
            case Calendar.DATE:
                return calendar.get(Calendar.DATE);
        }

        return 0;
    }


    public static void main(String[] args) {
        int year = DateKit.getCurrent(Calendar.YEAR);
        int month = DateKit.getCurrent(Calendar.MONTH);

        System.out.println(year);
        System.out.println(month);
    }
}
