package org.leverx.nvasilyeva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.leverx.nvasilyeva.cloudspatialprovider.api.RemoteCloudService;
import org.leverx.nvasilyeva.localsystem.service.impl.TimeServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TimeServiceTest {

    @Mock
    RemoteCloudService remoteCloudService;

    @InjectMocks
    TimeServiceImpl timeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void checkLastRoadNetworkUpdateTest_true() {

        when(remoteCloudService.lastRoadNetworkUpdate()).thenReturn(LocalDate.now().plusDays(7));

        boolean result=timeService.checkLastRoadNetworkUpdate();

        Assertions.assertTrue(result);

    }

    @Test
    void checkLastRoadNetworkUpdateTest_false() {

        when(remoteCloudService.lastRoadNetworkUpdate()).thenReturn(LocalDate.now().minusDays(7));

        boolean result=timeService.checkLastRoadNetworkUpdate();

        Assertions.assertFalse(result);

    }
}
