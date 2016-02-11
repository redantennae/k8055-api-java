package be.thepieterdc.k8055;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pieter on 10/02/16.
 */
public class BoardInterfaceTest {

    @Test
    public void testConstruct() {
        BoardInterface b = new BoardInterface();
    }

    @Test
    public void testInstance() {
        assertNotNull(BoardInterface.instance());
    }
}