package code.resmed.rockpaper.model;

public class ServiceResponse<T> {
	public enum Status {
		SUCCESS,
		FAILED
	}
	
	private Status status = Status.SUCCESS;
	private String message;
	private T payload;
	
	/* Auto-generated */
	public void setPayload(T payload) {
		this.payload = payload;
	}

	public T getPayload() {
		return payload;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
