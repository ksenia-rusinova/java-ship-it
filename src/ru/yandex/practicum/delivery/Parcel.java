package ru.yandex.practicum.delivery;

public abstract class Parcel {
    protected String description;
    public int weight;
    protected String deliveryAddress;
    public int sendDay;

    public static final int STANDARD_COST = 2;
    public static final int PERISHABLE_COST = 3;
    public static final int FRAGILE_COST = 4;

    public Parcel(int sendDay, String deliveryAddress, int weight, String description) {
        this.sendDay = sendDay;
        this.deliveryAddress = deliveryAddress;
        this.weight = weight;
        this.description = description;
    }

    protected void packageItem(String description) {
        System.out.println("Посылка <" + description + "> упакована");
    }

    protected void deliver(String description, String deliveryAddress) {
        System.out.println("Посылка <" + description + "> доставлена по адресу " + deliveryAddress);
    }

    public int calculateDeliveryCost(int weight) {
        return weight * getBaseCost();
    }

    protected abstract int getBaseCost();
}
