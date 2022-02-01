package br.ufrn.imd.view;

public class PaymentAnswer {
	
	private PaymentAnswer instance;
	
	private PaymentAnswer() {}
	
	private Boolean paymentStatus;
	
	private Boolean waitingForAnswer;
	
	private double value;
	
	public PaymentAnswer getInstance() {
		if(instance == null) {
			instance = new PaymentAnswer();
		}
		return instance;
	}

	public Boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Boolean getWaitingForAnswer() {
		return waitingForAnswer;
	}

	public void setWaitingForAnswer(Boolean waitingForAnswer) {
		this.waitingForAnswer = waitingForAnswer;
	}
	
}
