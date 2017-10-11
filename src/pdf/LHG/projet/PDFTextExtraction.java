/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf.LHG.projet;

/**
 *
 * @author Aizn
 */
import java.io.BufferedWriter;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.graphics.text.LineText;
import org.icepdf.core.pobjects.graphics.text.PageText;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
  	
  	public class PDFTextExtraction {
  	    public PDFTextExtraction(File f) {

  	        /* Recuperer le Fichier ouvert */
  	        String filePath = f.getAbsolutePath();
  	
  	        // read/store the font cache.
  	        //FontPropertiesManager.getInstance().loadOrReadSystemFonts();
               
  	        /* Ouverture du document */
  	        Document document = new Document();
  	        try {
 	            document.setFile(filePath);
  	        } catch (PDFException ex) {
  	            System.out.println("Error parsing PDF document " + ex);
  	        } catch (PDFSecurityException ex) {
  	            System.out.println("Error encryption not supported " + ex);
  	        } catch (FileNotFoundException ex) {
  	            System.out.println("Error file not found " + ex);
  	        } catch (IOException ex) {
  	            System.out.println("Error handling PDF document " + ex);
  	        }
  	
  	        try {
  	            /* Creation d'un fichier text pour extraire le PDF */
  	            File file = new File("extracted_text.txt");
                    String monChemin = "C:/Users/Aizn/Desktop/";
                    BufferedWriter m_bw = new BufferedWriter(new FileWriter(monChemin + file));
 	
  	            /* Extraction du texte du PDF vers le fichier texte creer */
  	            for (int pageNumber = 0, max = document.getNumberOfPages();
  	                 pageNumber < max; pageNumber++) {
 	                PageText pageText = document.getPageText(pageNumber);
  	                System.out.println("Extracting page text: " + pageNumber);
  	                if (pageText != null && pageText.getPageLines() != null) {
 	                    ArrayList<LineText> pageLines = pageText.getPageLines();
  	                    for (LineText lineText : pageLines) {
 	                        m_bw.write(lineText.toString());
  	                        m_bw.write('\n');
  	                    }
  	                }
  	            }
                
  	            /* Fermeture du BufferedWriter */
  	            m_bw.close();
  	
  	        } catch (IOException ex) {
 	            System.out.println("Error writing to file " + ex);
  	        } catch (InterruptedException ex) {
  	            System.out.println("Error paring page " + ex);
 	        }
  	
                    /* Dispose le Document */
  	        document.dispose();
 	    }
  	}