import java.util.Random;

public class Withdrawal implements Runnable 
{
	private Random rand = new Random();
	private Buffer sharedLocation;
	private String localThreadName;
	
	public Withdrawal(Buffer shared, String threadName)
	{
		sharedLocation = shared;
		localThreadName = threadName;
	}

	//Generates withdraw amount from 1 to 50
	private int randomNum()
	{
		return rand.nextInt(50) + 1;
	}

	@Override
	public void run()
	{
		Thread.currentThread().setName(localThreadName);
		try 
        {
			Thread.sleep(rand.nextInt(800));
			sharedLocation.withdrawal(randomNum());
		}catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}