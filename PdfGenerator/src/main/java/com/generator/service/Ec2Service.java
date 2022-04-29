package com.generator.service;

import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeSnapshotsRequest;
import com.amazonaws.services.ec2.model.DescribeSnapshotsResult;
import com.amazonaws.services.ec2.model.DescribeVolumesRequest;
import com.amazonaws.services.ec2.model.DescribeVolumesResult;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Snapshot;
import com.amazonaws.services.ec2.model.Volume;
import com.generator.config.Config;
import com.generator.model.Ec2Data;


public class Ec2Service {

	public Ec2Data getEc2Data() {

		AWSCredentials credentials = new BasicAWSCredentials(Config.ACCESS_KEY_ID, Config.ACCESS_SEC_KEY);

		AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_SOUTH_1).build();

		boolean done = false;
		int Start_state = 0, Stop_state = 0;

		DescribeInstancesRequest request = new DescribeInstancesRequest();

		try {

			while (!done) {
				DescribeInstancesResult response = ec2Client.describeInstances(request);

				for (Reservation reservation : response.getReservations()) {
					for (com.amazonaws.services.ec2.model.Instance instance : reservation.getInstances()) {
						String state = instance.getState().getName();
						if (state.equals("stopped")) {
							Stop_state++;
						} else {
							Start_state++;
						}
					}
				}
				request.setNextToken(response.getNextToken());

				if (response.getNextToken() == null) {
					done = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		int totalEc2 = Start_state + Stop_state;

		DescribeVolumesRequest volumeRequest = new DescribeVolumesRequest();
		done = false;
		int volumeCount = 0;

		try {

			while (!done) {
				DescribeVolumesResult volumeResult = ec2Client.describeVolumes(volumeRequest);
				for (Volume volume : volumeResult.getVolumes()) {
					String vid = volume.getVolumeId();
					if (vid != null) {
						volumeCount++;
					}
				}
				volumeRequest.setNextToken(volumeResult.getNextToken());

				if (volumeResult.getNextToken() == null) {
					done = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		DescribeSnapshotsRequest snapshotRequest = new DescribeSnapshotsRequest();

		done = false;
		int snapCount = 0;

		try {

			while (!done) {
				DescribeSnapshotsResult snapshotResult = ec2Client.describeSnapshots(snapshotRequest);

				for (Snapshot snapshot : snapshotResult.getSnapshots()) {
					String snap = snapshot.getSnapshotId();
					if (snap != null) {
						snapCount++;
					}
				}

				snapshotRequest.setNextToken(snapshotResult.getNextToken());

				if (snapshotResult.getNextToken() == null) {
					done = true;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Ec2Data(Start_state, Stop_state, totalEc2, volumeCount, snapCount);
	}
}
