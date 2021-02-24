package org.leverx.nvasilyeva.localsystem.service.impl;

import org.leverx.nvasilyeva.cloudspatialprovider.api.RemoteCloudService;
import org.leverx.nvasilyeva.localsystem.entity.LatLong;
import org.leverx.nvasilyeva.localsystem.service.DistanceService;
import org.leverx.nvasilyeva.localsystem.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceImpl implements DistanceService {

    private RemoteCloudService remoteCloudDistanceService;
    private TimeService timeService;

    @Autowired
    public DistanceServiceImpl(RemoteCloudService remoteCloudDistanceService, TimeService timeService) {
        this.remoteCloudDistanceService = remoteCloudDistanceService;
        this.timeService=timeService;
    }

    /**
     * Send request to remote server to find distance between 2 points.
     * Use cache because this request is very expensive and have rare updates
     * @param from contain parameters of start point
     * @param to contain parameters of finish point
     * @return distance between 2 point. If info is not out of date return value from cache,
     *         else make request to remote server
     */
    @Override
    @Cacheable(cacheNames="distance", key = "#from+to")
    public double distanceInMeter(LatLong from, LatLong to) {
           return remoteCloudDistanceService.FetchDistanceInMeters(from, to);
    }

    /**
     * Invalidate all cache
     */
    @CacheEvict(value = "distance", allEntries = true)
    public void deleteAllCacheValuesOfDistance() {
    }

    /**
     * Delete cache if local time of update info is out of date
     * Scheduled method run at 10 o'clock every 7 days
     */
    @Scheduled(cron = "0 10 */7 * *")
    public void deleteCache(){
        if(timeService.checkLastRoadNetworkUpdate()) deleteAllCacheValuesOfDistance();
    }

}
