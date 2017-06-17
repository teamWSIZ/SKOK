package bank.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

public class AServiceTest {
    AService testee;


    @Before
    public void setUp() throws Exception {
        testee = new AService();
    }

    @Test
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
    public void oneElementArrayTest() {
        int[] a = new int[]{1};

        assertThat(testee.findIndex(a, 5)).isEqualTo(0);
        assertThat(testee.findIndex(a, 8)).isEqualTo(0);
        assertThat(testee.findIndex(a, 20)).isEqualTo(0);
    }
}