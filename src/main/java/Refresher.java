import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

public class Refresher {
    private static final Logger log = Logger.getLogger(Refresher.class);

    FileInputStream fileInputStream;
    Properties properties = new Properties();

    private static Refresher refresher;

    @Property(propertyName = "com.mycompany.name", defaultProp = "DefaultName")
    private String myCompanyName;
    @Property(propertyName = "com.mycompany.owner")
    private String myCompanyOwner;
    @Property(propertyName = "com.mycompany.number")
    private Integer age;
    @Property(propertyName = "com.mycompany.address", defaultProp = "{street: DefaultStreet, home: DefaultHome}")
    private static Address address;

    public Refresher(String myCompanyName, String myCompanyOwner, Integer age, Address address) {
        this.myCompanyName = myCompanyName;
        this.myCompanyOwner = myCompanyOwner;
        this.age = age;
        this.address = address;
    }

    public static Refresher getRefresher() {
        if (refresher == null) {
            address = new Address();
            address.setStreet("Street");
            address.setHome("Home");
            refresher = new Refresher("Microsoft", "Bill", 45, address);
        }
        return refresher;
    }

    public String getMyCompanyName() {
        return myCompanyName;
    }

    public String getMyCompanyOwner() {
        return myCompanyOwner;
    }

    public Integer getAge() {
        return age;
    }

    public static Address getAddress() {
        return address;
    }

    public synchronized void doRefresh(String path) {
        for (Field field : Refresher.class.getDeclaredFields()) {
            if (field.isAnnotationPresent(Property.class)) {
                String type = field.getType().toString();

                Pattern pattern = Pattern.compile("\\w+ (java.lang.)*(\\w+)");
                Matcher matcher = pattern.matcher(type);
                while (matcher.find()) {
                    type = matcher.group(2);
                }
                getProperties(field, type, path);
            }
        }
    }

    private void getProperties(Field field, String type, String path) {
        try {
            fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
            fileInputStream.close();

            String valueOfPropertyName = properties.getProperty(field.getAnnotation(Property.class).propertyName());
            String valueOfDefaultProperty = field.getAnnotation(Property.class).defaultProp();
            String currentValue = valueOfPropertyName;
            if (currentValue == null) {
                currentValue = valueOfDefaultProperty;
                if (currentValue.equals("NULL_VALUE")) {
                    setNullField(field);
                    return;
                }
            }

            switch (type) {
                case "String":
                    field.set(refresher, currentValue);
                    break;
                case "Integer":
                    field.set(refresher, Integer.parseInt(currentValue));
                    break;
                case "Double":
                    field.set(refresher, Double.parseDouble(currentValue));
                    break;
                case "Address":
                    setAddressFields(currentValue, field);
                    break;
                default:
                    setNullField(field);
                    throw new IllegalStateException("Unexpected value of type - " + type + ", for field - " + field);
            }
        }
        catch (FileNotFoundException e) {
            log.error("File not found exception");
        }
        catch (IllegalAccessException e) {
            log.error("Executable method does not have access to the definition of the specified class, field, method, or constructor" + "\n" + e);
        } catch (IllegalStateException e) {
            setNullField(field);
            log.error("Incorrect value: " + type + "\n" + e);
        } catch (NumberFormatException e) {
            setNullField(field);
            log.error("Failed to parse number for: " + field + "\n" + e);
        } catch (IOException e) {
            log.error(e);
        }
    }

    private void setAddressFields(String currentValue, Field field){
        Address address = new Address();

        Pattern pattern = Pattern.compile("\\{street: (.+), home: (.+)}");
        Matcher matcher = pattern.matcher(currentValue);
        while (matcher.find()) {
            address.setStreet(matcher.group(1));
            address.setHome(matcher.group(2));
        }
        try {
            field.set(refresher, address);
        } catch (IllegalAccessException e) {
            setNullField(field);
            log.error(e);
        }
    }

    private void setNullField(Field field){
        try {
            field.set(refresher, null);
        } catch (IllegalAccessException e) {
            log.error(e);
        }
    }

    public void printFields() {
        System.out.println("CompanyName - " + getMyCompanyName());
        System.out.println("CompanyOwner - " + getMyCompanyOwner());
        System.out.println("Age - " + getAge());
        System.out.println("Address - " + getAddress());
    }
}
