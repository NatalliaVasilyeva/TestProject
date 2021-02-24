package org.leverx.nvasilyeva.localsystem.service.impl;


import org.leverx.nvasilyeva.cloudspatialprovider.api.RemoteCloudService;
import org.leverx.nvasilyeva.localsystem.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TimeServiceImpl implements TimeService {
    private LocalDate lastUpdate=LocalDate.now();

    private RemoteCloudService remoteCloudDistanceService;

    @Autowired
    public TimeServiceImpl(RemoteCloudService remoteCloudDistanceService) {
        this.remoteCloudDistanceService = remoteCloudDistanceService;
    }

    /**
     * Method is used for compare last update time on local server and remote server
     * @return true - local server has out of date time
     *         false - local server has normal time
     */
    public boolean checkLastRoadNetworkUpdate(){
        boolean isChanged=false;
        LocalDate lastRoadsUpdateFromServer=remoteCloudDistanceService.lastRoadNetworkUpdate();
        if(getLastUpdate().isBefore(lastRoadsUpdateFromServer)){
           setLastUpdate(lastRoadsUpdateFromServer);
            isChanged=true;
        }
        return isChanged;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
