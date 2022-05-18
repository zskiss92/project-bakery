package controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class BakeryControllerTest {

    BakeryController tester = new BakeryController();

    @BeforeAll
    static void setUp() {
    }

    @AfterAll
    static void tearDown() {
    }


    @Test
    void increaseAmountOfIngredientByFiveTest() {
        Assertions.assertEquals(6, tester.increaseAmountOfIngredientByFive(1));
        Assertions.assertEquals(7, tester.increaseAmountOfIngredientByFive(2));
        Assertions.assertEquals(8, tester.increaseAmountOfIngredientByFive(3));
        Assertions.assertEquals(5005, tester.increaseAmountOfIngredientByFive(5000));
        Assertions.assertEquals(893, tester.increaseAmountOfIngredientByFive(888));
    }

    @Test
    void multiplyByFiveTest() {
        Assertions.assertEquals(5, tester.multiplyByFive(1));
        Assertions.assertEquals(10, tester.multiplyByFive(2));
        Assertions.assertEquals(15, tester.multiplyByFive(3));
        Assertions.assertEquals(625, tester.multiplyByFive(125));
        Assertions.assertEquals(125, tester.multiplyByFive(25));
    }

    @Test
    void multiplyByTwoTest() {
        Assertions.assertEquals(2, tester.multiplyByTwo(1));
        Assertions.assertEquals(4, tester.multiplyByTwo(2));
        Assertions.assertEquals(6, tester.multiplyByTwo(3));
        Assertions.assertEquals(512, tester.multiplyByTwo(256));
        Assertions.assertEquals(2048, tester.multiplyByTwo(1024));
    }
}