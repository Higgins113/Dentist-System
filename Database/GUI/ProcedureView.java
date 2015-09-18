//Stephen Higgins R00115149 DNET2
//PROCEDURES GUI

package GUI;

import java.awt.*; 
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import java.io.Serializable;

import javax.swing.*;

import Database.CreateDB;
import Model.Patient;
import Model.Procedure;

public class ProcedureView extends JPanel implements ActionListener
{
   JButton addProc= new JButton("Add a procedure");
   JButton deleteProc= new JButton("Delete a procedure");

   JLabel procName     = new JLabel( "Enter Procedure Name:  " );
   JLabel procCost    = new JLabel( "Enter Procedure Cost: " ) ;   
   JLabel procNo = new JLabel("Procedure ID: ");
   JLabel procedures     = new JLabel( "PROCEDURES" );
   
   JTextField procedureNo  = new JTextField(15);                                              
   JTextField inProcName = new JTextField(15);   
   JTextField inProcCost = new JTextField(15);

   JList procedureList;

   DefaultListModel procedureModel;
  
   ArrayList<Procedure> procs = new ArrayList();
   CreateDB cdb = new CreateDB();

   
   public ProcedureView() throws ClassNotFoundException, SQLException
   {  
	  super(); 
      setLayout(null );
         
      procedureModel = new DefaultListModel();
      procedureList = new JList(procedureModel);
      
      loadDatabase();
      	  
      JScrollPane procedureScrollPane = new JScrollPane(procedureList);
      procedureScrollPane.setBounds(10, 10, 100, 200);
      
      add(procName);
      add(inProcName);
      add(procCost);
      add(inProcCost);
      add(addProc);
      add(procedureNo);
      add(procNo);
      add(procedures);
      add(deleteProc);
      add(procedureScrollPane);
      procName.setSize(150,40);
      procName.setLocation(200,220);
      
      inProcName.setSize(200,20);
      inProcName.setLocation(400,230);
      
      procCost.setSize(150,40);
      procCost.setLocation(200,270);

      inProcCost.setSize(200,20);
      inProcCost.setLocation(400,280);
      
      addProc.setSize(150,40);
      addProc.setLocation(400,340);
      addProc.addActionListener(this);

      deleteProc.setSize(150,40);
      deleteProc.setLocation(700,550);
      deleteProc.addActionListener(new ActionListener()
      {
    	  
    	  @Override
    	  public void actionPerformed(ActionEvent arg0)
    	  {
    		  int selectedIndex = procedureList.getSelectedIndex();
    		  if (selectedIndex != -1) 
    		  {
    			  Procedure a = (Procedure) procedureModel.getElementAt(selectedIndex);
    		      a.removeProc(selectedIndex);
    		      procedureModel.remove(selectedIndex);
    		      procs.remove(a);
    		      int procNo = a.getProcNo();
    		      String SQL="DELETE FROM ProcedureS " + "WHERE procedureID = " + procNo; 									
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
      
      procNo.setSize(150,40);
      procNo.setLocation(200,180);

      procedureNo.setSize(200,20);
      procedureNo.setLocation(400,190);
      procedureNo.setText(String.valueOf(procs.size()+1));

      procedureScrollPane.setSize(200,300);
      procedureScrollPane.setLocation(700,170);    

      procedures.setSize(150,40);
      procedures.setLocation(700,120);
      procedures.setFont(new Font("Times New Romans", Font.BOLD,20));
      
      procedureNo.setEditable(false);
   
      
   }


  public void actionPerformed( ActionEvent evt)
  {
		 String a = inProcName.getText();
		 Double b = Double.parseDouble(inProcCost.getText());
		 
		 Procedure ab = new Procedure(a,b);
		 ab.addProc(ab);
	     procs.add(ab);
	     try {
			saveDatabase(ab);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     procedureModel.addElement(ab);
	     procedureNo.setText(String.valueOf(procs.size()+1));
	   	     
  } 


  public void saveDatabase(Procedure a) throws ClassNotFoundException, SQLException  
  {
	  String Name = a.getProcName();
	  Float Cost = (float) a.getProcCost();
	  int procNo = a.getProcNo();
	  
	  String SQL="insert into PROCEDURES values " + 
									"("+procNo+",'"+Name+"'," +Cost+""+")";
	  cdb.insert(SQL);

  }
  
  public void loadDatabase() throws ClassNotFoundException, SQLException  
  {
	  String SQL_STATEMENT = "SELECT * from procedures"+ "";
	  String Name = null;
	  Double Cost=0.0;
	  int procNo=0;
	
	 // db.main(args);
	  
		Connection cons = DriverManager.getConnection(CreateDB.JDBC_URL);
		Statement statement =  cons.createStatement();
		ResultSet rs = statement.executeQuery(SQL_STATEMENT);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		
		
		while (rs.next())
		{
			//System.out.println("");
			for(int i = 1 ; i<=columnCount; i ++)
			{
				procNo=rs.getInt(i);
				i++;
				Name=rs.getString(i);
				i++;
				Cost=(double) rs.getFloat(i);
				i++;
				Procedure ab = new Procedure(Name,Cost);
				procs.add(ab);
				ab.addProc(ab);
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
		
		for (Procedure procedures : procs)
	    {
			  procedureModel.addElement(procedures);
		}
	
		
  }
  
  

  
  	
}