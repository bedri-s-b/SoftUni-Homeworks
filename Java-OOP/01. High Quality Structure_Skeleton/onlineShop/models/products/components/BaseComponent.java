package onlineShop.models.products.components;

import onlineShop.models.products.BaseProduct;

import static onlineShop.common.constants.OutputMessages.COMPONENT_TO_STRING;

public abstract class BaseComponent extends BaseProduct implements Component{
    private int generation;

    protected BaseComponent(int id, String manufacturer, String model, double price, double overallPerformance,int generation) {
        super(id, manufacturer, model, price, overallPerformance);
        this.generation = generation;
    }


    @Override
    public int getGeneration() {
        return this.generation;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getManufacturer() {
        return super.getManufacturer();
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public double getPrice() {
        return super.getPrice() ;
    }

    @Override
    public double getOverallPerformance() {
        return super.getOverallPerformance();
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(COMPONENT_TO_STRING,getGeneration());
    }
}
