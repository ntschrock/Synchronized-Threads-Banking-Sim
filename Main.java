/*Name: Noah Schrock
  Course: CNT 4714 Fall 2021
  Assignment title: Project2:An Application EmployingSynchronized/Cooperating Multiple Threads In JavaUsing Locks–A Banking Simulator
  Due Date: October 3, 2021
*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main 
{
	public static void main(String[] args) 
    {
		ExecutorService application = Executors.newFixedThreadPool(14);
		Buffer sharedLocation = new SynchronizeTransaction();
		
		//headers
        System.out.printf("Deposit Threads\t\t\tWithdrawal Threads\t\t\t\tBalance\n");
		System.out.printf("---------------\t\t\t------------------\t\t\t\t--------\n");
		try
		{
			while(true)
            {
				//9 withdrawal threads
				application.execute(new Withdrawal(sharedLocation,"W1"));
				application.execute(new Withdrawal(sharedLocation,"W2"));
				application.execute(new Withdrawal(sharedLocation,"W3"));
				application.execute(new Withdrawal(sharedLocation,"W4"));
				application.execute(new Withdrawal(sharedLocation,"W5"));
				application.execute(new Withdrawal(sharedLocation,"W6"));
				application.execute(new Withdrawal(sharedLocation,"W7"));
				application.execute(new Withdrawal(sharedLocation,"W8"));
				application.execute(new Withdrawal(sharedLocation,"W9"));
				
				//6 deposit threads
				application.execute(new Deposit(sharedLocation,"D1"));
				application.execute(new Deposit(sharedLocation,"D2"));
				application.execute(new Deposit(sharedLocation,"D3"));
				application.execute(new Deposit(sharedLocation,"D4"));
				application.execute(new Deposit(sharedLocation,"D5"));
                application.execute(new Deposit(sharedLocation,"D6"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		application.shutdown();
	}
}