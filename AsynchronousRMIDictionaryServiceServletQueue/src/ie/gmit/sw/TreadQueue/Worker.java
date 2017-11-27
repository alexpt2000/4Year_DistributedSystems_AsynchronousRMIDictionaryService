package ie.gmit.sw.TreadQueue;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import ie.gmit.sw.ServiceSetup.DictionaryServiceInterface;

public class Worker implements Runnable {

	private BlockingQueue<Request> inQueue;
	private Map<String, DictionaryServiceInterface> outQueue;
	private DictionaryServiceInterface res;

	public Worker(BlockingQueue<Request> inQueue, Map<String, DictionaryServiceInterface> outQueue) {
		this.inQueue = inQueue;
		this.outQueue = outQueue;

	}

	@Override
	public void run() {
		Request req = inQueue.poll();

		try {
			System.out.println("\nChecking Status of Task No: " + req.getTaskNumber() + " - " + req.getKeyWord() );
			Thread.sleep(1000);

			outQueue.put(req.taskNumber, res);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
