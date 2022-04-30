import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;


public class SynchronizeTransaction implements Buffer 
{
	private Lock access = new ReentrantLock();
	private Condition canWithdraw = access.newCondition();
	private int balance = 0;
	public int getBalance()
	{
		return balance;
	}
	
	//deposit output
	@Override
	public void deposit(int value)
	{
		access.lock();
		
		balance += value;
		//deposit output line
		System.out.printf("Thread %s deposits $%d", Thread.currentThread().getName(), value);
		System.out.printf("\t\t\t\t\t\t\t\t\t\t\t(+)  Balance is %d\n", balance);	
		canWithdraw.signalAll();

		access.unlock();
	}
	
	//withdrawal output
	@Override
	public void withdrawal(int value)
	{
		access.lock();
		
		try
		{
			while(balance < value) //balance is less than withdraw amount
			{
				System.out.printf("\t\t\t\t\t\t\t\tThread %s withdraws $%d", Thread.currentThread().getName(), value);
				System.out.printf("\t\t\t(******) WITHDRAWAL BLOCKED - INSUFFICIENT FUNDS!!!\n");
				canWithdraw.await();
			}
			
			balance -= value;
			//balance is greater than or equal to the withdraw amount
			System.out.printf("\t\t\t\t\t\t\t\tThread %s withdraws $%d", Thread.currentThread().getName(), value);
			System.out.printf("\t\t\t(-)  Balance is %d\n", balance);
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			access.unlock();
		}
	}
}
