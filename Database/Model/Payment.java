
//Stephen Higgins R00115149 DNET2
//PAYMENT CLASS

package Model;
import java.util.Date;

public class Payment
{
	private static int paymentNo;
	private double paymentAmt;
	private Date paymentDate;

	public Payment(Date d, Double amt)
	{
		setDate(d);	
		setPaymentAmount(amt);
		paymentNo++;
	}
		
	public void setDate(Date d)
	{
		paymentDate=d;
	}
	
	public void setPaymentAmount(double p)
	{
		paymentAmt=p;
	}
	
	public Date getDate()
	{
		return paymentDate;
	}
	
	public double getPaymentAmount()
	{
		return paymentAmt;
	}
	
	public String toString()
	{
		return "Payment Details: " + getDate() + " " + getPaymentAmount();
	}
	
	public void print()
	{
		System.out.println(toString());
	}
}
