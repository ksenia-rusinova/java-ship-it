package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;

public class ParcelBoxTests {
    private ParcelBox<StandardParcel> standardBox;

    @BeforeEach
    public void createParcel() {
        standardBox = new ParcelBox<>(50);
    }

    @Test
    public void checkIfMaxWeightIsNotExceededAddParcel() {
        StandardParcel sp = new StandardParcel(2, "Адрес 1", 50, "Стандартная посылка 1");
        standardBox.addParcel(sp);

        assertFalse(standardBox.getParcels().isEmpty());
    }

    @Test
    public void checkIfMaxWeightIsExceededNotAddParcel() {
        StandardParcel sp = new StandardParcel(2, "Адрес 1", 51, "Стандартная посылка 1");
        standardBox.addParcel(sp);

        assertTrue(standardBox.getParcels().isEmpty());
    }
}
