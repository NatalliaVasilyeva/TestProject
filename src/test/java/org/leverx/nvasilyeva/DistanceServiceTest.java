package org.leverx.nvasilyeva;

import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.leverx.nvasilyeva.cloudspatialprovider.api.RemoteCloudService;
import org.leverx.nvasilyeva.localsystem.entity.LatLong;
import org.leverx.nvasilyeva.localsystem.service.impl.DistanceServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DistanceServiceTest {

    @Mock
    RemoteCloudService remoteCloudService;

    @InjectMocks
    DistanceServiceImpl distanceService;

    LatLong startPoint;
    LatLong finishPoint;
    Random random = new Random();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        startPoint = new LatLong(random.nextInt(), random.nextInt());
        finishPoint = new LatLong(random.nextInt(), random.nextInt());
    }

    @AfterEach
    public void clear() {
        startPoint = null;
        finishPoint = null;
    }

    @Test
    void getDistanceBetweenPoints_true() {
        LatLong startPointNew = new LatLong(0, 0);
        LatLong finishPointNew = new LatLong(4, 7);

        lenient().when(remoteCloudService.FetchDistanceInMeters(startPointNew, finishPointNew)).thenReturn(8.06);
        double expectedDistance = Precision.round(Math.sqrt(Math.pow((finishPointNew.getLatitude()-startPointNew.getLatitude()),2)+Math.pow((finishPointNew.getLongitude()-startPointNew.getLatitude()),2)),2);

        double actual = distanceService.distanceInMeter(startPointNew, finishPointNew);

        Assertions.assertEquals(expectedDistance, actual);
    }

    @Test
    void getDistanceBetweenPoints_false() {
        LatLong startPointNew = new LatLong(0, 0);
        LatLong finishPointNew = new LatLong(4, 7);

        lenient().when(remoteCloudService.FetchDistanceInMeters(startPointNew, finishPointNew)).thenReturn(8.0);
        double expectedDistance = Precision.round(Math.sqrt(Math.pow((finishPointNew.getLatitude()-startPointNew.getLatitude()),2)+Math.pow((finishPointNew.getLongitude()-startPointNew.getLatitude()),2)),2);

        double actual = distanceService.distanceInMeter(startPointNew, finishPointNew);

        Assertions.assertNotEquals(expectedDistance, actual);
    }
}
