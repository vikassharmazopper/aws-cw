package aws.example.ec2;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DryRunResult;
import com.amazonaws.services.ec2.model.DryRunSupportedRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;

public class RecoverInstance {
	
	public static void recoverInstance(String instance_id)
    {
		 final AmazonEC2 ec2 = AmazonEC2ClientBuilder.defaultClient();

	       /* DryRunSupportedRequest<TerminateInstancesRequest> dry_request =
	            () -> {
	            	
	            	RecoverInstanceRequest request = new TerminateInstancesRequest()
	                .withInstanceIds(instance_id);

	            return request.getDryRunRequest();
	        }; */

	      //  DryRunResult dry_response = ec2.dryRun(dry_request);
		 DryRunResult dry_response = ec2.dryRun(null);

	        if(!dry_response.isSuccessful()) {
	            System.out.printf(
	                "Failed dry run to start instance %s", instance_id);

	            throw dry_response.getDryRunResponse();
	        }

	        TerminateInstancesRequest request = new TerminateInstancesRequest()
	            .withInstanceIds(instance_id);
	        
	        ec2.terminateInstances(request);
	        
	        System.out.printf("Successfully terminate instance %s", instance_id);
		
    }

}
