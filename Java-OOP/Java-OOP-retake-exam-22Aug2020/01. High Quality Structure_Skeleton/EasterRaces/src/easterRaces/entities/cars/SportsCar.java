package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar {
    final static double CUBIC_CENTIMETERS = 3000;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower,CUBIC_CENTIMETERS);
        setHorsePower(horsePower);
    }


    private void setHorsePower(int horsePower) {
        if (horsePower < 250 ||horsePower > 450){
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,horsePower));
        }
        super.horsePower = horsePower;
    }


    @Override
    public double getCubicCentimeters() {
        return CUBIC_CENTIMETERS;
    }
}
