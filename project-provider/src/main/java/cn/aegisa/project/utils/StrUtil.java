package cn.aegisa.project.utils;

/**
 * Using IntelliJ IDEA.
 *
 * @author HNAyd.xian
 * @date 2018/2/7 16:47
 */
public abstract class StrUtil {
    public static boolean strCheckNotNull(String... strings) {
        for (String string : strings) {
            if (string == null || string.trim().equals("")) {
                return false;
            }
        }
        return true;
    }
}
