package test.offices;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author WongChenHoll
 * @description Office——Word操作
 * @date 2024-1-3 星期三 11:45
 **/
public class WordUtil {

    public static void copyDocx(File source, File target) throws IOException, OpenXML4JException, XmlException {

        FileInputStream inputStream = new FileInputStream(source);
        XWPFWordExtractor extractor = new XWPFWordExtractor(OPCPackage.open(inputStream));
        System.out.println(extractor.getText());
        extractor.close();
        inputStream.close();
    }
    public static void copyDoc(File source, File target) throws IOException {

        FileInputStream inputStream = new FileInputStream(source);
        XWPFDocument doc = new XWPFDocument(inputStream);
        List<XWPFParagraph> list = doc.getParagraphs();
        for (XWPFParagraph paragraph : list) {
            System.out.println("段落内容：" + paragraph.getText());
            List<XWPFRun> runs = paragraph.getRuns();
            for (XWPFRun run : runs) {
            }
        }
        doc.close();
        inputStream.close();
    }

    public static void main(String[] args) throws IOException {
        copyDoc(new File("C:\\Users\\WangChenHol\\Desktop\\全职教师薪资体系.docx"), new File("C:\\Users\\WangChenHol\\Desktop\\copy.docx"));
    }
}
