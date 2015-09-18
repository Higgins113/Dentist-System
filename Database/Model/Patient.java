
//Stephen Higgins R00115149 DNET2
//PATIENT CLASS

package Model;
import java.util.ArrayList;

public class Patient
{
	private int patientNo;
	private String patientName,patientAdd,patientPhone;
	private ArrayList<Invoice> p_invoiceList;
	private ArrayList<Procedure> in_procList;

	static int counter;
	
	public Patient(String name,String address,String phoneNo)
	{
		setName(name);
		setAddress(address);
		setPhoneNo(phoneNo);
		counter++;
		patientNo=counter;
		in_procList = new ArrayList<Procedure>();
		p_invoiceList = new ArrayList<Invoice>();
	}
	

	public void setName(String n)
	{
	     patientName=n;
	}
	
	
	public void setAddress(String a)
	{
	     patientAdd=a;
	}
	
	public void setPhoneNo(String n)
	{
	     patientPhone=n;
	}
	   
	public void setPatientNo(int n)
	{
		patientNo=n;
	}
	
	
	public String getName()
	{
		return patientName;
	}
	
	public String getPhone()
	{
		return patientPhone;
	}
	
	public String getAddress()
	{
		return patientAdd;
	}

	public void addInvoice()
	{
		Invoice i = new Invoice();
		i.addProcedure(in_procList);
		p_invoiceList.add(i);
		i.setAmt();
	}
	
	public void addProcedure(Procedure a)
	{
		in_procList.add(a);
	}
	
	public ArrayList getInvoice()
	{
		return p_invoiceList;
	}
	
	public ArrayList getProcedures()
	{
		return in_procList;
	}
	
	public int getPatientNo()
	{
		return patientNo;
	}
	
	public String toString()
	{
	   return  "ID: " + getPatientNo() + " Name: " + getName() + " Address: " + getAddress() + " PhoneNo:" + getPhone();
	}
	   
	public void print()
	{	 
	   System.out.println(toString());
	}

}