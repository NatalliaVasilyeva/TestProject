package org.leverx.nvasilyeva.localsystem.сontroller;

import org.leverx.nvasilyeva.localsystem.entity.LatLong;
import org.leverx.nvasilyeva.localsystem.entity.Route;
import org.leverx.nvasilyeva.localsystem.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/distance/v1")
public class DistanceController {

    private DistanceService distanceService;

    @Autowired
    public DistanceController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @PostMapping(value = "/pointsDistance")
    @ResponseBody
    public double getDistance(@RequestBody Route route){
        LatLong from = route.getStart();
        LatLong to = route.getFinish();
        double distance;
        if (from!=null&&to!=null&&!from.equals(to)) {
           distance= distanceService.distanceInMeter(from, to);
        } else {
            throw new RuntimeException("Wrong input parameters");
        }
        return distance;
    }
}
