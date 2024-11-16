package org.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MathUtilsTest {

    MathUtils mathUtils;

    @BeforeAll
    void beforeAllInit() {
        System.out.println("This needs to run before all");
    }

    @BeforeEach
    void init() {
        mathUtils = new MathUtils();
    }

    @AfterEach
    void cleanUp() {
        System.out.println("Cleaning up");
    }

    @Nested
    class AddTest {
        @Test
        @DisplayName("Testing add method for positive")
        void testAddPositive() {
            assertEquals(2, mathUtils.add(1, 1), "The add method should add 2 numbers");
        }

        @Test
        @DisplayName("Testing add method for negative")
        void testAddNegative() {
            assertEquals(-2, mathUtils.add(-1, -1), "The add method should add 2 numbers");
        }
    }

    @Test
    @DisplayName("Multiply method")
    void testMultiply() {
        assertAll(
                () -> assertEquals(4, mathUtils.multiply(2,2), "should return the right product"),
                () -> assertEquals(0, mathUtils.multiply(2,0), "should return the right product"),
                () -> assertEquals(-2, mathUtils.multiply(2,-1), "should return the right product")
        );
    }

    @Test
    void testDivide() {
        boolean isServerUp = true;
        assumeTrue(isServerUp);
        assertThrows(ArithmeticException.class, () ->mathUtils.divide(1,0), "Divide by zero should throw");
    }

    @RepeatedTest(3)
    @EnabledOnOs(OS.WINDOWS)
    void testComputeCircleArea(RepetitionInfo repetitionInfo) {
        repetitionInfo.getCurrentRepetition();
        assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "Should return right circle area");
    }

    @Test
    @Disabled
    @DisplayName("TDD method")
    void testDisable() {
        fail("This test should be disabled");
    }

}
