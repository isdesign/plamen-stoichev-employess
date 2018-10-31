/*
 * ************************************************************************* 
 *  
 *  @author	Plamen Stoichev 
 * 
 * ************************************************************************* 
 */
package sirmahomework;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Plamen Stoichev < Copyright: 2015-2018 >
 */
public class SirmaHomeWork extends JFrame{
   
    protected JButton loadBtn = new JButton("Load from...");
    protected JPanel topPanel, btnPanel;
    protected ReadFromFile readFromFile;
    protected SortPair sortPair;
    protected JTable table;
    
    public SirmaHomeWork(){
        
        readFromFile = ReadFromFile.getInstance();
        sortPair = new SortPair(readFromFile.getEmployeeList());
        loadBtn.addActionListener(new LoadFile());
        
        topPanel = new JPanel();
        btnPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.CENTER);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);
        table = new JTable();
        showDataInTable(sortPair.getLongestTime(), table);
        JScrollPane tableScrollContainer = new JScrollPane(table);
        topPanel.add(tableScrollContainer, BorderLayout.NORTH);
        
        btnPanel.add(loadBtn);
        
    
    }
    
    public final void showDataInTable(List<EmployeesPair> listOfPairs, JTable table){
     DefaultTableModel model = new DefaultTableModel(new Object[]{"Employee ID #1", 
                                                                  "Employee ID #2",
                                                                  "Project ID",
                                                                  "Days worked"}, 0);
     for(EmployeesPair pair : listOfPairs){
          model.addRow(new Object[]{pair.getFirstEmpID(), pair.getSecondEmpID(), pair.getProjectID(), pair.getTotalDays()});
     }
     table.setModel(model);
}
    class LoadFile implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileOpen = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("txt files", "txt");
            fileOpen.addChoosableFileFilter(filter);
            
            if (fileOpen.showOpenDialog(SirmaHomeWork.this) == JFileChooser.APPROVE_OPTION) {
            File file = fileOpen.getSelectedFile();
  
                try {
                    sortPair = new SortPair(readFromFile.getEmployeeList(file.getAbsolutePath()));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SirmaHomeWork.class.getName()).log(Level.SEVERE, null, ex);
                }
            showDataInTable(sortPair.getLongestTime(), table);
            revalidate();
            }
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SirmaHomeWork sirmaHomeWork = new SirmaHomeWork();
        sirmaHomeWork.setTitle("Sirma Homework - Plamen Stoichev");
        sirmaHomeWork.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sirmaHomeWork.setSize(600, 500);
        sirmaHomeWork.setLocationRelativeTo(null);
        sirmaHomeWork.setVisible(true);
        
    }
    
}
