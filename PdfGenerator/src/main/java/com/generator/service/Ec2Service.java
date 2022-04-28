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
import com.amazonaws.services.ec2.model.Reservation;
import com.generator.config.Config;
import com.generator.model.Ec2Data;


public class Ec2Service {

	public Ec2Data getEc2Data() {
		
AWSCredentials credentials = new BasicAWSCredentials(Config.ACCESS_KEY_ID, Config.ACCESS_SEC_KEY);
        
        AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard()
        		              .withCredentials(new AWSStaticCredentialsProvider(credentials))
        		              .withRegion(Regions.AP_SOUTH_1)
        		              .build();
        
        DescribeInstancesRequest request = new DescribeInstancesRequest();
       
            DescribeInstancesResult response = ec2Client.describeInstances(request);
            						
             List<Reservation> reservations =  response.getReservations();
             
             int Start_state = 0,Stop_state = 0;
             
             for(Reservation res : reservations) {
            	for (com.amazonaws.services.ec2.model.Instance instance : res.getInstances()) {
					
				String state =	instance.getState().getName();
					if(state.equals("stopped")) {
						Stop_state++;
					}else {
						Start_state++;
					}
				}
             }
             int total = Start_state+Stop_state;
             return new Ec2Data(Start_state,Stop_state,total);
	}
}
