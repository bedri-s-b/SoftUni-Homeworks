package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.*;

public abstract class BaseCar implements Car {

    private String model;
    protected int horsePower;
    protected double cubicCentimeters;

    protected BaseCar(String model, int horsePower,double cubicCentimeters) {
        this.setModel(model);
    }

    protected void setModel(String model) {
        if (model.isEmpty() || model.trim().length() < 4){
            throw new IllegalArgumentException(String.format(INVALID_MODEL,model,4));
        }
        this.model = model;
    }

    public void setCubicCentimeters(double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }


    @Override
    public double calculateRacePoints(int laps) {
        return getCubicCentimeters() / getHorsePower() * laps;
    }
}
