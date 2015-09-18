//Stephen Higgins R00115149 DNET2
//REPORTS GUI

package GUI;

import java.awt.*; 
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import javax.swing.*;

import Model.Invoice;
import Model.Patient;
import Model.Payment;
import Model.Procedure;

public class ReportView extends JPanel implements ActionListener

{
	JButton report= new JButton("Generate Report");
	JLabel patient = new JLabel("PATIENTS");
	JLabel proc = new JLabel("PROCEDURES");
	JLabel pay = new JLabel("PAYMENTS");


	JList patientList,procedureList,paymentList;
	DefaultListModel patientModel,procedureModel,paymentModel;

	
	Procedure a = new Procedure("d",30);
	
	ArrayList<Patient> PatientList;

 public ReportView(ArrayList<Patient> patientList2)
  {  
	 super(); 
	 setLayout(null);
	 
	 PatientList=patientList2;
	 
	 a.minus();
	 
	 patientModel = new DefaultListModel();
	 patientList = new JList(patientModel);
	 
	 procedureModel = new DefaultListModel();
	 procedureList = new JList(procedureModel);
     
	 paymentModel = new DefaultListModel();
	 paymentList = new JList(paymentModel);
     
     
	 JScrollPane procedureScrollPane = new JScrollPane(procedureList);
	 procedureScrollPane.setBounds(10, 10, 100, 200);
     
     JScrollPane patientScrollPane = new JScrollPane(patientList);
     patientScrollPane.setBounds(10, 10, 100, 200);
     
     JScrollPane paymentScrollPane = new JScrollPane(paymentList);
     paymentScrollPane.setBounds(10, 10, 100, 200);
     
     add(procedureScrollPane);
     add(paymentScrollPane);
     add(patientScrollPane);
     add(report);
     add(patient);
     add(pay);
     add(proc);
     
     patient.setSize(150,40);
     patient.setLocation(530,140);
     
     proc.setSize(150,40);
     proc.setLocation(200,140);
     
     pay.setSize(150,40);
     pay.setLocation(850,140);
     
     procedureScrollPane.setSize(300,300);
     procedureScrollPane.setLocation(200,170);    
     
     patientScrollPane.setSize(300,300);
     patientScrollPane.setLocation(530,170);  
     
     paymentScrollPane.setSize(300,300);
     paymentScrollPane.setLocation(850,170);  
     
	 patient.setFont(new Font("Times New Romans", Font.BOLD,20));
	 proc.setFont(new Font("Times New Romans", Font.BOLD,20));
	 pay.setFont(new Font("Times New Romans", Font.BOLD,20));

     
     report.setSize(150,40);
     report.setLocation(650,500);
     report.addActionListener(this);
}

public void actionPerformed(ActionEvent arg0) 
{
		procedureModel.removeAllElements();
		patientModel.removeAllElements();

	  ArrayList<Procedure> procedureList =new ArrayList<Procedure>();

	  procedureList=a.getArray();
		ArrayList<Payment> in_paymentList = new ArrayList<Payment>();
		ArrayList<Invoice> in_invoiceList = new ArrayList<Invoice>();
		  
		for (Procedure procedures : procedureList)
	    {
			procedureModel.addElement(procedures);
		}
		
		for(Patient patients : PatientList)
		{
			patientModel.addElement(patients);
			in_invoiceList=patients.getInvoice();
		}
		
		for(Invoice invoices : in_invoiceList)
		{
			in_paymentList=invoices.getPayment();
		}
		
		for(Payment payments : in_paymentList)
		{
			paymentModel.addElement(payments);
		}
	  
	  
}

}
