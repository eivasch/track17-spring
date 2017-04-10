package track.lessons.lesson4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import track.container.Container;
import track.container.JsonConfigReader;
import track.container.beans.Car;
import track.container.beans.Engine;
import track.container.beans.Gear;
import track.container.config.Bean;
import track.container.config.InvalidConfigurationException;

import java.io.File;
import java.util.List;

public class ContainerTest {
    static JsonConfigReader reader;
    static File file;


    @BeforeClass
    public static void init() {
        reader = new JsonConfigReader();
        file = new File("/home/pufelka/Tehnotrack/java/src/main/resources/config.json");
    }

    @Test
    public void readJsonTest() {
        try {
            List<Bean> beans = reader.parseBeans(file);
            Assert.assertTrue(true);
        } catch (InvalidConfigurationException exception) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void setGear() throws Exception {
        JsonConfigReader reader = new JsonConfigReader();
        File file = new File("/home/pufelka/Tehnotrack/java/src/main/resources/config.json");
        List<Bean> beans = reader.parseBeans(file);
        Container container = new Container(beans);
        Gear gear = (Gear) container.getByClass("track.container.beans.Gear");
        Assert.assertEquals(gear.getCount(), 6);
        Gear gear1 = (Gear) container.getById("gearBean");
        Assert.assertEquals(gear1.getCount(), 6);
    }

    @Test
    public void setEngine() throws Exception {
        List<Bean> beans = reader.parseBeans(file);
        Container container = new Container(beans);
        Engine engine = (Engine) container.getByClass("track.container.beans.Engine");

        Assert.assertEquals(engine.getPower(), 200);
        Engine engine1 = (Engine) container.getById("engineBean");
        Assert.assertEquals(engine1.getPower(), 200);
        engine1.setPower(100);
        Assert.assertEquals(engine1.getPower(), 100);
    }

    @Test
    public void setCar() throws Exception {
        List<Bean> beans = reader.parseBeans(file);
        Container container = new Container(beans);
        Car car = (Car) container.getById("carBean");

        Assert.assertEquals(car.getGear().getCount(), 6);
        Assert.assertEquals(car.getEngine().getPower(), 200);
    }
}
