package dao;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DynamoDBManager {
    private static DynamoDBMapper mapper;

    static {

        AmazonDynamoDB ddb = null;
        final String endpoint = System.getenv("ENDPOINT_OVERRIDE");

        if (endpoint != null && !endpoint.isEmpty()) {
            AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(endpoint, "us-east-1");
            ddb = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(endpointConfiguration).build();
        } else {
            ddb = AmazonDynamoDBClientBuilder.defaultClient();
        }

        mapper = new DynamoDBMapper(ddb);
    }

    public static DynamoDBMapper mapper() {
        return DynamoDBManager.mapper;
    }

}
