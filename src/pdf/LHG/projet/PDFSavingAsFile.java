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
import org.icepdf.ri.common.FileExtensionUtils;
import org.icepdf.ri.common.ViewModel;

/**
 *
 * @author Aizn
 */
public class PDFSavingAsFile {
    public PDFSavingAsFile(File f) throws IOException{
        
        
    /* Création du dialog de sauvegarde du fichier  */
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sauvegarde du Fichier PDF");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(FileExtensionUtils.getPDFFileFilter());
        if (ViewModel.getDefaultFile() != null) {
            fileChooser.setCurrentDirectory(ViewModel.getDefaultFile());
        }
        String filePath = f.getAbsolutePath();
        
        
    /* Ouverture du document */
        Document document = new Document();
        try {
            document.setFile(filePath);
        } catch (PDFException ex) {
            Logger.getLogger(PDFSavingAsFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PDFSecurityException ex) {
            Logger.getLogger(PDFSavingAsFile.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    /* Option d'ajout de "new" pour le nouveau pdf sauvegarder */
        fileChooser.setSelectedFile(new File(generateNewSaveName(f.getAbsolutePath())));
                
        

    /* Ouverture du Dialog de Sauvegarde  */
        int returnVal = fileChooser.showSaveDialog(pdf.LHG.projet.PDFJFrame.frame1);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            System.err.println("file="+fileChooser.getSelectedFile());
            
            
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
    
    
    protected String generateNewSaveName(String fileName) {
        if (fileName != null) {
            /* Ajout de la fonction qui rajoute "new" au fichier sauvergarder par la fonction SaveFile  */
            /* Par example Test.pdf deviendra Test-new.pdf */
            int endIndex = fileName.toLowerCase().indexOf(FileExtensionUtils.pdf) - 1;
            String result;
            if (endIndex < 0) {
                result = fileName + "-new." + FileExtensionUtils.pdf;
            } else {
                result = fileName.substring(0, endIndex) + "-new." +
                        FileExtensionUtils.pdf;
            }
            return result;
        }
        return null;
    }
        }
    


