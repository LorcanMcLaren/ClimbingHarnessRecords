
public class Harness 
{
	String make;
	int modelNumber;
	private int usesSinceCheck;
	private String lastCheckedBy;
	private boolean isOnLoan;
	private String borrowingMember;
	
	Harness( String make, int modelNumber, int usesSinceCheck, String lastCheckedBy, boolean isOnLoan, String borrowingMember )
	{
		this.make = make;
		this.modelNumber = modelNumber;
		this.usesSinceCheck = usesSinceCheck;
		this.lastCheckedBy = lastCheckedBy;
		this.isOnLoan = isOnLoan;
		this.borrowingMember = borrowingMember;
	}
	
	Harness( String make, int modelNumber, String lastCheckedBy )
	{
		this.make = make;
		this.modelNumber = modelNumber;
		this.lastCheckedBy = lastCheckedBy;
		usesSinceCheck = 25;
		isOnLoan = false;
		borrowingMember = null;
	}
	
	void checkHarness( String instructorChecking )
	{
		if ( !isOnLoan)
		{
			usesSinceCheck = 0;
			lastCheckedBy = instructorChecking;
		}
		else
			throw new IllegalArgumentException ( "ERROR: Harness cannot be checked while on loan." );
		
	}
	
	boolean isHarnessOnLoan()
	{
		if ( isOnLoan )
			return true;
		else
			return false;
	}
	
	boolean canHarnessBeLoaned()
	{
		if ( !isOnLoan && usesSinceCheck < 25 )
			return true;
		else
			return false;
	}
	
	Harness loanHarness( String borrowingMember )
	{
		if ( this.canHarnessBeLoaned() )
		{
			usesSinceCheck++;
			isOnLoan = true;
			this.borrowingMember = borrowingMember;
			return this;
		}
		else
			return null;
	}
	
	Harness returnHarness()
	{
		if ( this.isHarnessOnLoan() )
		{
			isOnLoan = false;
			borrowingMember = null;
			return this;
		}
		else
			return null;
	}
	
	public String toString()
	{
		String output =  "Make: " + make + "; Model Number: " + modelNumber + 
				"; Uses Since Last Check: " + usesSinceCheck + "; Checked By: " + lastCheckedBy + "; "
						+ "Is On Loan: " + isOnLoan;
		if ( isOnLoan )
			output += "; On Loan To: " + borrowingMember + "\r\n";
		else
			output += "\r\n";
		return output;
	}	
}
