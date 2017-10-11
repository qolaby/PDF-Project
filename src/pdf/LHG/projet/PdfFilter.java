/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf.LHG.projet;
import java.io.File;

import javax.swing.filechooser.FileFilter;


public class PdfFilter extends FileFilter {

    
	public static final PdfFilter INSTANCE = new PdfFilter();
	
    public boolean accept(File f) {
		if (f.isDirectory()) return true;
        return f.getName().toLowerCase().endsWith(".pdf");
    }

    public String getDescription() {
		return "*.pdf PDF files";
	}

}