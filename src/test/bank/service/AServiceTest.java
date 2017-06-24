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

    @Test
    public void otherWindowTest() {
        int[] a = new int[]{0, 1};

        assertThat(testee.isWindowOk(a, 1, 1)).isEqualTo(true);
        assertThat(testee.isWindowOk(a, 1, 0)).isEqualTo(false);
        assertThat(testee.isWindowOk(a, 2, 1)).isEqualTo(true);
        assertThat(testee.isWindowOk(a, 2, 0)).isEqualTo(false);
    }

    @Test
    public void sequenceWindowTest() {
        int[] a = new int[]{0, 1, 2, 3, 4, 5};
        assertThat(testee.isWindowOk(a, 1, 10)).isEqualTo(true);
        assertThat(testee.isWindowOk(a, 2, 10)).isEqualTo(true);
        assertThat(testee.isWindowOk(a, 3, 10)).isEqualTo(false);
        assertThat(testee.isWindowOk(a, 4, 10)).isEqualTo(false);
    }

    // Colisions


    @Test
    public void simpleCollisionsTest() {
        assertThat(testee.collidesWith(0, 1, 1, 1, 1, 1.01)).isEqualTo(true);
        assertThat(testee.collidesWith(0, 1, 100, 1, 1, 1.01)).isEqualTo(false);
        assertThat(testee.collidesWith(0, 1, 1, 2, 1, 1.4143)).isEqualTo(true); //odległość to dokładnie sqrt(2)=1.41421356
        assertThat(testee.collidesWith(0, 1, 1, 2, 1, 1.4140)).isEqualTo(false);
    }

    @Test
    public void simpleStoperTest() {
        assertThat(testee.differenceInSeconds(0, 0, 1, 10)).isEqualTo(70);
        assertThat(testee.differenceInSeconds(1, 5, 1, 10)).isEqualTo(5);
        assertThat(testee.differenceInSeconds(10, 0, 5, 0)).isEqualTo(300);
    }
}