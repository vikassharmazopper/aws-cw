/*
 * Copyright 2010-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package aws.example.cloudwatch;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.ComparisonOperator;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.PutMetricAlarmRequest;
import com.amazonaws.services.cloudwatch.model.PutMetricAlarmResult;
import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.amazonaws.services.cloudwatch.model.Statistic;

/**
 * Creates a new CloudWatch alarm based on CPU utilization for an instance
 */
public class PutMetricAlarm {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		final String USAGE = "To run this example, supply an alarm name and instance id\n"
				+ "Ex: DeleteAlarm <alarm-name> <instance-id>\n";

		if (args.length != 2) {
			System.out.println(USAGE);
			System.exit(1);
		}

		String alarmName = args[0];
		String instanceId = args[1];

		final AmazonCloudWatch cw = AmazonCloudWatchClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

		Dimension dimension = new Dimension().withName("InstanceId").withValue(instanceId);

		PutMetricAlarmRequest request = new PutMetricAlarmRequest().withAlarmName(alarmName)
				.withComparisonOperator(ComparisonOperator.LessThanThreshold).withEvaluationPeriods(1)
				.withMetricName("CPUUtilization").withNamespace("AWS/EC2").withPeriod(300)
				.withStatistic(Statistic.Minimum).withThreshold(70.0).withActionsEnabled(true)
				.withAlarmDescription("Alarm when server CPU utilization exceeds 70%").withDimensions(dimension)
				.withAlarmActions("arn:aws:sns:us-east-1:841668200780:cloudwatch-test")
				.withOKActions("arn:aws:sns:us-east-1:841668200780:cloudwatch-test");

		PutMetricAlarmResult response = cw.putMetricAlarm(request);

		System.out.printf("Successfully created alarm with name %s", alarmName);

	}

	public void putMetricAlarm(String alarmName1, String instanceId1, String metricName, String alarmArn, String okAlarmArn, String period,
			String threshhold, String description, String statistic, String operator) {

		final String USAGE = "To run this example, supply an alarm name and instance id\n"
				+ "Ex: DeleteAlarm <alarm-name> <instance-id>\n";

		String alarmName = alarmName1;
		String instanceId = instanceId1;

		//final AmazonCloudWatch cw = AmazonCloudWatchClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
		
		final AmazonCloudWatch cw = AmazonCloudWatchClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_1).build();


		Dimension dimension = new Dimension().withName("InstanceId").withValue(instanceId);
		
		System.out.println("Here 1");

		ComparisonOperator c = null;
		Statistic s = null;

		if (operator.equals("LessThanThreshold"))
			c = ComparisonOperator.LessThanThreshold;
		if (operator.equals("GreaterThanThreshold"))
			c = ComparisonOperator.GreaterThanThreshold;
		if (operator.equals("GreaterThanOrEqualToThreshold"))
			c = ComparisonOperator.GreaterThanOrEqualToThreshold;
		if (operator.equals("LessThanOrEqualToThreshold"))
			c = ComparisonOperator.LessThanOrEqualToThreshold;
		
		System.out.println("Here 2");

		if (statistic.equals("minimum"))
			s = Statistic.Minimum;
		if (statistic.equals("maximum"))
			s = Statistic.Maximum;
		if (statistic.equals("average"))
			s = Statistic.Average;
		
		if(okAlarmArn==null || okAlarmArn.length() < 1)
			okAlarmArn = alarmArn;
		
		System.out.println("Here 3");

		PutMetricAlarmRequest request = new PutMetricAlarmRequest().withAlarmName(alarmName).withComparisonOperator(c)
				.withEvaluationPeriods(1).withMetricName(metricName).withNamespace("AWS/EC2")
				.withPeriod(Integer.parseInt(period)).withStatistic(s).withThreshold(Double.parseDouble(threshhold))
				.withActionsEnabled(true).withAlarmDescription(description).withDimensions(dimension)
				.withAlarmActions(alarmArn).withOKActions(okAlarmArn);
		
		System.out.println("Here 4");

		PutMetricAlarmResult response = cw.putMetricAlarm(request);

		System.out.println("Successfully created alarm with name %s : {} "+alarmName);
		
		System.out.println("Response is : {} " + response);

		/*
		 * String alarmName = alarmName1; String instanceId = instanceId1;
		 * 
		 * 
		 * final AmazonCloudWatch cw =
		 * AmazonCloudWatchClientBuilder.standard().withRegion(Regions.US_EAST_1
		 * ).build();
		 * 
		 * Dimension dimension = new Dimension() .withName("InstanceId")
		 * .withValue(instanceId);
		 * 
		 * PutMetricAlarmRequest request = new PutMetricAlarmRequest()
		 * .withAlarmName(alarmName) .withComparisonOperator(
		 * ComparisonOperator.LessThanThreshold) .withEvaluationPeriods(1)
		 * .withMetricName("CPUUtilization") .withNamespace("AWS/EC2")
		 * .withPeriod(300) .withStatistic(Statistic.Minimum)
		 * .withThreshold(70.0) .withActionsEnabled(true) .withAlarmDescription(
		 * "Alarm when server CPU utilization exceeds 70%")
		 * .withDimensions(dimension).withAlarmActions(
		 * "arn:aws:sns:us-east-1:841668200780:cloudwatch-test").withOKActions(
		 * "arn:aws:sns:us-east-1:841668200780:cloudwatch-test");
		 * 
		 * PutMetricAlarmResult response = cw.putMetricAlarm(request);
		 * 
		 * System.out.printf( "Successfully created alarm with name %s",
		 * alarmName);
		 */

	}
}
