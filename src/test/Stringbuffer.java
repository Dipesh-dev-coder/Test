package test;

public class Stringbuffer {

	public static void main(String []args)
	{
		long startTime = System.currentTimeMillis();  
		StringBuffer sb = new StringBuffer();

		Thread t1 = new Thread(() ->{
			for(int i=0; i<1000; i++)
			{
				sb.append("A");
			}
		});
		
		Thread t2 = new Thread(() ->{
			for(int i=0; i<1000; i++)
			{
				sb.append("B");
			}
		});
		
		t1.start();
		t2.start();
		
		try
		{
			t1.join();
			t2.join();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Result taken by StringBuffer: "+sb.toString().length());
		
		StringBuffer sb1 = new StringBuffer();
		for(int i=0; i<1000; i++) {
			sb1.append("Hi");
		}
		System.out.println("time taken by StringBuffer "+(System.currentTimeMillis() - startTime)+" ms");
		
		startTime = System.currentTimeMillis();  
		StringBuilder sb2 = new StringBuilder();
		for(int i=0; i<1000; i++) {
			sb2.append("Hi");
		}
		System.out.println("time taken by StringBuilder "+(System.currentTimeMillis() - startTime)+" ms");
	}
}
