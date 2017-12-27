import java.util.ArrayList;

public class HarnessRecords 
{
	ArrayList<Harness> recordList;
	
	HarnessRecords()
	{
		recordList = new ArrayList<Harness>();
	}
	
	HarnessRecords( In input )
	{
		recordList = new ArrayList<Harness>();
		String make;
		int modelNumber;
		int usesSinceCheck;
		String lastCheckedBy;
		boolean isOnLoan;
		String borrowingMember;
		Harness harness;
		
		String[] characteristics;
		String line = input.readLine();
		int numberOfHarnesses = Integer.parseInt( line );

		for ( int i = 0; i < numberOfHarnesses; i++ )
		{
			line = input.readLine();
			if ( line != null )
			{
				characteristics = line.split( "   " );

				make = characteristics[0];
				modelNumber = Integer.parseInt( characteristics[1] );
				usesSinceCheck = Integer.parseInt( characteristics[2] );
				lastCheckedBy =  characteristics[3];

				if ( characteristics[4].equals( "true" ) ){
					isOnLoan = true;
					borrowingMember = characteristics[5];
				}
				else{
					isOnLoan = false;
					borrowingMember = null;
				}
				harness = new Harness( make, modelNumber, usesSinceCheck, lastCheckedBy, isOnLoan, borrowingMember );
				recordList.add( harness );
			}
		}
	}
	
	boolean isEmpty()
	{
		if ( this.isEmpty() )
			return true;
		else 
			return false;
	}
	
	void addHarness( Harness harness )
	{
		recordList.add( harness);
	}
	
	Harness findHarness( String make, int modelNumber )
	{
		if ( recordList.isEmpty() )
			return null;
		
		Harness harness;
		for ( int i = 0; i < recordList.size(); i++ )
		{
			harness = recordList.get(i);
			if ( harness.make.equals( make ) && harness.modelNumber == modelNumber )
				return harness;
		}
		return null;
	}
	
	Harness checkHarness( String instructorName, String make, int modelNumber )
	{
		Harness harness = findHarness( make, modelNumber );
		int index = recordList.indexOf( harness );
		
		if ( harness != null && harness.isHarnessOnLoan() == false )
		{
			harness.checkHarness( instructorName );
			recordList.set( index, harness );
			return harness;
		}
		else
			return null;
	}
	
	Harness loanHarness( String borrowingMember )
	{
		if ( recordList.isEmpty() )
			return null;
		
		Harness harness;
		for ( int i = 0; i < recordList.size(); i++ )
		{
			harness = recordList.get(i);
			if ( harness.canHarnessBeLoaned() == true )
			{
				harness.loanHarness( borrowingMember );
				recordList.set( i, harness );
				return harness;
			}		
		}
		return null;
	}
	
	Harness returnHarness( String make, int modelNumber )
	{
		Harness harness = findHarness( make, modelNumber );
		int index = recordList.indexOf( harness );
		
		if ( harness != null && harness.isHarnessOnLoan() == true )
		{
			harness.returnHarness();
			recordList.set( index, harness );
			return harness;
		}
		else
			return null;
	}
	
	Harness removeHarness( String make, int modelNumber )
	{
		Harness harness = findHarness( make, modelNumber );
		
		if ( harness != null )
		{
			recordList.remove( harness );
			return harness;
		}
		return null;
	}
	
	String printRecords()
	{
		String records = "";
		for ( int i = 0; i < recordList.size(); i++ )
		{
			records += recordList.get(i).toString() + "\r\n";
		}
		return records;
	}	
}
