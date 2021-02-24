package org.leverx.nvasilyeva.cloudspatialprovider.api;


import org.leverx.nvasilyeva.localsystem.entity.LatLong;

import java.time.LocalDate;

/**
Interface for emulate remote server works
 */

public interface RemoteCloudService {
    double FetchDistanceInMeters(LatLong from, LatLong to);
    LocalDate lastRoadNetworkUpdate();
}
