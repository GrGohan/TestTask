import org.apache.log4j.Logger;

import java.io.FileNotFoundException;

public class MainClass {
    private static final Logger log = Logger.getLogger(MainClass.class);

    public static void main(String[] args) {
//        if (args.length > 0) {
//            try {
//                Refresher refresher = Refresher.getRefresher();
//                System.out.println("Property File: " + args[0] + "\n");
//                refresher.printFields();
//                refresher.doRefresh(args[0]);
//                System.out.println("-------------");
//                refresher.printFields();
//            } catch (Exception e) {
//               log.error(e);
//            }
//        }

        String path = "src/main/resources/fields.properties";
        Refresher refresher = Refresher.getRefresher();
        refresher.printFields();
        refresher.doRefresh(path);
        System.out.println("----------");
        refresher.printFields();
    }
}
