package com.jacksonbarreto.pixelArt.tests;

import com.jacksonbarreto.pixelArt.Point2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Point2DTest {
    Point2D p1 = new Point2D(2.5, 3.75);
    Point2D p2 = new Point2D(2.5, 3.75);

    @Test
    void YouShouldntCreateOnePoint() {
        assertThrows(IllegalArgumentException.class, () -> new Point2D(null));
    }

    @Test
    void shouldHaveCoordinatesCorrect() {
        assertEquals(2.5, p1.getCoordinateX());
        assertEquals(3.75, p1.getCoordinateY());
    }

    @Test
    void shouldIncrementarBothCoordinate() {
        p1.offset(2, 1);
        assertEquals(4.5, p1.getCoordinateX());
        assertEquals(4.75, p1.getCoordinateY());
    }

    @Test
    void shouldIncrementOnlyOneCoordinate() {
        p1.offsetX(4.2);
        assertEquals(6.7, p1.getCoordinateX());
        p1.offsetY(1.3);
        assertEquals(5.05, p1.getCoordinateY());
    }

    @Test
    void shouldChangeCoordinate() {
        p1.setCoordinate(3.2, 5.7);
        assertEquals(3.2, p1.getCoordinateX());
        assertEquals(5.7, p1.getCoordinateY());
        p1.setCoordinateX(2.33);
        assertEquals(2.33, p1.getCoordinateX());
        p1.setCoordinateY(7.88);
        assertEquals(7.88, p1.getCoordinateY());
    }

    @Test
    void shouldBeEqual() {
        assertEquals(p1, p2);
        assertEquals(p2, p1);
        assertEquals(p2, p2.clone());
        assertEquals(p1, p2.clone());
    }

}
