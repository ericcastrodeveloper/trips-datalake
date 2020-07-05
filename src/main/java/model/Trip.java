package model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "trip")
public class Trip {

    @DynamoDBHashKey(attributeName = "country")
    private String country;
    @DynamoDBIndexRangeKey(attributeName = "city", localSecondaryIndexName = "cityIndex")
    private String city;
    @DynamoDBRangeKey(attributeName = "dateTrip")
    private String dateTrip;
    @DynamoDBAttribute(attributeName = "reason")
    private String reason;

    public Trip(String country, String city, String dateTrip, String reason) {
        this.country = country;
        this.city = city;
        this.dateTrip = dateTrip;
        this.reason = reason;
    }

    public Trip() {
        super();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateTrip() {
        return dateTrip;
    }

    public void setDateTrip(String dateTrip) {
        this.dateTrip = dateTrip;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
