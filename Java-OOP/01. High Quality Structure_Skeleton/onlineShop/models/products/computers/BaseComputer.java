package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.COMPUTER_COMPONENTS_TO_STRING;
import static onlineShop.common.constants.OutputMessages.COMPUTER_PERIPHERALS_TO_STRING;

public abstract class BaseComputer extends BaseProduct implements Computer {

    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        return super.getOverallPerformance() + components.stream()
                .mapToDouble(Component::getOverallPerformance).average().orElse(0);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + components.stream().mapToDouble(Component::getPrice).sum()
                + peripherals.stream().mapToDouble(Peripheral::getPrice).sum();
    }

    @Override
    public void addComponent(Component component) {
        for (Component component1 : components) {
            if (component1.getClass().getSimpleName().equals(component.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(EXISTING_COMPONENT
                        , component.getClass().getSimpleName()
                        , this.getClass().getSimpleName()
                        , component1.getId()));
            }
        }
        components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        int index = -1;
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).getClass().getSimpleName().equals(componentType)) {
                index = i;
                break;
            }
        }
        if (components.isEmpty() || index == -1) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT, componentType, this.getClass()
                    .getSimpleName(), getId()));
        }
        return components.remove(index);
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        for (int i = 0; i < peripherals.size(); i++) {
            if (peripherals.get(i) == peripheral) {
                throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL, peripheral.getClass().getSimpleName()
                        , getClass().getSimpleName(), getId()));
            }
        }
        peripherals.add(peripheral);
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        int index = -1;

        for (int i = 0; i < peripherals.size(); i++) {
            if (peripherals.get(i).getClass().getSimpleName().equals(peripheralType)) {
                index = i;
                break;
            }
        }
        if (peripherals.size() == 0) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL, peripheralType, this.getClass().getSimpleName(), this.getId()));
        }
        return this.peripherals.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(System.lineSeparator());

        sb.append(String.format(" " + COMPUTER_COMPONENTS_TO_STRING, components.size()));
        sb.append(System.lineSeparator());
        for (Component component : components) {
            sb.append("  ").append(component.toString()).append(System.lineSeparator());
        }

        sb.append(String.format(" " + COMPUTER_PERIPHERALS_TO_STRING, peripherals.size(), getOverallPerformance()));
        sb.append(System.lineSeparator());

        for (Peripheral peripheral : peripherals) {
            sb.append("  ").append(peripheral.toString()).append(System.lineSeparator());
        }

        return sb.toString().trim();

    }
}
