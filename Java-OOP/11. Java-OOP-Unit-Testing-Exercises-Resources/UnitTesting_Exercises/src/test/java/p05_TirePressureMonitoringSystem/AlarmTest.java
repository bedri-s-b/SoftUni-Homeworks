package p05_TirePressureMonitoringSystem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AlarmTest {
    private Alarm alarm;
    private Sensor sensor;

    @Before
    public void setUp() {
        this.sensor = Mockito.mock(Sensor.class);
        this.alarm = new Alarm(sensor);
    }

    @Test
    public void when_pressureLowerThenLowPressureThreshold_then_alarmShouldBeOn() {
        // mock sensor
        Mockito.when(this.sensor.popNextPressurePsiValue()).thenReturn(14.6);
        this.alarm.check();
        assertTrue(this.alarm.getAlarmOn());
    }

    @Test
    public void when_pressureHighThenHighPressureThreshold_then_alarmShouldBeOn(){
        // mock again
        Mockito.when(this.sensor.popNextPressurePsiValue()).thenReturn(30.2);
        this.alarm.check();
        assertTrue(this.alarm.getAlarmOn());
    }

    @Test
    public void when_thePressureIsBetweenLowAndHigh_thenAlarmShouldBeOf(){
        Mockito.when(this.sensor.popNextPressurePsiValue()).thenReturn(17.0);
        this.alarm.check();
        assertFalse(this.alarm.getAlarmOn());
    }

}