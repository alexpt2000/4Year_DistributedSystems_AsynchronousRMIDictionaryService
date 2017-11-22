package ie.gmit.sw.TreadQueue;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable{
	
	private BlockingQueue<Reference> inQueue;
	private Map<String, Definitions> outQueue;
	private Definitions definitions;

	
	
	public Worker(BlockingQueue<Reference> inQueue, Map<String, Definitions> outQueue){
		this.inQueue = inQueue;
		this.outQueue = outQueue;
		
	}

	@Override
	public void run() {
		Reference req = inQueue.poll();
		
		try {
			System.out.println("\nChecking Job No: " + req.getJobNumber());
			Thread.sleep(1000);
		
			outQueue.put(req.jobNumber, definitions);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
