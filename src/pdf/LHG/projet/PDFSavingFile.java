/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf.LHG.projet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.ri.common.ViewModel;
import org.icepdf.ri.common.views.AnnotationComponent;

/**
 *
 * @author Aizn
 */
public class PDFSavingFile {
    AnnotationComponent annotationComponent;
    public PDFSavingFile(File f) throws IOException{
        
        
    /* Création du dialog de sauvegarde du fichier  */
        final JFileChooser fileChooser = new JFileChooser();
      
        fileChooser.setCurrentDirectory(ViewModel.getDefaultFile());
        
        String filePath = f.getAbsolutePath();
        
        
    /* Ouverture du document */
        Document document = new Document();
        try {
            document.setFile(filePath);
        } catch (PDFException ex) {
            Logger.getLogger(PDFSavingFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PDFSecurityException ex) {
            Logger.getLogger(PDFSavingFile.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    /* Sauvegarde dans le meme Fichier Ouvert */
        fileChooser.setSelectedFile(f);
        File file = fileChooser.getSelectedFile();
            
    /* Creation du nouveau fichier PDF à l'aide d'un fileoutputstream*/
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                BufferedOutputStream buf = new BufferedOutputStream(
                        fileOutputStream, 4096 * 2);
                
                if (document.getStateManager().isChanged() &&
                        !Document.foundIncrementalUpdater) {
                    
                } else {
                    if (!document.getStateManager().isChanged()) {
                        document.writeToOutputStream(buf);
                    } else {
                        
                        document.saveToOutputStream(buf);
                        
        
                    }
                }
                buf.flush();
                fileOutputStream.flush();
                buf.close();
                fileOutputStream.close();
                
                ViewModel.setDefaultFile(file);
            }
}
    
    
   


