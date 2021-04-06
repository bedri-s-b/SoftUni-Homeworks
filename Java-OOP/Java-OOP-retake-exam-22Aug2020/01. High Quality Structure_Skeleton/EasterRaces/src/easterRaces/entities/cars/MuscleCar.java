package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar {
    final static double CUBIC_CENTIMETERS = 5000;


    public MuscleCar(String model, int horsePower) {
        super(model, horsePower,CUBIC_CENTIMETERS);
        setHorsePower(horsePower);
    }

    private void setHorsePower(int horsePower) {
        if (horsePower < 400 ||horsePower > 600){
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,horsePower));
        }
        super.horsePower = horsePower;
    }


    @Override
    public double getCubicCentimeters() {
        return CUBIC_CENTIMETERS;
    }
}
