package bank.service;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Przykładowe testy wykorzystujące bibliotekę AssertJ
 */


public class AServiceTest {
    AService testee;


    @Before
    public void setUp() throws Exception {
        testee = new AService();
    }

    @Test   //przykład testu
    public void aaaTest() {
        assertThat(1).isEqualTo(1);
    }

    @Test
    public void simpleFindTest() {
        int[] a = new int[]{0, 2, 8, 15, 16};

        assertThat(testee.findIndex(a, 5)).isEqualTo(2);
        assertThat(testee.findIndex(a, 8)).isEqualTo(2);
        assertThat(testee.findIndex(a, 20)).isEqualTo(4);
        assertThat(testee.findIndex(a, -20)).isEqualTo(0);
        assertThat(testee.findIndex(a, 0)).isEqualTo(0);
    }

    @Test
    public void alphaTest() {
        int[] a = new int[]{0, 2, 4};

        assertThat(testee.findIndex(a, 1)).isEqualTo(1);
        assertThat(testee.findIndex(a, 2)).isEqualTo(1);
        assertThat(testee.findIndex(a, 3)).isEqualTo(2);
    }

    @Test
    public void oneElementArrayTest() {
        int[] a = new int[]{1};

        assertThat(testee.findIndex(a, 5)).isEqualTo(0);
        assertThat(testee.findIndex(a, 8)).isEqualTo(0);
        assertThat(testee.findIndex(a, 20)).isEqualTo(0);
    }

    //////////////////
    //square


    @Test
    public void simpleSquareTest() {

        //kwadrat o wierzchłkach (0,0), (10,0), (10,10), (0,10)
        assertThat(testee.isInsideSquare(0, 10, 10, 5, 5)).isEqualTo(true);
        assertThat(testee.isInsideSquare(0, 10, 10, 10, 0)).isEqualTo(true);
        assertThat(testee.isInsideSquare(0, 10, 10, 100, 100)).isEqualTo(false);

        assertThat(testee.isInsideSquare(0, 1, 1, 0, 0)).isEqualTo(true);
        assertThat(testee.isInsideSquare(0, 1, 1, 1, 2)).isEqualTo(false);

    }

    //////////////////
    //window
    @Test
    public void simpleWindowTest() {
        int[] a = new int[]{0, 1, 4, 2, 1, 5};

        assertThat(testee.isWindowOk(a, 2, 6)).isEqualTo(true);
        assertThat(testee.isWindowOk(a, 3, 6)).isEqualTo(false);
        assertThat(testee.isWindowOk(a, 3, 8)).isEqualTo(true);
    }
}