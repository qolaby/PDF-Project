/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf.LHG.projet;

import java.io.File;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.PInfo;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Aizn
 */
public class PDFMetaDataExtraction {
    
  	    public PDFMetaDataExtraction(File f) {
                
                
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
  	
  	        /* Initialiser les differentes entrees qu'on devrai rcuperer */
                String title = "";
  	        String author = "";
  	        String subject = "";
 	        String keyWords = "";
  	        String creator = "";
  	        String producer = "";
  	        String creationDate = "";
  	        String modDate = "";
  	
  	        /* Recuperation des Entrees du PDF */
  	        PInfo documentInfo = document.getInfo();
 	        if (documentInfo != null) {
  	            title = documentInfo.getTitle();
  	            author = documentInfo.getAuthor();
  	            subject = documentInfo.getSubject();
	            keyWords = documentInfo.getKeywords();
  	            creator = documentInfo.getCreator() != null ?
 	                    documentInfo.getCreator() : "Not Available";
  	            producer = documentInfo.getProducer() != null ?
  	                    documentInfo.getProducer() : "Not Available";
  	            creationDate = documentInfo.getCreationDate() != null ?
  	                    documentInfo.getCreationDate().toString() : "Not Available";
  	            modDate = documentInfo.getModDate() != null ?
 	                    documentInfo.getModDate().toString() : "Not Available";
  	        }
  	
  	        /* Test Si ca Marche Sortie avec println*/
  	        System.out.println("Title:    " + title);
  	        System.out.println("Subject:  " + subject);
  	        System.out.println("Author:   " + author);
  	        System.out.println("Keywords: " + keyWords);
  	        System.out.println("Creator:  " + creator);
  	        System.out.println("Producer: " + producer);
  	        System.out.println("Created:  " + creationDate);
  	        System.out.println("Modified: " + modDate);
  	
  	        /* Dispose le Document */
  	        document.dispose();
  	    }
  	}
