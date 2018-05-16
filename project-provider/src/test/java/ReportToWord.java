import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 动态更改word文档
 *
 * @author Eason
 */
public class ReportToWord {

    /**
     * 替换动态文档
     *
     * @param content
     * @param markersign
     * @param replacecontent
     * @return
     */
    public String replaceStr(String content, String markersign,
                             String replacecontent) {
        //String rc = strToRtf(replacecontent);
        String rc = encode2HtmlUnicode(replacecontent);
        String target = "";
        markersign = "$" + markersign + "$";
        target = content.replace(markersign, rc);
        return target;
    }

    /**
     * 把给定的str转换为10进制表示的unicode
     * 目前只是用于mht模板的转码
     *
     * @param str
     * @return
     */
    public String encode2HtmlUnicode(String str) {

        if (str == null) return "";

        StringBuilder sb = new StringBuilder(str.length() * 2);
        for (int i = 0; i < str.length(); i++) {
            sb.append(encode2HtmlUnicode(str.charAt(i)));
        }
        return sb.toString();
    }

    public String encode2HtmlUnicode(char character) {
        if (character > 255) {
            return "&#" + (character & 0xffff) + ";";
        } else {
            return String.valueOf(character);
        }
    }


    /**
     * 读取和输出文件
     *
     * @param inputPath
     * @param outPath
     * @param data
     */
    public void rgModel(String inputPath, String outPath, Map<String, String> data) {
        /* 字节形式读取模板文件内容,将结果转为字符串 */
        String sourname = inputPath;
        String sourcecontent = "";
        InputStream ins = null;
        try {
            ins = new FileInputStream(sourname);
            byte[] b = new byte[1638400];// 提高对文件的读取速度，特别是对于1M以上的文件
            if (ins == null) {
                System.out.println("源模板文件不存在");
            }
            int bytesRead = 0;
            while (true) {
                bytesRead = ins.read(b, 0, 1638400);
                if (bytesRead == -1) {
                    System.out.println("读取模板文件结束");
                    break;
                }
                sourcecontent += new String(b, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* 修改变化部分 */
        String targetcontent = "";
        String oldText = "";
        Object newValue;
        /* 结果输出保存到文件 */
        try {
            Iterator<String> keys = data.keySet().iterator();
            int keysfirst = 0;
            while (keys.hasNext()) {
                oldText = (String) keys.next();
                newValue = data.get(oldText);
                String newText = (String) newValue;
                if (keysfirst == 0) {
                    targetcontent = replaceStr(sourcecontent, oldText, newText);
                    keysfirst = 1;
                } else {
                    targetcontent = replaceStr(targetcontent, oldText, newText);
                    keysfirst = 1;
                }
            }

            FileWriter fw = new FileWriter(outPath, true);
            PrintWriter out = new PrintWriter(fw);
            if (targetcontent.equals("") || targetcontent == "") {
                out.println(sourcecontent);
            } else {
                out.println(targetcontent);
            }
            out.close();
            fw.close();
            System.out.println(outPath + " 生成文件成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


//
//        ReportToWord oRTF = new ReportToWord();
//        // *****************************************
//        // 利用HashMap读取数据库中的数据
//        HashMap<String, String> map = new HashMap<>();
//        map.put("aname", "沈阳市于洪区公用事业管理局");
//        map.put("bname", "Microsoft Windows SMB 远程代码执行漏洞(CVE-2017-0143)(MS17-010)【原理扫描】");
//        map.put("cname", "使用Windows Update将操作系统补丁升级到最新");
//        // ******************************************
//        oRTF.rgModel("D:\\LC.mht", "d:\\docs\\LC-OUT1.doc", map);

    }

    public void fileOut(String aname, String bname, String cname, String ip, String port, String fileName) {

        ReportToWord oRTF = new ReportToWord();
        // *****************************************
        // 利用HashMap读取数据库中的数据
        HashMap<String, String> map = new HashMap<>();
        map.put("aname", aname);
        map.put("bname", bname);
        map.put("suggest", cname);
        map.put("ip", ip);
        map.put("port", port);
        // ******************************************
        oRTF.rgModel("D:\\LC.mht", "d:\\docs\\沈阳移动漏洞通知单-" + fileName + ".doc", map);
    }
}