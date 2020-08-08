import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class AddressTest {
    private static Address address;

    @BeforeClass
    public static void createNewAddress(){
        address = new Address();
    }

    @Test
    public void addressSetterOfStreetShouldSetsProperly() throws NoSuchFieldException, IllegalAccessException {
        address.setStreet("Pushkina");
        Field field = address.getClass().getDeclaredField("street");
        field.setAccessible(true);

        assertEquals("Fields didn't match", field.get(address), "Pushkina");
    }

    @Test
    public void addressGetterOfStreetShouldGetsValue() throws NoSuchFieldException, IllegalAccessException {
        Field field = address.getClass().getDeclaredField("street");
        field.setAccessible(true);
        field.set(address, "Pushkina");
        String result = address.getStreet();

        assertEquals("field wasn't retrieved properly", result, "Pushkina");
    }

    @Test
    public void addressSetterOfHomeShouldSetsProperly() throws NoSuchFieldException, IllegalAccessException {
        address.setHome("Kolotushkina");
        Field field = address.getClass().getDeclaredField("home");
        field.setAccessible(true);

        assertEquals("Fields didn't match", field.get(address), "Kolotushkina");
    }

    @Test
    public void addressGetterOfHomeShouldGetsValue() throws NoSuchFieldException, IllegalAccessException {
        final Field field = address.getClass().getDeclaredField("home");
        field.setAccessible(true);
        field.set(address, "Kolotushkina");
        String result = address.getHome();

        assertEquals("field wasn't retrieved properly", result, "Kolotushkina");
    }
}
