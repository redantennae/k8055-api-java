package be.thepieterdc.k8055;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * BoardInterface
 *
 * @author Pieter De Clercq
 */
public class BoardInterface {
    /**
     * Board interface
     */
    public interface Board extends Library {
        int OpenDevice(long addr);
        void CloseDevice();
    }

    /**
     * The board.
     */
    private static Board board;

    /**
     * @return Board the instance of the board
     */
    public static Board instance() {
        if(board == null) {
            System.setProperty("jna.library.path", ".");
            board = (Board) Native.loadLibrary("k8055", Board.class);
        }
        return board;
    }
}
