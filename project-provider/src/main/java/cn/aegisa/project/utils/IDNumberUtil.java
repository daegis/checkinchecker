package cn.aegisa.project.utils;

import java.util.Calendar;

public abstract class IDNumberUtil {

    /**
     * @param no id number to be checked
     * @return true if validated, false if invalid
     */

    public static boolean checkID(String no) {
        if (no == null || no.length() != 18 || !no.matches("\\d{17}[0-9X]")) {
            return false;
        }
        // 1-17位相乘因子数组
        int[] factor = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        // 18位随机码数组
        char[] random = "10X98765432".toCharArray();
        // 计算1-17位与相应因子乘积之和
        int total = 0;
        for (int i = 0; i < 17; i++) {
            total += Character.getNumericValue(no.charAt(i)) * factor[i];
        }
        // 判断随机码是否相等
        return random[total % 11] == no.charAt(17);
    }

    public static String getAgeFromID(String no) {
        if (!checkID(no)) {
            return "- -";
        }
        String dob = no.substring(6, 14);
        String yearString = dob.substring(0, 4);
        String monthString = dob.substring(4, 6);
        String dayString = dob.substring(6, 8);
        int year = Integer.parseInt(yearString);
        int month = Integer.parseInt(monthString);
        int day = Integer.parseInt(dayString);
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);
        int monthNow = calendar.get(Calendar.MONTH) + 1;
        int dayNow = calendar.get(Calendar.DATE);
        int yearMinus = yearNow - year;
        int monthMinus = monthNow - month;
        int dayMinus = dayNow - day;
        int age = yearMinus;
        if (monthMinus < 0) {
            return String.valueOf(age);
        }
        if (monthMinus == 0) {
            if (dayMinus < 0) {
                return String.valueOf(age - 1);
            } else {
                return String.valueOf(age);
            }
        }
        return String.valueOf(age);
    }

    public static String getDOB(String no) {
        if (!checkID(no)) {
            return "- -";
        }
        String dob = no.substring(6, 14);
        String yearString = dob.substring(0, 4);
        String monthString = dob.substring(4, 6);
        String dayString = dob.substring(6, 8);
        int year = Integer.parseInt(yearString);
        int month = Integer.parseInt(monthString);
        int day = Integer.parseInt(dayString);
        return String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day);
    }

    public static String getGender(String no) {
        if (!checkID(no)) {
            return "- -";
        }
        char c = no.charAt(16);
        int gender = c - '0';
        if (gender % 2 == 0) {
            return "女";
        }
        return "男";
    }
}
