import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Using IntelliJ IDEA.
 *
 * @author XIANYINGDA at 2018/5/16 15:17
 */
public class Testqwdqwd {

    public static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream(new File("d:\\bbb.xls"));
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook workbook = new HSSFWorkbook(fs);
        HSSFSheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        ReportToWord reportToWord = new ReportToWord();
        List<String> list = new LinkedList<>();
        for (int i = 1; i <= rowNum; i++) {
            HSSFRow row = sheet.getRow(i);
            String aaaa = row.getCell(0).getStringCellValue();
            String bbbb = row.getCell(3).getStringCellValue();
            String cccc = row.getCell(4).getStringCellValue();
            String ip = row.getCell(1).getStringCellValue();
            String port = "" + new Double(row.getCell(2).getNumericCellValue()).intValue();
            String fileName = aaaa;
            if (list.contains(aaaa)) {
                Integer integer = map.get(aaaa);
                if (integer == null) {
                    map.put(aaaa, 1);
                    integer = 1;
                } else {
                    integer++;
                    map.put(aaaa, integer);
                }
                fileName = fileName + String.valueOf(integer);
            } else {
                list.add(aaaa);
            }
            reportToWord.fileOut(aaaa, bbbb, cccc, ip, port, fileName);
        }
    }
}
