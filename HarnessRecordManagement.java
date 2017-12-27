import javax.swing.JOptionPane;

public class HarnessRecordManagement 
{
	public static void main( String[] args )
	{
		In in = new In( "ClimbingHarnessRecords.txt" );
		HarnessRecords records = new HarnessRecords( in );
		boolean quit = false;
		String make;
		int modelNumber;
		String lastCheckedBy;
		String borrowingMember;
		
		while ( !quit )
		{
			try
			{
				String[] options = { "Add a harness to your records", "Remove a harness from your records", "Check the safety of a harness", 
						"Loan a harness to a member", "Return a harness from loan", "Print records" };
				String command = (String) JOptionPane.showInputDialog(null, "Update your records using one of the following commands: ",
						"Climbing Harness Records", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

				if ( command.equals( "Add a harness to your records" ) ) 
				{

					make = JOptionPane.showInputDialog( "**All new harnesses must be checked for safety before they can be loaned**\r\n"
							+ "Enter the make of your new harness: " );
					try
					{ 
						modelNumber = Integer.parseInt( JOptionPane.showInputDialog( "Enter the model number of your new harness: ") ); 
						Harness harness = new Harness( make, modelNumber, "null" );
						records.addHarness( harness );
						JOptionPane.showMessageDialog(null, "Successfully added harness to records." );
					}
					catch ( NumberFormatException exception ) 
					{ JOptionPane.showMessageDialog(null , "Invalid input: Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE ); }
				}
				else if ( command.equals( "Remove a harness from your records" ) )
				{
					make = JOptionPane.showInputDialog( "Enter the make of the harness being removed: " );
					try
					{	
						modelNumber = Integer.parseInt( JOptionPane.showInputDialog( "Enter the model number of the harness being removed: ") );

						if ( records.removeHarness( make, modelNumber ) == null )
							JOptionPane.showMessageDialog(null, "This harness could not be found. Please ensure you have entered its details correctly." );
						else
						{
							JOptionPane.showMessageDialog(null, "Successfully removed harness from records." );
						}
					}
					catch ( NumberFormatException exception ) 
					{ JOptionPane.showMessageDialog(null , "Invalid input: Please try again", "ERROR", JOptionPane.ERROR_MESSAGE ); }
				}
				else if ( command.equals( "Check the safety of a harness" ) )
				{
					lastCheckedBy = JOptionPane.showInputDialog( "Who is the instructor checking this harness? " );
					make = JOptionPane.showInputDialog( "Enter the make of the harness being checked: " );
					try
					{
						modelNumber = Integer.parseInt( JOptionPane.showInputDialog( "Enter the model number of the harness being checked: ") );
						if ( records.checkHarness( lastCheckedBy, make, modelNumber ) == null )
							JOptionPane.showMessageDialog(null, "This harness could not be checked. \r\nPlease ensure you have entered its details correctly - it may currently be on loan." );
						else
						{
							JOptionPane.showMessageDialog(null, "Successfully removed harness from records." );
						}
					}
					catch ( NumberFormatException exception ) 
					{ JOptionPane.showMessageDialog(null , "Invalid input: Please try again", "ERROR", JOptionPane.ERROR_MESSAGE ); }
				}
				else if ( command.equals( "Loan a harness to a member" ) )
				{
					borrowingMember = JOptionPane.showInputDialog( "Who is borrowing this harness? ");
					if ( records.loanHarness( borrowingMember ) == null )
						JOptionPane.showMessageDialog(null, "No harnesses currently available for loan. Please try again later." );
					else
					{
						JOptionPane.showMessageDialog(null, "A harness has been loaned to: " + borrowingMember );
					}
				}
				else if ( command.equals( "Return a harness from loan" ) )
				{
					make = JOptionPane.showInputDialog( "Enter the make of the harness being returned: " );
					try
					{
						modelNumber = Integer.parseInt( JOptionPane.showInputDialog( "Enter the model number of the harness being returned: ") );
						if ( records.returnHarness( make, modelNumber ) == null )
							JOptionPane.showMessageDialog(null, "This harness could not be returned. \r\nPlease ensure you have entered its details correctly." );
						else
						{
							JOptionPane.showMessageDialog(null, "Successfully returned harness from use." );
						}
						
					}
					catch ( NumberFormatException exception ) 
					{ JOptionPane.showMessageDialog(null , "Invalid input: Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE ); }
				}
				else if ( command.equals( "Print records" ) )
				{
					JOptionPane.showMessageDialog(null, records.printRecords() );
				}
			}
			catch ( NullPointerException exception ) 
			{ quit = true; }
		}
	}
}
