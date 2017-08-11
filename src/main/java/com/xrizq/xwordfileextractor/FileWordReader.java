package com.xrizq.xwordfileextractor;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * @author mrhezqhe@gmail.com
 */
public class FileWordReader {

    private String fileName;

    public FileWordReader(String fileName){
        this.fileName = fileName;
    }

    public void readDocFile() {

        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            HWPFDocument doc = new HWPFDocument(fis);

            WordExtractor we = new WordExtractor(doc);

            String[] paragraphs = we.getParagraphText();

            System.out.println("Total no of paragraph "+paragraphs.length);
//            for (String para : paragraphs) {
//                System.out.println(para.toString());
//            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<XWPFParagraph> readDocxFile() {
        List<XWPFParagraph> paragraphs = null;
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            XWPFDocument document = new XWPFDocument(fis);

            paragraphs = document.getParagraphs();

            System.out.println("Total no of paragraph "+paragraphs.size());
//            for (XWPFParagraph para : paragraphs) {
//                System.out.println(para.getText());
//            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paragraphs;
    }

}
