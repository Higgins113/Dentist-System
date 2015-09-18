
//Stephen Higgins R00115149 DNET2
//INVOICE CLASS
package Model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Invoice 
{
	private int invoiceNo;
	private double invoiceAmt;
	private Date invoiceDate;
	private boolean isPaid;
	private ArrayList<Procedure> in_procList;
	private ArrayList<Payment> in_paymentList;
	private static int counter=1;

	
	public Invoice()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		setDate(date);
		in_procList = new ArrayList<Procedure>();
		in_paymentList = new ArrayList<Payment>();
		invoiceNo=counter;
		counter++;
		isPaid=false;
	}
	
	public void setDate(Date d)
	{
		invoiceDate=d;
	}
	
	public void setAmt()
	{		
		for (Procedure procedures : in_procList)
		{
		  invoiceAmt=invoiceAmt + procedures.getProcCost();
		}
		
	}

	public void addProcedure(ArrayList<Procedure> procList)
	{
		   for (Procedure proc : procList)
		    {
			   in_procList.add(proc);
			}
	}
	
	public void deleteProcedure(Procedure a)
	{
		in_procList.remove(a);
	}
	
	public void addPayment(Payment a)
	{
		in_paymentList.add(a);
		double check=invoiceAmt-a.getPaymentAmount();
		if(check==0)
		{
			isPaid=true;
		}
	}
	
	public ArrayList<Payment> getPayment()
	{
		return in_paymentList;
	}
	
	public Date getDate()
	{
		return invoiceDate;
	}
	
	public ArrayList<Procedure> getProcedures()
	{
		return in_procList;
	}
	
	public int getInvoiceNo()
	{
		return invoiceNo;
	}
	
	public double getAmt()
	{
		return invoiceAmt;
	}
	
	public String toString()
	{
		return "Invoice Details: Invoice No: " + getInvoiceNo() + " Cost " + getAmt()  + " Date: " + getDate() + "Status: " + isPaid;
	}
	
	public void print()
	{
		System.out.println(toString());
		   
	}
}
