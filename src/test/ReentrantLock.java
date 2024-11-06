package test;

import java.util.concurrent.locks.Lock;

class shareresorce
{
	private final Lock lock = new java.util.concurrent.locks.ReentrantLock();
	
	public void preformOpration(String operation)
	{
		lock.lock();
		try {
		 System.out.println(Thread.currentThread().getName()+" - operation " + operation);
		}
		finally {
			lock.unlock();
		}
	}
}
public class ReentrantLock {

	public static void main(String[] args) {
		
		shareresorce shareresorce = new shareresorce();
		
		Thread thread1 = new Thread(() -> {
			for(int i=0; i<5; i++)
			{
				shareresorce.preformOpration("Thread1 "+i);
			}
		});
		
		Thread thread2 = new Thread(() -> {
			for(int i=0; i<5; i++)
			{
				shareresorce.preformOpration("Thread2 "+i);
			}
		});
		
		thread1.start();
		thread2.start();
	}

}
