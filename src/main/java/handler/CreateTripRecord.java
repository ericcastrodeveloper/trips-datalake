package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.TripRepository;
import model.HandlerRequest;
import model.HandlerResponse;
import model.Trip;

import java.io.IOException;

public class CreateTripRecord implements RequestHandler<HandlerRequest, HandlerResponse> {

    private final TripRepository repository = new TripRepository();


    @Override
    public HandlerResponse handleRequest(final HandlerRequest handlerRequest, final Context context) {

        Trip trip = null;

        try{
            trip = new ObjectMapper().readValue(handlerRequest.getBody(), Trip.class);
            System.out.println(trip);
            context.getLogger().log("Trip: "+ trip);
        }catch(IOException e){
            return HandlerResponse.builder().setStatusCode(400).setRawBody("There is a error in your Trip!").build();
        }
        context.getLogger().log("Creating a new trip record for the country" + trip.getCountry());
        final Trip tripRecorded = repository.save(trip);
        return HandlerResponse.builder().setStatusCode(201).setObjectBody(trip).build();
    }
}
