import be.thepieterdc.k8055.BoardInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Pieter De Clercq
 */
public class BoardInterfaceTest {

    private BoardInterface boardInterface;

    @Before
    public void setUp() throws Exception {
        this.boardInterface = new BoardInterface();
    }

    @After
    public void tearDown() throws Exception {
        this.boardInterface = null;
    }

    @Test
    public void testEquals() throws Exception {
        BoardInterface another = new BoardInterface();
        assertEquals(another, this.boardInterface);
    }

    @Test
    public void testHashCode() throws Exception {
        BoardInterface another = new BoardInterface();
        assertEquals(another.hashCode(), this.boardInterface.hashCode());
    }

    @Test
    public void testInstance() throws Exception {
        BoardInterface.Board anInstance = BoardInterface.instance();
        BoardInterface.Board anotherInstance = BoardInterface.instance();
        assertEquals(anotherInstance, anInstance);
    }
}