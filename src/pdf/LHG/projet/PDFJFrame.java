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

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import pdf.LHG.projet.SwingViewBuilder;


public class PDFJFrame extends JFrame implements ActionListener{
        
   JMenu m_menu1,m_menu2,m_menu3,m_menu4,m_menu5,m_menu6,m_menu7;
   JMenuItem m_ouvrir,m_quitter,m_save,m_saveas;
   JMenuItem m_undo,m_redo,m_copier,m_coller;
   JMenuItem m_update,m_about;
   static JFrame frame1;
   SwingController m_controller;
   
   File m_file;
   JFileChooser fc = new JFileChooser();
   
    public PDFJFrame() 
	{  
		/* Création des composants */
		frame1 = new JFrame("PDF Viewer App");
		//JPanel m_panel1= new JPanel();
                
		/* Bar de menu */
		JMenuBar menu_bar1 = new JMenuBar();
                JMenuBar menu_bar2 = new JMenuBar();
                
		/* différents menus */
		    m_menu1 = new JMenu("File");
                    m_ouvrir = new JMenuItem("Open");
                    m_ouvrir.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/Icons/add.png")));
                    m_ouvrir.setAccelerator(KeyStroke.getKeyStroke('N', ActionEvent.CTRL_MASK));
                    m_ouvrir.addActionListener(this);
                    
                    m_save = new JMenuItem("Save");
                    m_save.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/Icons/save.png")));
                    m_save.setAccelerator(KeyStroke.getKeyStroke('S', ActionEvent.CTRL_MASK));
                    m_save.addActionListener(this);
                    
                    m_saveas = new JMenuItem("Save As...");
                    m_saveas.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/Icons/saveas.png")));
                    m_saveas.setAccelerator(KeyStroke.getKeyStroke('S', ActionEvent.CTRL_MASK+java.awt.event.ActionEvent.ALT_MASK));
                    m_saveas.addActionListener(this);
                    
                    m_quitter = new JMenuItem("Quit");
                    m_quitter.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/Icons/exit.png")));
                    m_quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
                    m_quitter.addActionListener((ActionEvent e) -> {
                        frame1.dispose();
            });
                    
                        m_menu1.add(m_ouvrir);
                        m_menu1.add(m_save);
                        m_menu1.add(m_saveas);
                        m_menu1.addSeparator();
                        m_menu1.add(m_quitter);
                    
		m_menu2 = new JMenu("Edit");
                    m_undo = new JMenuItem("Undo");
                    m_undo.setEnabled(false);
                    m_undo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/Icons/undo.png")));
                    m_redo = new JMenuItem("Redo");
                    m_redo.setEnabled(false);
                    m_redo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/Icons/redo.png")));
                    m_copier = new JMenuItem("Copy");
                    m_copier.setEnabled(false);
                    m_copier.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/Icons/copy.png")));
                    m_coller = new JMenuItem("Paste");
                    m_coller.setEnabled(false);
                    m_coller.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/Icons/paste.png")));
                        m_menu2.add(m_undo);
                        m_menu2.add(m_redo);
                        m_menu2.addSeparator();
                        m_menu2.add(m_copier);
                        m_menu2.add(m_coller);
                    
                m_menu3 = new JMenu("Tools");
                
                m_menu4 = new JMenu("View");
                
                m_menu5 = new JMenu("Help");
                    m_update = new JMenuItem("Check for Updates");
                    m_about = new JMenuItem("About");
                        m_menu5.add(m_update);
                        m_menu5.addSeparator();
                        m_menu5.add(m_about);
                
                m_menu6 = new JMenu("Test1");
                m_menu7 = new JMenu("Test2");
                JPanel jp_temp = new JPanel();
                jp_temp.add(m_menu6);
                jp_temp.add(m_menu7);
                jp_temp.setLayout(new GridLayout(1, 2));
		/* Ajouter les menu sur la bar de menu */
		menu_bar1.add(m_menu1);
		menu_bar1.add(m_menu2);
                menu_bar1.add(m_menu3);
		menu_bar1.add(m_menu4);
                menu_bar1.add(m_menu5);
                
                menu_bar2.add(m_menu6);
                menu_bar2.add(m_menu7);
                /* Ajout du Controller et Viewer ICEPDF */
                m_controller = new SwingController();
                
                pdf.LHG.projet.SwingViewBuilder m_factory = new SwingViewBuilder(m_controller);
               
                /* Ajouter la bar du menu à la frame */
		frame1.setJMenuBar(menu_bar1);
                JPanel m_viewerComponentPanel = m_factory.buildViewerPanel();
                /* add copy keyboard command */

                ComponentKeyBinding.install(m_controller, m_viewerComponentPanel);
                
                /* Ajout du  via callback */

                m_controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                m_controller.getDocumentViewController()));

                /* Ajout du JPanel contenant le Viewer au JFrame */
                frame1.add(jp_temp);
                frame1.getContentPane().add(m_viewerComponentPanel);
                frame1.pack();
                frame1.setSize(1000,700);
                frame1.setName("PDF Viewer App");
                frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame1.show(true);
	}
            @Override
            public void actionPerformed(ActionEvent event) {
                if(event.getSource() == m_ouvrir )
                {
                PdfFilter m_pdffilter = new PdfFilter();
                
                int val_retour = fc.showOpenDialog(frame1);
                if (val_retour == JFileChooser.APPROVE_OPTION) {
                   m_file = fc.getSelectedFile();
                   
                  if( m_pdffilter.accept(m_file)){
                   m_controller.openDocument(m_file.getAbsolutePath());
                  }else {
                      JOptionPane jop1;      
                        jop1 = new JOptionPane();
                        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("resources/Icons/error.png"));
                        jop1.showMessageDialog(null, "Le format du fichier que vous essayez d’ouvrir, " +m_file.getName()+ " est différent de l'extension .pdf !", "Erreur", JOptionPane.ERROR_MESSAGE, img);  
                  }
                } else {
                     System.out.println("L'ouverture est annulée\n");
                }
            }if(event.getSource() == m_saveas)
            {
                try {
                       PDFSavingAsFile m_pdfsaveas = new PDFSavingAsFile(m_file);
                    } catch (IOException ex) {
                        Logger.getLogger(PDFJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
            }if(event.getSource() == m_save)
            {
                    try {
                       PDFPSConverter m_pdfsave = new PDFPSConverter(m_file);
                    } catch (IOException ex) {
                        Logger.getLogger(PDFJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
            }
            }
}