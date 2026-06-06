package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {
    private int timeToLive;
    public static final int PERISHABLE_COST = 3;

    public PerishableParcel(int sendDay, String deliveryAddress, int weight, String description, int timeToLive) {
        super(sendDay, deliveryAddress, weight, description);
        this.timeToLive = timeToLive;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    @Override
    protected int getBaseCost() {
        return PERISHABLE_COST;
    }

    public static boolean isExpired(int currentDay, int sendDay, int timeToLive){
        return (sendDay + timeToLive) < currentDay;
    }
}
