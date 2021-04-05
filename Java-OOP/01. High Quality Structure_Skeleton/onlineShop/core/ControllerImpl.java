package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.BaseComputer;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.HashMap;
import java.util.Map;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Map<Integer, Computer> computers;

    public ControllerImpl() {
        computers = new HashMap<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        Computer computer;
        if (computers.containsKey(id)) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }

        if (computerType.equals("Laptop")) {
            computer = new Laptop(id, manufacturer, model, price);
        } else if (computerType.equals("DesktopComputer")) {
            computer = new DesktopComputer(id, manufacturer, model, price);
        } else {
            throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }

        this.computers.put(computer.getId(), computer);

        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        checkComputerExistWithId(computerId);
        Computer computer = computers.get(computerId);
        boolean sameIdComputerAndComponent = computer.getComponents().stream().anyMatch(c -> c.getId() == id);

        if (sameIdComputerAndComponent) {
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        } else if (componentType.equals("CentralProcessingUnit")) {
            computer.addComponent(new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation));
        } else if (componentType.equals("Motherboard")) {
            computer.addComponent(new Motherboard(id, manufacturer, model, price, overallPerformance, generation));
        } else if (componentType.equals("RandomAccessMemory")) {
            computer.addComponent(new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation));
        } else if (componentType.equals("PowerSupply")) {
            computer.addComponent(new PowerSupply(id, manufacturer, model, price, overallPerformance, generation));
        } else if (componentType.equals("SolidStateDrive")) {
            computer.addComponent(new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation));
        } else if (componentType.equals("VideoCard")) {
            computer.addComponent(new VideoCard(id, manufacturer, model, price, overallPerformance, generation));
        } else {
            throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }
        return String.format(ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        checkComputerExistWithId(computerId);
        Computer computer = computers.get(computerId);
        Component component = computer.removeComponent(componentType);
        if (component != null){
         return (String.format(REMOVED_COMPONENT,component.getClass()
                    .getSimpleName(),component.getId()));
        }
        return super.toString();
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        checkComputerExistWithId(computerId);
        Computer computer = computers.get(computerId);

        boolean haveSamePeripheral = computer.getPeripherals().stream().anyMatch(p -> p.getId() == id);

        if (haveSamePeripheral){
            throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
        }else if (peripheralType.equals("Headset")){
            computer.addPeripheral(new Headset(id,manufacturer,model,price,overallPerformance,connectionType));
        }else if (peripheralType.equals("Keyboard")){
            computer.addPeripheral(new Keyboard(id,manufacturer,model,price,overallPerformance,connectionType));
        }else if (peripheralType.equals("Monitor")){
            computer.addPeripheral(new Monitor(id,manufacturer,model,price,overallPerformance,connectionType));
        }else if (peripheralType.equals("Mouse")){
            computer.addPeripheral(new Mouse(id,manufacturer,model,price,overallPerformance,connectionType));
        }else {
            throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }
        return String.format(ADDED_PERIPHERAL,peripheralType,id,computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        checkComputerExistWithId(computerId);
        Computer computer = computers.get(computerId);

        boolean haveSamePerihelia = computer.getPeripherals().stream().anyMatch(p -> p.getClass().getSimpleName().equals(peripheralType));

        if (haveSamePerihelia){
            Peripheral peripheral = computer.removePeripheral(peripheralType);
            return String.format(REMOVED_PERIPHERAL,peripheralType,peripheral.getId());
        }
        return super.toString();
    }

    @Override
    public String buyComputer(int id) {
        checkComputerExistWithId(id);
        Computer computer = computers.get(id);
        computers.remove(computer.getId());
        checkComputerExistWithId(id);
        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        double bestCanBuy = 0;
        Computer computer = null;
        for (Map.Entry<Integer, Computer> computerEntry : computers.entrySet()) {
            double overallPerformance = computerEntry.getValue().getOverallPerformance();
            if (overallPerformance <= budget){
                bestCanBuy = overallPerformance;
                computer = computerEntry.getValue();
            }
        }
        if (bestCanBuy == 0){
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER,budget));
        }
        return computer.toString();
    }

    @Override
    public String getComputerData(int id) {
        checkComputerExistWithId(id);
        return computers.get(id).toString();
    }

    private void checkComputerExistWithId(int id) {
        if (!computers.containsKey(id)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
    }
}
