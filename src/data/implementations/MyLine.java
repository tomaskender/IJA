package data.implementations;

import data.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class MyLine implements Line {
    private String id;
    private List<Street> streets = new ArrayList<>();
    private List<Stop> stops = new ArrayList<>();
    private List<Vehicle> vehicles = new ArrayList<>();

    private List<Coordinate> route = new ArrayList<>();

    public static Line CreateLine(String id) { return new MyLine(id); }

    public MyLine(String id) {
        this.id = id;
    }

    @Override
    public boolean AddStop(Stop stop) {
        // is the street neighboring any existing streets?
        if(AddTraversalStreet(stop.getStreet()) == false)
            return false;
        if(stop.getStreet().AddStopToStreet(stop) == false)
            return false;
        stops.add(stop);
        return true;
    }

    @Override
    public boolean AddTraversalStreet(Street street) {
        boolean follows = false;
        if(streets.size() == 0) {
            follows = true;
        } else {
            for (Street s : streets) {
                if (s.Follows(street)) {
                    follows = true;
                    break;
                }
            }
        }
        if(follows == false) {
            return false;
        }
        if(!streets.contains(street))
            streets.add(street);
        return true;
    }

    @Override
    public boolean AddVehicleToLine(Vehicle v) {
        boolean alreadyContainsThisVehicle = false;
        for(Vehicle vehicle : vehicles) {
            if(vehicle.getStart() == v.getStart()) {
                alreadyContainsThisVehicle = true;
                break;
            }
        }
        if(!alreadyContainsThisVehicle) {
            vehicles.add(v);
            return true;
        } else {
            return false;
        }
    }
}
