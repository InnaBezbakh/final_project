package hooks;

import driverManager.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Before
    public void initDriver() {
        DriverManager.setUpDriver();
    }
    @After
    public void quiteBrowser() {
        DriverManager.quiteDriver();
    }
}

