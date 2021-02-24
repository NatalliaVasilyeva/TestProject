package org.leverx.nvasilyeva.localsystem.service;

import org.leverx.nvasilyeva.localsystem.entity.LatLong;

/**
 * Interface for working with distance information
 */

public interface DistanceService {
    double distanceInMeter(LatLong from, LatLong to);
}
