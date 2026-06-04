package ru.yandex.practicum;

import org.junit.jupiter.api.*;
import ru.yandex.practicum.delivery.PerishableParcel;

import static org.junit.jupiter.api.Assertions.*;
import static ru.yandex.practicum.delivery.PerishableParcel.*;

public class PerishableParcelTests {
    private PerishableParcel pp;

    @BeforeEach
    public void createParcel() {
        pp = new PerishableParcel(5, "Адрес 1", 10, "Скоропортящаяся посылка 1", 9);
    }

    @Test
    public void checkIfIsNotExpiredFalse() {
        boolean actual = isExpired(13, pp.sendDay, pp.timeToLive);
        assertFalse(actual);
    }

    @Test
    public void checkIfSendDayPlusTimeToLiveEqCurrDayFalse() {
        boolean actual = isExpired(14, pp.sendDay, pp.timeToLive);
        assertFalse(actual);
    }

    @Test
    public void checkIfIsExpiredTrue() {
        boolean actual = isExpired(15, pp.sendDay, pp.timeToLive);
        assertTrue(actual);
    }
}
