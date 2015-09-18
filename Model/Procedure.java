
//Stephen Higgins R00115149 DNET2
//PROCEDURE CLASS

package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Procedure implements Serializable
{
	
	private int procNo;
	private String procName;
	private double procCost;
	static ArrayList<Procedure> ProcedureList= new ArrayList<Procedure>();

	static int counter=1;
	
	public Procedure(String procName,double procCost)
	{
		setProcName(procName);
		setProcCost(procCost);
		procNo=counter;
		counter++;
	}
	
	public void setProcName(String n)
	{
		procName=n;
	}
	
	
	public ArrayList<Procedure> getArray()
	{
		return ProcedureList;
	}
	
	public void addProc(Procedure n)
	{
		ProcedureList.add(n);
	}
	
	public void removeProc(int index)
	{
		ProcedureList.remove(index);
	}
	
	public void setProcCost(double p)
	{
	   procCost=p;
	}
	
	public int getProcNo()
	{
		return procNo;
	}
	
	public String getProcName()
	{
		return procName;
	}
	
	
	public double getProcCost()
	{
		return procCost;
	}
	
	
	public String toString()
	{
	    return  "ID: " + getProcNo() + " Name: " + getProcName() + " Cost:" + getProcCost();
	}
	   
	public void minus()
	{
		counter--;
	}
}