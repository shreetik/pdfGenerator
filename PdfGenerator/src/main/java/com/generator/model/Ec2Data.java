package com.generator.model;

public class Ec2Data {

	private int Start_state;
	private int Stop_state;
	private int Total_instances;
	private int Volume_count;
	private int Snapshot_count;

	public Ec2Data(int start_state, int stop_state, int total_instances, int volume_count, int snapshot_count) {
		super();
		Start_state = start_state;
		Stop_state = stop_state;
		Total_instances = total_instances;
		Volume_count = volume_count;
		Snapshot_count = snapshot_count;
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

	public int getVolume_count() {
		return Volume_count;
	}

	public void setVolume_count(int volume_count) {
		Volume_count = volume_count;
	}

	public int getSnapshot_count() {
		return Snapshot_count;
	}

	public void setSnapshot_count(int snapshot_count) {
		Snapshot_count = snapshot_count;
	}

}
