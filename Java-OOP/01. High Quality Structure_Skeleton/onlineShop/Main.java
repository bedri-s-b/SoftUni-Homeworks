package onlineShop;

import onlineShop.core.EngineImpl;
import onlineShop.core.interfaces.Engine;
import onlineShop.models.products.components.BaseComponent;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.computers.BaseComputer;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.Laptop;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
