package mainProject.userInput;

public class CloudWatchInput {

String instanceId;
	
	String regions;
	
	String alarmName;
	
	String operator;
	
	String metricName;
	
	String statistic;
	
	String threshold;
	
	String description;
	
	String alarmActionArn;
	
	String okActionArn;
	
	String ec2Action;
	
	String period;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getRegions() {
		return regions;
	}

	public void setRegions(String regions) {
		this.regions = regions;
	}

	public String getAlarmName() {
		return alarmName;
	}

	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	public String getStatistic() {
		return statistic;
	}

	public void setStatistic(String statistic) {
		this.statistic = statistic;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAlarmActionArn() {
		return alarmActionArn;
	}

	public void setAlarmActionArn(String alarmActionArn) {
		this.alarmActionArn = alarmActionArn;
	}

	public String getOkActionArn() {
		return okActionArn;
	}

	public void setOkActionArn(String okActionArn) {
		this.okActionArn = okActionArn;
	}

	public String getEc2Action() {
		return ec2Action;
	}

	public void setEc2Action(String ec2Action) {
		this.ec2Action = ec2Action;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	

}
