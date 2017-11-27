package ie.gmit.sw;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class ServiceQueue implements Runnable {

	private BlockingQueue<Words> inQueue;
	private Map<String, Validator> outQueue;
	private Validator res;
	private DictionaryService strSer;

	public ServiceQueue(BlockingQueue<Words> inQueue, Map<String, Validator> outQueue, DictionaryService strSer) {
		this.inQueue = inQueue;
		this.outQueue = outQueue;
		this.strSer = strSer;
	}

	@Override
	public void run() {
		Words req = inQueue.poll();

		try {
			System.out.println("\nChecking Status of Task No: " + req.getTaskNumber());
			Thread.sleep(500);

			res = strSer.findDefinition(req.getKeyWord());

			System.out.println(req.getKeyWord());
			outQueue.put(req.taskNumber, res);
		} catch (RemoteException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
