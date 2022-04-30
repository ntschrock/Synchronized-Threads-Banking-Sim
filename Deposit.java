import java.util.Random;

public class Deposit extends Main implements Runnable
{
	private Random rand = new Random();
	private Buffer sharedLocation;
	private String threadNameLocal;
	
	public Deposit(Buffer shared, String threadName)
	{
		sharedLocation = shared;
		threadNameLocal = threadName;
	}
	
	//Generates deposit amount from 1 to 250
	private int randomNum()
	{
		return rand.nextInt(250) + 1;
	}

	@Override
	public void run()
	{
		Thread.currentThread().setName(threadNameLocal);
		try 
		{
			Thread.sleep(rand.nextInt(5500));
			sharedLocation.deposit(randomNum());
		}catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}