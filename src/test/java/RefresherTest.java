import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class RefresherTest {
    private static Refresher refresher;

    @BeforeClass
    public static void createNewRefresher(){
        refresher = Refresher.getRefresher();
    }

    @Test
    public void newInstanceOfRefresherShouldBeTheSame(){
        Refresher refresher1 = Refresher.getRefresher();
        Refresher refresher2 = Refresher.getRefresher();

        assertEquals(refresher1, refresher2);
    }

    @Test
    public void refresherGetterOfCompanyNameShouldGetsValue() throws NoSuchFieldException, IllegalAccessException {
        Field field = refresher.getClass().getDeclaredField("myCompanyName");
        field.setAccessible(true);
        field.set(refresher, "Microsoft");
        String result = refresher.getMyCompanyName();

        assertEquals("field wasn't retrieved properly", result, "Microsoft");
    }

    @Test
    public void refresherGetterOfCompanyOwnerShouldGetsValue() throws NoSuchFieldException, IllegalAccessException {
        Field field = refresher.getClass().getDeclaredField("myCompanyOwner");
        field.setAccessible(true);
        field.set(refresher, "Bill");
        String result = refresher.getMyCompanyOwner();

        assertEquals("field wasn't retrieved properly", result, "Bill");
    }

    @Test
    public void refresherGetterOfAgeShouldGetsValue() throws NoSuchFieldException, IllegalAccessException {
        Field field = refresher.getClass().getDeclaredField("age");
        field.setAccessible(true);
        field.set(refresher, 5);
        int result = refresher.getAge();

        assertEquals("field wasn't retrieved properly", result, 5);
    }

    @Test(timeout = 5000)
    public void doRefreshShouldReturnFasterThanFiveSeconds(){
        refresher.doRefresh("src/main/resources/fields.properties");
    }
}
