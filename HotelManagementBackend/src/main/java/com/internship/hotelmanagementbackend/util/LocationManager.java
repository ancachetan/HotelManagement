package com.internship.hotelmanagementbackend.util;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.stereotype.Component;

/*I have provided 3 methods for distance calculation
 *       - using the Harvesine formula
 *       - transforming from degrees to meters for both latitude and longitude and then calculating the distance between 2 points
 *       - using geodesy library and a WGS84 ellipsoid as a reference
 *
 * */

@Component
public class LocationManager {
    private static final double EARTH_RADIUS = 6367.449;

    public boolean isLocationInRadius(double latitude1, double longitude1, double latitude2, double longitude2, double radius) {
        return calculateDistance(latitude1, longitude1, latitude2, longitude2) <= radius;
    }

    private double calculateDistanceUsingHaversineFormula(double latitude1, double longitude1, double latitude2, double longitude2) {
        double distanceLatitude = Math.toRadians(latitude1 - latitude2);
        double distanceLongitude = Math.toRadians(longitude1 - longitude2);
        double distance = Math.sin(distanceLatitude / 2) * Math.sin(distanceLatitude / 2) +
                Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) *
                        Math.sin(distanceLongitude / 2) * Math.sin(distanceLongitude / 2);
        double c = 2 * Math.atan2(Math.sqrt(distance), Math.sqrt(1 - distance));
        return EARTH_RADIUS * c;
    }

    private double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double x1 = longitudeToMeters(longitude1, latitude1);
        double y1 = latitudeToMeters(latitude1);
        double x2 = longitudeToMeters(longitude2, latitude2);
        double y2 = latitudeToMeters(latitude2);

        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private double calculateDistanceUsingWGS84Ellipsoid(double latitude1, double longitude1, double latitude2, double longitude2) {
        GeodeticCalculator geodeticCalculator = new GeodeticCalculator();
        Ellipsoid reference = Ellipsoid.WGS84;
        GlobalPosition position1 = new GlobalPosition(latitude1, longitude1, 0.0);
        GlobalPosition position2 = new GlobalPosition(latitude2, longitude2, 0.0);
        double distance = geodeticCalculator.calculateGeodeticCurve(reference, position2, position1).getEllipsoidalDistance();
        return distance / 1000;
    }

    private double latitudeToMeters(double latitude) {
        return latitude * (Math.PI * EARTH_RADIUS / 180.0);
    }

    private double longitudeToMeters(double longitude, double latitude) {
        return longitude * (Math.PI * EARTH_RADIUS / 180.0) * Math.cos(Math.toRadians(latitude));
    }
}
