package ru.yandex.practicum.delivery;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    public ArrayList<T> l = new ArrayList<>();
    private int maxWeight;
    private int currentWeight = 0;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel){
        if(currentWeight + parcel.weight > maxWeight){
            System.out.println("Невозможно добавить посылку в коробку. Превышен максимальный вес коробки.");
        } else {
            l.add(parcel);
            currentWeight += parcel.weight;
            System.out.println("Посылка добавлена в коробку.");
        }
    }

    public ArrayList<T> getAllParcels(){
        return l;
    }
}
