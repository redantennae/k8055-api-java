package be.thepieterdc.k8055;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * Class that bridges Java to the K8055.
 *
 * @author Pieter De Clercq
 */
public class BoardInterface {

    public interface Board extends Library {
        int OpenDevice(long addr);
        void CloseDevice();
        int ReadAnalogChannel(long channel);
        void ReadAllAnalog(long data1, long data2);
        void OutputAnalogChannel(long channel, long data);
        void OutputAllAnalog(long data1, long data2);
        void ClearAnalogChannel(long channel);
        void ClearAllAnalog();
        void SetAnalogChannel(long channel);
        void SetAllAnalog();
        void WriteAllDigital(long data);
        void ClearDigitalChannel(long channel);
        void ClearAllDigital();
        void SetDigitalChannel(long channel);
        void SetAllDigital();
        boolean ReadDigitalChannel(long channel);
        int ReadAllDigital();
        void ResetCounter(long num);
        int ReadCounter(long num);
        void SetCounterDebounceTime(long num, long debounceTime);
    }

    private static Board board;

    @Override
    public boolean equals(Object other) {
        return other instanceof BoardInterface;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    /**
     * @return the singleton instance of the board
     */
    public static Board instance() {
        if(board == null) {
            System.setProperty("jna.library.path", ".");
            board = (Board) Native.loadLibrary("k8055", Board.class);
        }
        return board;
    }
}
