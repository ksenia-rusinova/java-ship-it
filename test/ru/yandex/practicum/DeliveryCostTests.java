package ru.yandex.practicum;

import org.junit.jupiter.api.*;
import ru.yandex.practicum.delivery.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.yandex.practicum.delivery.Parcel.*;

public class DeliveryCostTests {
    private List<Parcel> allParcels;

    @BeforeEach
    public void createListForParcels() {
        allParcels = new ArrayList<>();
    }

    @Test
    public void checkCalculateCostsForTypeStandard() {
        StandardParcel sp = new StandardParcel(2, "Адрес 1", 12, "Стандартная посылка 1");
        allParcels.add(sp);

        int total = 0;
        for(Parcel parcel : allParcels){
            total += parcel.calculateDeliveryCost(parcel.weight);
        }

        assertEquals(sp.weight * STANDARD_COST, total);
    }

    @Test
    public void checkCalculateCostsForTypeFragile() {
        FragileParcel fp = new FragileParcel(3, "Адрес 1", 1, "Хрупкая посылка 1");
        allParcels.add(fp);

        int total = 0;
        for(Parcel parcel : allParcels){
            total += parcel.calculateDeliveryCost(parcel.weight);
        }

        assertEquals(fp.weight * FRAGILE_COST, total);
    }

    @Test
    public void checkCalculateCostsForTypePerishable() {
        PerishableParcel pp = new PerishableParcel(5, "Адрес 1", 10, "Скоропортящаяся посылка 1", 9);
        allParcels.add(pp);

        int total = 0;
        for(Parcel parcel : allParcels){
            total += parcel.calculateDeliveryCost(parcel.weight);
        }

        assertEquals(pp.weight * PERISHABLE_COST, total);
    }
}
