package ie.gmit.sw;

public class Words {
	String keyWord;
	String taskNumber;

	public Words(String keyWord, String taskNumber) {
		super();
		this.keyWord = keyWord;
		this.taskNumber = taskNumber;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

}
