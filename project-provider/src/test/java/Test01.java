import com.Ostermiller.util.MD5;
import org.junit.Test;

/**
 * Using IntelliJ IDEA.
 *
 * @author HNAyd.xian at 2018/3/30 15:22
 */
public class Test01 {

    @Test
    public void test01() {
        String hashString = MD5.getHashString(MD5.getHashString("cool"));
        System.out.println(hashString);
    }
}
