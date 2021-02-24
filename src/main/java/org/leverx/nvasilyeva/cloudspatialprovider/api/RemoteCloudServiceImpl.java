package org.leverx.nvasilyeva.cloudspatialprovider.api;

import org.leverx.nvasilyeva.localsystem.entity.LatLong;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 Class for emulate remote server works
 */
@Service
public class RemoteCloudServiceImpl implements RemoteCloudService {
    private LocalDate time=LocalDate.now();

    /**
     * Simulate method that return information about distance from remote server.
     * It simple method for return distance between 2 points without additional conditions
     * In real project would be such conditions like traffic jam, road construction
     *
     * @param from - contain parameters of start point
     * @param to - contain parameters of finish point
     * @return distance between 2 points
     */
    @Override
    public double FetchDistanceInMeters(LatLong from, LatLong to) {
        int latitudeStart = from.getLatitude();
        int latitudeFinish = to.getLatitude();
        int longitudeStart = from.getLongitude();
        int longitudeFinish = to.getLongitude();
        return Math.sqrt(Math.pow((latitudeFinish-latitudeStart),2)+Math.pow((longitudeFinish-longitudeStart),2));
    }

    /**
     * Simulate method of time checking
     * @return time of last update information about distance on server
     */
    @Override
    public LocalDate lastRoadNetworkUpdate() {
        return time;
    }

    /**
     * Method changes updating time on server
     */
    @Scheduled(cron = "0 0 */7 * *")
    public void changeUpdateTimeEvery7Days() {
        time=LocalDate.now();
    }
}
