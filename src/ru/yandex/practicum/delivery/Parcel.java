package ru.yandex.practicum.delivery;

public abstract class Parcel {
    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected int sendDay;

    public Parcel(int sendDay, String deliveryAddress, int weight, String description) {
        this.sendDay = sendDay;
        this.deliveryAddress = deliveryAddress;
        this.weight = weight;
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public int getSendDay() {
        return sendDay;
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
