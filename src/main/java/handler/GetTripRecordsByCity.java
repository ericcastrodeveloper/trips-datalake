package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import dao.TripRepository;
import model.HandlerRequest;
import model.HandlerResponse;
import model.Trip;

import java.util.List;

public class GetTripRecordsByCity implements RequestHandler<HandlerRequest, HandlerResponse> {

    private final TripRepository repository = new TripRepository();


    @Override
    public HandlerResponse handleRequest(HandlerRequest handlerRequest, Context context) {
        String city = handlerRequest.getPathParameters().get("city");
        String country = handlerRequest.getPathParameters().get("country");

        context.getLogger()
                .log("Searching for registered trips for "+ city);

        final List<Trip> trips = this.repository.findByCity(city, country);

        if(trips == null || trips.isEmpty()){
            return HandlerResponse.builder().setStatusCode(404).build();
        }

        return HandlerResponse.builder().setStatusCode(200).setObjectBody(trips).build();
    }
}
