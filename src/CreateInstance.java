import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.RunInstancesRequest;


public class CreateInstance {
    public static void main(String[] args) {
        //=============================== Submitting a Request =======================================//
        /*
         * The ProfileCredentialsProvider will return your [default]
         * credential profile by reading from the credentials file located at
         * (C:\\Users\\Justin\\.aws\\credentials).
         */
        AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider("default").getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (C:\\Users\\Justin\\.aws\\credentials), and is in valid format.",
                    e);
        }

        // Create the AmazonEC2Client object so we can call various APIs.
        AmazonEC2 ec2 = new AmazonEC2Client(credentials);
        Region usEast1 = Region.getRegion(Regions.US_EAST_1);
        ec2.setRegion(usEast1);
        RunInstancesRequest runInstancesRequest = 
        		  new RunInstancesRequest();
        	        	
        	  runInstancesRequest.withImageId("ami-a8d369c0")
        	                     .withInstanceType("t2.micro")
        	                     .withMinCount(1)
        	                     .withMaxCount(1)
        	                     .withKeyName("Pair")
        	                     .withSecurityGroups("default");
        
      
       ec2.runInstances(runInstancesRequest); 
        //ec2.createImage(createImageRequest);
        //System.out.print(ec2.describeAccountAttributes().toString());

    }
}
