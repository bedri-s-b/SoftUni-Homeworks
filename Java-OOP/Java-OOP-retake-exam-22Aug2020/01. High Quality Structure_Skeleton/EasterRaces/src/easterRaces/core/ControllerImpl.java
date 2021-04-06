package easterRaces.core;

import easterRaces.common.ExceptionMessages;
import easterRaces.common.OutputMessages;
import easterRaces.core.interfaces.Controller;
import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.CarRepository;
import easterRaces.repositories.DriverRepository;
import easterRaces.repositories.RaceRepository;
import easterRaces.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static easterRaces.common.ExceptionMessages.CAR_EXISTS;
import static easterRaces.common.ExceptionMessages.DRIVER_EXISTS;
import static easterRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private DriverRepository drivers;
    private CarRepository cars;
    private RaceRepository races;

    public ControllerImpl(Repository<Driver> riderRepository, Repository<Car> motorcycleRepository, Repository<Race> raceRepository) {
        this.drivers = new DriverRepository();
        this.cars = new CarRepository();
        this.races = new RaceRepository();
    }

    @Override
    public String createDriver(String driver) {
        Driver driver1 = drivers.getByName(driver);
        if (driver1 == null) {
            drivers.add(new DriverImpl(driver));
            return String.format(DRIVER_CREATED, driver);
        } else {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car = cars.getByName(model);

        if (car == null) {
            switch (type) {
                case "Muscle":
                    car = new MuscleCar(model, horsePower);
                    break;
                case "Sports":
                    car = new SportsCar(model, horsePower);
                    break;
            }
            cars.add(car);
            return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
        } else {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }

    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = drivers.getByName(driverName);
        Car car = cars.getByName(carModel);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        } else if (car == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND, carModel));
        } else {
            driver.addCar(car);
            return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
        }
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = races.getByName(raceName);
        Driver driver = drivers.getByName(driverName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        } else if (driver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        } else {
            race.addDriver(driver);
            return String.format(DRIVER_ADDED, driverName, raceName);
        }
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = races.getByName(name);
        if (race != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
        } else {
            races.add(new RaceImpl(name,laps));
            return String.format(OutputMessages.RACE_CREATED,name);
        }
    }

    @Override
    public String startRace(String raceName) {
        Race race = races.getByName(raceName);
        if (race == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }else if (race.getDrivers().size() < 3){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName,3));
        }else {

            List<Driver> collect = race.getDrivers().stream()
                    .filter(Driver::getCanParticipate)
                    .sorted((d1, d2) -> Double.compare(d2.getCar().calculateRacePoints(race.getLaps()), d1.getCar().calculateRacePoints(race.getLaps())))
                    .limit(3)
                    .collect(Collectors.toList());


            StringBuilder sb = new StringBuilder();

            String name = race.getName();
            drivers.getByName(collect.get(0).getName()).winRace();
            races.remove(race);

            sb.append(String.format("Driver %s wins %s race.%n",collect.get(0).getName(),name));
            sb.append(String.format("Driver %s is second in %s race.%n",collect.get(1).getName(),name));
            sb.append(String.format("Driver %s is third in %s race.",collect.get(2).getName(),name));


            return sb.toString().trim();
        }
    }
}
