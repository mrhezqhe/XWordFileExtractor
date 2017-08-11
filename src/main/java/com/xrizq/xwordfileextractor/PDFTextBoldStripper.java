package com.xrizq.xwordfileextractor;

import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.IOException;

/**
 * @author mrhezqhe@gmail.com
 */
public class PDFTextBoldStripper extends PDFTextStripper {

    public TextPosition txt = null;

    public PDFTextBoldStripper() throws IOException {
        super();
    }

    protected void processTextPosition(TextPosition text) {
        String ff = text.getFont().getFontDescriptor().getFontFamily();
        String postscriptName = text.getFont().getName();
        if ("TimesNewRomanPS-BoldMT".equalsIgnoreCase(postscriptName)){
//            System.out.println("postscriptName : "+postscriptName);
            txt = text;
        }

    }


}
