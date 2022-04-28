package com.generator.model;

public class Ec2Data {

	private int Start_state;
	private int Stop_state;
	private int Total_instances;
	
	
	
	public Ec2Data(int start_state, int stop_state, int total_instances) {
		super();
		Start_state = start_state;
		Stop_state = stop_state;
		Total_instances = total_instances;
	}
	public int getStart_state() {
		return Start_state;
	}
	public void setStart_state(int start_state) {
		Start_state = start_state;
	}
	public int getStop_state() {
		return Stop_state;
	}
	public void setStop_state(int stop_state) {
		Stop_state = stop_state;
	}
	public int getTotal_instances() {
		return Total_instances;
	}
	public void setTotal_instances(int total_instances) {
		Total_instances = total_instances;
	}
	
	
}
