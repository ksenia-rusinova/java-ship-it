package ru.yandex.practicum.delivery;

public class StandardParcel extends Parcel {

    public StandardParcel(int sendDay, String deliveryAddress, int weight, String description) {
        super(sendDay, deliveryAddress, weight, description);
    }

    @Override
    protected int getBaseCost() {
        return STANDARD_COST;
    }
}
