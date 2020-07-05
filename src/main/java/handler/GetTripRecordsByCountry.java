package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import dao.TripRepository;
import model.HandlerRequest;
import model.HandlerResponse;
import model.Trip;

import java.util.List;

public class GetTripRecordsByCountry implements RequestHandler<HandlerRequest, HandlerResponse> {

    private final TripRepository repository = new TripRepository();

    @Override
    public HandlerResponse handleRequest(HandlerRequest handlerRequest, Context context) {

        final String country = handlerRequest.getPathParameters().get("country");

        context.getLogger().log("Searching for registered trips by country "+ country);

        final List<Trip> trips = this.repository.findByCountry(country);

        if(trips == null || trips.isEmpty()){
            return HandlerResponse.builder().setStatusCode(404).build();
        }
        return HandlerResponse.builder().setStatusCode(200).setObjectBody(trips).build();
    }
}
