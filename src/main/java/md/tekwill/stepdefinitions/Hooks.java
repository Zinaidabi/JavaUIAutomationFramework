package md.tekwill.stepdefinitions;

import io.cucumber.java.*;
import md.tekwill.managers.DriverManager;

public class Hooks {
    @BeforeAll
    public static void beforeAll() {
        System.out.println("The execution of the test suite started");
    }

    @Before
    public void executeBeforeEachTest() {
        System.out.println("Test started");
    }

    @After
    public void executeAfterEachTest() {
        System.out.println("The test is completed");
        DriverManager.getInstance().quitTheDriver();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("The test execution finished ");
    }
}
