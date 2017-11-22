package ie.gmit.sw.TreadQueue;

public class Request {
	String taskNumber;
	String keyWord;

	public Request(String taskNumber, String keyWord) {
		super();
		this.taskNumber = taskNumber;
		this.keyWord = keyWord;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

}
