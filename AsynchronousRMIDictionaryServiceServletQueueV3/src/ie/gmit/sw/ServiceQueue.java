package ie.gmit.sw;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class ServiceQueue implements Runnable {

	private BlockingQueue<Words> inQueue;
	private Map<String, Resultator> outQueue;
	private Resultator res;
	private DictionaryService strSer;

	public ServiceQueue(BlockingQueue<Words> inQueue, Map<String, Resultator> outQueue, DictionaryService strSer) {
		this.inQueue = inQueue;
		this.outQueue = outQueue;
		this.strSer = strSer;

	}

	@Override
	public void run() {
		Words req = inQueue.poll();

		try {
			System.out.println("\nChecking Status of Task No: " + req.getTaskNumber());
			Thread.sleep(1000);

			res = strSer.compare(req.getKeyWord());
			System.out.println(req.getKeyWord());
			outQueue.put(req.taskNumber, res);
		} catch (RemoteException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
