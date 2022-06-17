package br.com.coffe.explorer.core.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

public class CoffeTest {

    @Test
    public void createCoffeTest() {
        Coffe coffe = new Coffe.Builder()
                .withDescription("Coffe test")
                .createdBy("test@test.com.br")
                .build();

        assertNotNull(coffe.getId());
        assertEquals(LocalDateTime.now(Clock.systemUTC()).truncatedTo(ChronoUnit.SECONDS),
                coffe.getCreationDateTime().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    public void createCoffeWithIdAndCreationDateTimeTest() {
        String id = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        Coffe coffe = new Coffe.Builder()
                .withId(id)
                .withCreationDateTime(now)
                .withDescription("Coffe test")
                .createdBy("test@test.com.br")
                .build();

        assertEquals(id, coffe.getId());
        assertEquals(now, coffe.getCreationDateTime());
    }

    @Test
    public void coffeIsImmutableTest() throws NoSuchMethodException {
        Constructor<Coffe> constructor = Coffe.class.getDeclaredConstructor(String.class, String.class, LocalDateTime.class, String.class, Flavor.class, List.class);
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

}