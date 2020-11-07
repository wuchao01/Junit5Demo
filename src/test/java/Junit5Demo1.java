import org.junit.jupiter.api.*;

public class Junit5Demo1 {
    @BeforeAll
    public static void beforeAll() {
        System.out.println("test beforeAll!");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("test beforeEach!");
    }

    @DisplayName("测试A用例")
    @Test
    public void putInA(){
        System.out.println("test A");
    }

    @DisplayName("测试B用例")
    @Test
    public void putInB(){
        System.out.println("test B");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("test afterEach!");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("test afterAll!");
    }
}
