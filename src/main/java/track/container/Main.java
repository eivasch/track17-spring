package track.container;

import track.container.beans.Car;
import track.container.config.Bean;
import track.container.config.ConfigReader;
import track.container.config.InvalidConfigurationException;

import java.io.File;
import java.util.List;

/**
 *
 */
public class Main {

    public static void main(String[] args) throws Exception {

        /*

        ПРИМЕР ИСПОЛЬЗОВАНИЯ

         */

//        // При чтении нужно обработать исключение
        JsonConfigReader reader = new JsonConfigReader();
        File file = new File("src/main/resources/config.json");
        List<Bean> beans;
        beans = reader.parseBeans(file);
        Container container = new Container(beans);
        Car car = (Car) container.getByClass("track.container.beans.Car");
        car = (Car) container.getById("carBean");
        System.out.println(car.toString());
    }
}
