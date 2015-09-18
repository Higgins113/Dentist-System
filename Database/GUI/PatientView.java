//Stephen Higgins R00115149 DNET2
//PATIENTS GUI

package GUI;

import javax.swing.*;

import Model.Invoice;
import Model.Patient;
import Model.Procedure;
import Database.CreateDB;
import Database.QueryDB;

import java.awt.*; 
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PatientView extends JPanel implements ActionListener
{
	 JLabel patientID     = new JLabel( "Patient ID:  " );
	 JLabel patientName     = new JLabel( "Enter Patients Name:  " );
	 JLabel patientAddress    = new JLabel( "Enter Patients Address : " ) ;   
	 JLabel patientPhoneNo    = new JLabel( "Enter Patients phone No : " ) ;   
	 JLabel patients     = new JLabel( "PATIENTS" );
	 Procedure a = new Procedure(" ",0);
	 JLabel storedProcedures     = new JLabel( "STORED PROCEDURES" );
	 JLabel procedures     = new JLabel( "PROCEDURES" );
	 

	 JTextField inPatID  = new JTextField(15);                                              
	 JTextField inPatientName = new JTextField(15);   
	 JTextField inPatientAddress = new JTextField(15);
	 JTextField inPatientPhoneNo = new JTextField(15);	 	 
	 
	 JButton addPatient= new JButton("Add a patient");
	 JButton addProcedure= new JButton("Add procedure");
	 JButton deletePatient= new JButton("Delete patient");
	 JButton deleteProcedure= new JButton("Delete a procedure");
	 JButton refreshProcedure= new JButton("Refresh procedures");
	 JButton listProcedure= new JButton("List patients procedures");
	 JButton createInvoice= new JButton("Create an Invoice");
	 
	 JList patientList,procedureslist,patientsProcedureList;
	 
	 DefaultListModel patientModel,proceduresModel,patientsProcedureModel;
	
	 ArrayList<Patient> PatientList;

	 ArrayList<Procedure> procList = new ArrayList<Procedure>();
	 
	 CreateDB cdb = new CreateDB();
	 
	 public PatientView(ArrayList<Patient> patientList2)
	 {		 
		super();
		setLayout(null);
		procList = a.getArray();
	     a.minus();
		PatientList=patientList2;
		
	    patientModel = new DefaultListModel();
	    patientList = new JList(patientModel);
		
	    
		try {
			loadDatabase();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    proceduresModel = new DefaultListModel();
	    procedureslist = new JList(proceduresModel);	

	    patientsProcedureModel = new DefaultListModel();
	    patientsProcedureList = new JList(patientsProcedureModel);
	    
	    JScrollPane patientsScrollPane = new JScrollPane(patientList); 
	    patientsScrollPane.setBounds(10, 10, 100, 200); // Setting Scroll Pane
	    
	    JScrollPane proceduresScrollPane = new JScrollPane(procedureslist);
	    proceduresScrollPane.setBounds(10, 10, 100, 200);
	    
	    JScrollPane patientsProcedureScrollPane = new JScrollPane(patientsProcedureList);
	    patientsProcedureScrollPane.setBounds(10, 10, 100, 200);
	    
	    add(patientID);
		add(patientName);
		add(patientAddress);
		add(patientPhoneNo);
		add(inPatID);
		add(inPatientName);
		add(inPatientAddress);
		add(inPatientPhoneNo);
		add(patients);
		add(addPatient);
		add(procedures);
		add(addProcedure);
		add(deletePatient);
		add(refreshProcedure);
		add(deleteProcedure);
	    add(patientsScrollPane);
	    add(proceduresScrollPane);
	    add(patientsProcedureScrollPane);
	    add(listProcedure);
	    add(procedures);
	    add(storedProcedures);
	    add(createInvoice);

		inPatID.setSize(200,20);
		inPatID.setLocation(300,190);
		inPatID.setText(String.valueOf(PatientList.size()+1));
		inPatID.setEditable(false);
		
		inPatientName.setSize(200,20);
		inPatientName.setLocation(300,230);
		
		inPatientAddress.setSize(200,20);
		inPatientAddress.setLocation(300,280);
		
		inPatientPhoneNo.setSize(200,20);
		inPatientPhoneNo.setLocation(300,330);
		
		patientID.setSize(150,40);
		patientID.setLocation(100,180);
		
		patientName.setSize(150,40);
		patientName.setLocation(100,220);
	      
		patientAddress.setSize(150,40);
		patientAddress.setLocation(100,270);
	      
		patientPhoneNo.setSize(150,40);
		patientPhoneNo.setLocation(100,320);
		
	    patientsScrollPane.setSize(250,300);
	    patientsScrollPane.setLocation(550,170);
	    
	    proceduresScrollPane.setSize(200,300);
	    proceduresScrollPane.setLocation(820,170);
	    
	    patientsProcedureScrollPane.setSize(200,300);
	    patientsProcedureScrollPane.setLocation(1050,170);
	
	    addPatient.setSize(150,40);
	    addPatient.setLocation(300,370);
	    addPatient.addActionListener(this);
	    
	    addProcedure.setSize(150,40);
	    addProcedure.setLocation(820,550);
	    addProcedure.addActionListener(new ActionListener()
	    {
	    	  
	    	  @Override
	    	  public void actionPerformed(ActionEvent arg0)
	    	  {
	    		  int selectedPatient = patientList.getSelectedIndex(); //Getting selected patient
	    		  int selectedProcedure = procedureslist.getSelectedIndex(); //Getting selected procedure
	    		  
	    		  if (selectedPatient != -1) 
	    		  {
	    			  Patient a = (Patient) patientModel.getElementAt(selectedPatient);
	    			  Procedure b = (Procedure) proceduresModel.getElementAt(selectedProcedure);
	    			  a.addProcedure(b);   			  
	    		  }
	    	  }
	      });
	    
	    
	    deletePatient.setSize(150,40);
	    deletePatient.setLocation(600,500);
	    deletePatient.addActionListener(new ActionListener()
	    {
	    	  
	    	  @Override
	    	  public void actionPerformed(ActionEvent arg0)
	    	  {
	    		  int selectedIndex = patientList.getSelectedIndex();
	    		  if (selectedIndex != -1) 
	    		  {
	    			  Patient a = (Patient) patientModel.getElementAt(selectedIndex);
	    		      patientModel.remove(selectedIndex);
	    		      PatientList.remove(a);
	    			  int patNo = a.getPatientNo();
	    			  System.out.println(patNo);
	    			  String SQL="DELETE FROM PATIENTS " + "WHERE patientID = " + patNo; 									
	    			  try {
						cdb.delete(SQL);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		  }
	    	  }
	      });
	      
	    deleteProcedure.setSize(150,40);
	    deleteProcedure.setLocation(1050,550);
	   /* deleteProcedure.addActionListener(new ActionListener()
	    {
	    	@Override
	    	  public void actionPerformed(ActionEvent arg0)
	    	  {
	    		  int selectedIndex = patientList.getSelectedIndex();
	    		  int selectedProc = patientsProcedureList.getSelectedIndex();

	    		  if (selectedIndex != -1) 
	    		  {
	    			  Patient a = (Patient) patientModel.getElementAt(selectedIndex);
	    			  procList.remove(selectedProc);
	    			 
	    		  }
	    	  }
	    	
	    /});*/
	    
	    listProcedure.setSize(200,40);
	    listProcedure.setLocation(1050,500);
	    listProcedure.addActionListener(new ActionListener()
	    {
	    	  
	    	  @Override
	    	  public void actionPerformed(ActionEvent arg0)
	    	  {
	    		  patientsProcedureModel.removeAllElements();

	    		  ArrayList<Procedure> procedureList =new ArrayList<Procedure>();
	    		  
	    		  int selectedIndex = patientList.getSelectedIndex();
	    		  if (selectedIndex != -1) 
	    		  {
	    			  Patient a = (Patient) patientModel.getElementAt(selectedIndex);
	    			  procedureList=a.getProcedures();
	    			  
	    			  for (Procedure procedures : procedureList)
		    		    {
	    				  patientsProcedureModel.addElement(procedures);
		    			}
	    		  }
	    	  }
	      });
	    
	    refreshProcedure.setSize(150,40);
	    refreshProcedure.setLocation(820,500);
	    refreshProcedure.addActionListener(new ActionListener()
	    {
	    	  
	    	  @Override
	    	  public void actionPerformed(ActionEvent arg0)
	    	  {  	     
	    		  proceduresModel.removeAllElements();

	    		    for (Procedure proc : procList)
	    		    {
	    		    	proceduresModel.addElement(proc);
	    			}
	    	  }
	      });
	    
	    createInvoice.setSize(150,40);
	    createInvoice.setLocation(1050,600);
	    createInvoice.addActionListener(new ActionListener()
	    {
	    	  @Override
	    	  public void actionPerformed(ActionEvent arg0)
	    	  {  	     
	    		  int selectedIndex = patientList.getSelectedIndex();
    			  Patient a = (Patient) patientModel.getElementAt(selectedIndex);
    			  a.addInvoice();    	
	    		  patientsProcedureModel.removeAllElements();
	    	  }
	    	
	    });
	
	    patients.setSize(150,40);
	    patients.setLocation(600,120);
	    patients.setFont(new Font("Times New Romans", Font.BOLD,20));

	    procedures.setSize(150,40);
	    procedures.setLocation(1060,120);
	    procedures.setFont(new Font("Times New Romans", Font.BOLD,20));
	    
	    storedProcedures.setSize(250,40);
	    storedProcedures.setLocation(810,120);
	    storedProcedures.setFont(new Font("Times New Romans", Font.BOLD,20));
	 }
	 
	  public void actionPerformed( ActionEvent evt)  
	  {
		  String a = inPatientName.getText();
		  String b = inPatientAddress.getText();
		  String c = inPatientPhoneNo.getText();

		  Patient ab = new Patient(a,b,c);
		  
		  PatientList.add(ab);
		  patientModel.addElement(ab);
		  inPatID.setText(String.valueOf(ab.getPatientNo()+1));
		  try {
			saveDatabase(ab);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  }
	  
	  public void saveDatabase(Patient a) throws ClassNotFoundException, SQLException  
	  {
		  String Name = a.getName();
		  String Address = a.getAddress();
		  String PhoneNo = a.getPhone();
		  int patNo = a.getPatientNo();
		  String SQL="insert into PATIENTS values " + 
										"("+patNo+",'"+Name+"','" +Address+"','" +PhoneNo +"'"+")";
		  cdb.insert(SQL);

	  }
	  
	  
	  public void loadDatabase() throws ClassNotFoundException, SQLException  
	  {
		  String SQL_STATEMENT = "SELECT * from patients"+ "";
		  String Name = null;
		  String Address = null;
		  String PhoneNo = null;
		  int patNo=0;
		
		 // db.main(args);
		  
			Connection cons = DriverManager.getConnection(CreateDB.JDBC_URL);
			Statement statement =  cons.createStatement();
			ResultSet rs = statement.executeQuery(SQL_STATEMENT);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			//for(int i = 1 ; i<=columnCount; i ++)
			//{
			//	System.out.format("%20s", rsmd.getColumnName(i) + " | ");
		//	}
			
			while (rs.next())
			{
				//System.out.println("");
				for(int i = 1 ; i<=columnCount; i ++)
				{
					patNo=rs.getInt(i);
					i++;
					Name=rs.getString(i);
					i++;
					Address=rs.getString(i);
					i++;
					PhoneNo=rs.getString(i);
					Patient ab = new Patient(Name,Address,PhoneNo);
					 PatientList.add(ab);
					//System.out.format("%20s", rs.getString(i) + " | ");
				} 
			}

			
			if(statement != null)
			{
				statement.close();
			}
			
			if(cons!=null)
			{
				cons.close();
			}
			
			for (Patient patients : PatientList)
    	    {
				  patientModel.addElement(patients);
   			}
		
			
	  }
	  

}
