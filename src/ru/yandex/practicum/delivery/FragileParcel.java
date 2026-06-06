package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable {
    public static final int FRAGILE_COST = 4;

    public FragileParcel(int sendDay, String deliveryAddress, int weight, String description) {
        super(sendDay, deliveryAddress, weight, description);
    }

    @Override
    protected void packageItem(String description) {
        System.out.println("Посылка <" + description + "> обёрнута в защитную плёнку");
        super.packageItem(description);
    }

    @Override
    protected int getBaseCost() {
        return FRAGILE_COST;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка <" + description + "> изменила местоположение на " + newLocation);
    }
}
