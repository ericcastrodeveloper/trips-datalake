package dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.Trip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripRepository {

    private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

    public Trip save(final Trip trip){
        mapper.save(trip);
        return trip;
    }

    public List<Trip> findByPeriod(final String starts, final String ends, final String country){

        final Map<String, AttributeValue> eav = new HashMap();
        eav.put(":val1", new AttributeValue().withS(starts));
        eav.put(":val2", new AttributeValue().withS(ends));
        eav.put(":val3", new AttributeValue().withS(country));

        final DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
                .withKeyConditionExpression("country = :val3 and dateTrip between :val1 and :val2")
                .withExpressionAttributeValues(eav);

        List<Trip> trips = mapper.query(Trip.class, queryExpression);

        return trips;
    }

    public List<Trip> findByCountry(final String country){

        final Map<String, AttributeValue> eav = new HashMap();
        eav.put(":val1", new AttributeValue().withS(country));

        final DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
                .withKeyConditionExpression("country = :val1")
                .withExpressionAttributeValues(eav);

        List<Trip> trips = mapper.query(Trip.class, queryExpression);

        return trips;
    }

    public List<Trip> findByCity(final String city, final String country){

        final Map<String, AttributeValue> eav = new HashMap();
        eav.put(":val1", new AttributeValue().withS(city));
        eav.put(":val2", new AttributeValue().withS(country));

        final DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
                .withIndexName("cityIndex").withConsistentRead(false)
                .withKeyConditionExpression("country = :val2 and city = :val1")
                .withExpressionAttributeValues(eav);

        List<Trip> trips = mapper.query(Trip.class, queryExpression);

        return trips;
    }
}
