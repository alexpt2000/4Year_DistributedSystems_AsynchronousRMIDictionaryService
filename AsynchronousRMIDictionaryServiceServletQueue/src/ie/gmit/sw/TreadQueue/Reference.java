package ie.gmit.sw.TreadQueue;

public class Reference {
	String jobNumber;
	String definition;

	public Reference(String jobNumber, String definition) {
		super();
		this.jobNumber = jobNumber;
		this.definition = definition;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

}
