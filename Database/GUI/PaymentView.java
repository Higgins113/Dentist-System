//Stephen Higgins R00115149 DNET2
//PAYMENT GUI

package GUI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;

import Model.Invoice;
import Model.Patient;
import Model.Payment;
import Model.Procedure;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentView extends JPanel
{
    JLabel patient = new JLabel("Select a Patient");
    JLabel invoice = new JLabel("Select an Invoice ");
    JLabel paymentNo     = new JLabel( "Payment Number:  " );
   	JLabel paymentAmt    = new JLabel( "Enter Payment Amount: " ) ;   
	   
	JTextField inInvoiceNo  = new JTextField(15);                                              	
	JTextField inInvoiceAmt = new JTextField(15);   
	JTextField inInvoiceDate = new JTextField(15);
	JTextField inPaymentNo  = new JTextField(15);                                              	
	JTextField inPaymentAmt = new JTextField(15);   
	 
	JList invoiceList,patientList;
	 
	DefaultListModel invoiceModel,patientModel;

	
	JButton addPayment= new JButton("Add a Payment");
	JButton refreshPatient= new JButton("Refresh the patients");
	JButton refreshInvoice= new JButton("Refresh patients invoices");

	 ArrayList<Patient> PatientList;

	 int PaymentNoCount=1;
	 
	public PaymentView(ArrayList<Patient> patientList2)
	{
		super();
		setLayout(null);
		setPreferredSize(new Dimension(1366, 1080));
		 inPaymentNo.setText(String.valueOf(PaymentNoCount));
		 
		PatientList=patientList2;
		
		invoiceModel = new DefaultListModel();
		invoiceList = new JList(invoiceModel);	
		    
		patientModel = new DefaultListModel();
		patientList = new JList(patientModel);	
			    
	    JScrollPane invoiceScrollPane = new JScrollPane(invoiceList);
	    invoiceScrollPane.setBounds(10, 10, 100, 200);
	    JScrollPane patientScrollPane = new JScrollPane(patientList);
	    patientScrollPane.setBounds(10, 10, 100, 200);
	    
		add(patient);
		add(paymentNo);
		add(paymentAmt);
		add(inPaymentNo);
		add(inPaymentAmt);
		add(addPayment);
		add(invoice);
		add(invoiceScrollPane);
		add(patientScrollPane);
		add(refreshPatient);
		add(refreshInvoice);
		
		patient.setSize(150,40);
		patient.setLocation(100,40);
	    patient.setFont(new Font("Times New Romans", Font.BOLD,20));
		
		invoice.setSize(250,70);
		invoice.setLocation(950,40);
	    invoice.setFont(new Font("Times New Romans", Font.BOLD,20));

		
		paymentNo.setSize(150,40);
		paymentNo.setLocation(500,180);
		
		paymentAmt.setSize(150,40);
		paymentAmt.setLocation(500,220);
		
		inPaymentNo.setSize(200,20);
		inPaymentNo.setLocation(650,190);
		inPaymentNo.setEditable(false);
		
		inPaymentAmt.setSize(200,20);
		inPaymentAmt.setLocation(650,230);


		refreshPatient.setSize(160,40);
		refreshPatient.setLocation(60,450);
		refreshPatient.addActionListener(new ActionListener()
		 {
	    	  
	    	  @Override
	    	  public void actionPerformed(ActionEvent arg0)
	    	  {  	     
	  	    	patientModel.removeAllElements();

	    		    for (Patient proc : PatientList)
	    		    {
	    		    	patientModel.addElement(proc);
	    			}
	    	  }
	      });
		
		refreshInvoice.setSize(200,40);
		refreshInvoice.setLocation(950,450);
		refreshInvoice.addActionListener(new ActionListener()
		{
			  @Override
	    	  public void actionPerformed(ActionEvent arg0)
	    	  {  	     
				  int selectedIndex = patientList.getSelectedIndex();
	    		  if (selectedIndex != -1) 
	    		  {
	    			  Patient a = (Patient) patientModel.getElementAt(selectedIndex);
	    			  ArrayList i=a.getInvoice();
	    			  
	    			  for (Object proc : i)
		    		    {
	    				  invoiceModel.addElement(proc);
		    			}
	    		  }
	    	  }
		});
	      
		
		addPayment.setSize(150,40);
		addPayment.setLocation(700,340);
		addPayment.addActionListener(new ActionListener()
		{
			 @Override
	    	  public void actionPerformed(ActionEvent arg0)
	    	  {  	     
	    		 int selectedInvoice = invoiceList.getSelectedIndex();
				 Double b = Double.parseDouble(inPaymentAmt.getText());
				 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				 Date date = new Date();
				 Payment a = new Payment(date,b);
				 PaymentNoCount++;
				 inPaymentNo.setText(String.valueOf(PaymentNoCount));
				 
				 if(selectedInvoice!=-1)
				 {
					 Invoice i = (Invoice) invoiceModel.getElementAt(selectedInvoice);
					 i.addPayment(a);
				 }
	    	  }
		});
	   
	    
		invoiceScrollPane.setSize(300,300);
		invoiceScrollPane.setLocation(900,100);
	    
		patientScrollPane.setSize(300,300);
		patientScrollPane.setLocation(20,100);
	

	}
	
}

