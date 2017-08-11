package com.xrizq.xwordfileextractor;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mrhezqhe@gmail.com
 */
public class Main {

    public static void main(String[] args) {

        try {

            String outputPath = "D:\\kbbi.txt";
            //remove duplicate and ascending order
            TreeSet<String> tree = new TreeSet<String>();

            for(int i = 1; i <= 10; i++){

                //split into smaller file
                String inputPath = "D:\\sharetemp\\kbbi-"+i+".docx";
                System.out.println("Processing file : "+inputPath);

                FileWordReader reader = new FileWordReader(inputPath);
                List<XWPFParagraph> paragraphs = reader.readDocxFile();

                Pattern p = Pattern.compile("[^a-zA-Z\\d'-]|-.*-|^-|-$");
                for (XWPFParagraph paragraph : paragraphs) {
                    int size = paragraph.getRuns().size();
                    for (XWPFRun run : paragraph.getRuns()) {
                        String text = run.text();
                        if(run.isBold()){
                            //clean up unwanted characters
                            text = text.replaceAll("[0-9]","");
                            text = text.replaceAll("-- ","");
                            text = text.replaceAll("--","");
                            text = text.replaceAll("~","");
                            text = text.replaceAll("; --","");
                            text = text.replaceAll("- ","");
                            text = text.replaceAll(";","");
                            text = text.replaceAll("/","");
                            text = text.replaceFirst("-","");
                            text = text.replaceFirst("`","");
                            text = text.replaceFirst("–","");
                            text = text.replaceFirst("—","");
                            text = text.replaceFirst(",","");
                            text = text.replaceFirst(" ","");

                            if(text.trim().length() > 0){
                                Matcher m = p.matcher(text);
                                if (!m.matches()){
                                    tree.add(text.toLowerCase());
                                }
                            }
                        }
                    }
                }
            }

            //write file
            Iterator<String> iterator = tree.iterator();
            System.out.print("Writing to file...");
            while (iterator.hasNext()) {
                writeToTxt(iterator.next(), outputPath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static boolean writeToTxt(String data, String filePath){
        boolean writeNow = false;
        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writeNow;
    }

}
