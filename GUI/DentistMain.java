//Stephen Higgins R00115149 DNET2
//GUI MAIN

package GUI;
import java.sql.SQLException;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;

import Database.CreateDB;
import Model.Patient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DentistMain extends JPanel
{
	ProcedureView a;
	PatientView b;
	
	PaymentView c;
	ReportView d;
	ArrayList<Patient> patientList = new ArrayList<Patient>();

	public DentistMain() throws ClassNotFoundException, SQLException
	
	{
		
		JTabbedPane tpane = new JTabbedPane();
	    tpane.addTab("Maintenance", null,a=new ProcedureView() ,"Maintenance");
	    tpane.addTab("Patients", null,b=new PatientView(patientList),"Patients");
	    tpane.addTab("Payments", null,c=new PaymentView(patientList),"Payments");
	    tpane.addTab("Reports", null,d=new ReportView(patientList),"Reportss");

        setLayout(new GridLayout(1, 1));
        add(tpane);
	}
	
	  public static void main ( String[] args ) throws ClassNotFoundException, SQLException
	  {
		  
		//JMenuBar menuBar= new JMenuBar();
		//JMenu file = new JMenu("File");
        //JMenuItem save = new JMenuItem("Save");
        //JMenuItem load = new JMenuItem("Load");
       /* save.setToolTipText("Save Progress");
        save.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
            	   JFileChooser saveFile = new JFileChooser();
                   saveFile.showSaveDialog(null);
             }

        });

        load.setToolTipText("Save Progress");
        load.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
            	  JFileChooser openFile = new JFileChooser();
                  openFile.showOpenDialog(null);
                  File file = openFile.getSelectedFile();
                 
             }
        });
        */
        
        //file.add(save);
        //file.add(load);
		//menuBar.add(file);
		  
		JFrame frame = new JFrame ("Dentist System");		
		DentistMain procAdd = new DentistMain();      
		//frame.setJMenuBar(menuBar);
		
	    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );  
	    frame.setPreferredSize(new Dimension(1366, 1080));
	    frame.setContentPane(procAdd);
	    frame.setExtendedState(Frame.MAXIMIZED_BOTH);  //Maximize screed
	    frame.pack();
	    frame.setVisible(true);
	    
	  }

}
