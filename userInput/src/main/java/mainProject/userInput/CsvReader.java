package mainProject.userInput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.opencsv.CSVReader;

import aws.example.cloudwatch.PutMetricAlarm;
import aws.example.ec2.RebootInstance;
import aws.example.ec2.StartStopInstance;
import aws.example.ec2.TerminateInstance;

public class CsvReader {
	
public static String LINE = "\n";
	
	public static String COMMA = ",";
	
	public static String TEMP_FILE_LOCATION = "/User/zopper/Desktop/invalidDetail.csv";

	public static void main(String args[]) {

		try {
			uploadFilel();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This function is useful for uploading file by a specific path on local ,
	 * staging , and prod at gstUplodJsonFormatFile.
	 */

	public static void uploadFilel() throws IOException {

		System.out.println("Request received for uploading GST Detail");

		Path path;
		File filePath;

		FileInputStream fileIn = new FileInputStream("/Users/zopper/Desktop/cloudWatchDetail.csv");

		File orgFile = new File("/Users/zopper/Desktop/cloudWatchDetail.csv");

		String timeStamp = System.currentTimeMillis() + "";
		String fileName = timeStamp + "_" + (orgFile).getName();
		path = Paths.get(new StringBuilder("/Users/zopper/Downloads/").append("/").append("gst").toString());
		filePath = new File(new StringBuilder("/Users/zopper/Downloads/").append("/").append("gst").toString());

		if ((orgFile).length() == 0) {
			System.out.println("Failed to store Empty File");

		}

		boolean isDirCreated = filePath.mkdirs();

		if (isDirCreated == false) {
			System.out.println("Folder is already present with same name");
		}
		try {
			Files.copy((fileIn), path.resolve(fileName));
		} catch (Exception e) {

			System.out.println("Exception in copying file and exception ");

		}

		fileName = path + "/" + fileName;

		try (CSVReader reader = new CSVReader(new FileReader(fileName))) {

			String[] nextLine;

			nextLine = reader.readNext();
			

			if (nextLine != null) {
				
				while ((nextLine = reader.readNext()) != null) {
					try {

						CloudWatchInput cloudWatchInput = new CloudWatchInput();

						String instanceId = nextLine[0];

						System.out.println("instanceId - " + instanceId);

						cloudWatchInput.setInstanceId(instanceId);

						String regions = nextLine[1];

						System.out.println("regions - " + regions);

						cloudWatchInput.setRegions(regions);

						String alarmName = nextLine[2];

						System.out.println("alarmName - " + alarmName);

						cloudWatchInput.setAlarmName(alarmName);

						String operator = nextLine[3];

						System.out.println("operator - " + operator);

						cloudWatchInput.setOperator(operator);

						String metricName = nextLine[4];

						System.out.println("metricName - " + metricName);

						cloudWatchInput.setMetricName(metricName);

						String statistic = nextLine[5];

						System.out.println("statistic - " + statistic);

						cloudWatchInput.setStatistic(statistic);

						String threshold = nextLine[6];

						System.out.println("threshold - " + threshold);

						cloudWatchInput.setThreshold(threshold);

						String description = nextLine[7];

						System.out.println("description - " + description);

						cloudWatchInput.setDescription(description);

						String alarmActionARN = nextLine[8];

						System.out.println("alarmActionARN - " + alarmActionARN);

						cloudWatchInput.setAlarmActionArn(alarmActionARN);

						String okActionARN = nextLine[9];

						System.out.println("okActionARN - " + okActionARN);

						cloudWatchInput.setOkActionArn(okActionARN);

						String ec2Action = nextLine[10];

						System.out.println("ec2Action - " + ec2Action);

						cloudWatchInput.setEc2Action(ec2Action);
							
						String period = nextLine[11];
						
						System.out.println("period - " + period);
						
						if(alarmActionARN!=null)
						{
							
							PutMetricAlarm pma = new PutMetricAlarm();
							try
							{
								System.out.println("Entering into put metric alarm");
							pma.putMetricAlarm(alarmName,instanceId,metricName,alarmActionARN,okActionARN,period,threshold,description,statistic,operator);
							System.out.println("Exit from put metric alarm");
							}
							catch(Exception e)
							{
								System.out.println("Exception in executing above method : {} "+e);
							}
						}
						
						

						if(ec2Action!=null)
						{
							StartStopInstance ssi = new StartStopInstance();	
							
							RebootInstance ri = new RebootInstance();
							
							TerminateInstance ti = new TerminateInstance();
							
							if(ec2Action.equals("start"))
							{
								ssi.startInstance(instanceId);
							}
							else if(ec2Action.equals("stop"))
							{
								ssi.stopInstance(instanceId);
							}
							else if(ec2Action.equals("reboot"))
							{
								ri.rebootInstance(instanceId);
							}
							else if(ec2Action.equals("terminate"))
							{
								ti.terminateInstance(instanceId);
							}
								
						}
					} catch (Exception e1) {
						System.out.format("%s ", e1);
						e1.printStackTrace();
					}

				}


			}

		}
	}
	

}
