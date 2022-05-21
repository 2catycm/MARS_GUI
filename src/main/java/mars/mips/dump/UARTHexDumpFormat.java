package mars.mips.dump;

import mars.Globals;
import mars.mips.hardware.AddressErrorException;
import mars.mips.hardware.Memory;
import mars.simulator.Exceptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class UARTHexDumpFormat extends AbstractDumpFormat {
    public UARTHexDumpFormat() {
        super("UART hex format", "HEX", "Written as Vivado COE Hex Memory File", "coe");
    }

    /**
     * Write MIPS memory contents according to the Memory Initialization File
     * (MIF) specification.
     *
     * @param file         File in which to store MIPS memory contents.
     * @param firstAddress first (lowest) memory address to dump.  In bytes but
     *                     must be on word boundary.
     * @param lastAddress  last (highest) memory address to dump.  In bytes but
     *                     must be on word boundary.  Will dump the word that starts at this address.
     * @throws AddressErrorException if firstAddress is invalid or not on a word boundary.
     * @throws IOException           if error occurs during file output.
     */
    public void dumpMemoryRange(File file, int firstAddress, int lastAddress)
            throws AddressErrorException, IOException {
        try (PrintStream out = new PrintStream(new FileOutputStream(file))) {
            out.print("03040000");
            for (int address = 0; address < Memory.textLimitAddress; address += Memory.WORD_LENGTH_BYTES) {
                Integer temp = Globals.memory.getRawWordOrNull(address);
                if (temp == null) {
                    out.print("00000000");
                    continue;
                }
                StringBuilder string = new StringBuilder(Integer.toHexString(temp));
                while (string.length() < 8) {
                    string.insert(0, '0');
                }
                out.print(string);
            }
            for (int i = Memory.textLimitAddress; i < Memory.kernelTextBaseAddress; i += Memory.WORD_LENGTH_BYTES) {
                out.print("00000000");
            }
            for (int i = Memory.kernelTextBaseAddress; i < Memory.kernelTextLimitAddress; i += Memory.WORD_LENGTH_BYTES) {
                Integer temp = Globals.memory.getRawWordOrNull(i);
                if (temp == null) {
                    out.print("00000000");
                    continue;
                }
                StringBuilder string = new StringBuilder(Integer.toHexString(temp));
                while (string.length() < 8) {
                    string.insert(0, '0');
                }
                out.print(string);
            }
            for (int i = 0; i < 3073; i++) {
                out.print("00000000");
            }
            for (int i = 0; i < Memory.dataSegmentBaseAddress; i += Memory.WORD_LENGTH_BYTES) {
                out.print("00000000");
            }
            for (int address = Memory.dataSegmentBaseAddress; address < Memory.dataSegmentLimitAddress; address += Memory.WORD_LENGTH_BYTES) {
                Integer temp = Globals.memory.getRawWordOrNull(address);
                if (temp == null) {
                    out.print("00000000");
                    continue;
                }
                StringBuilder string = new StringBuilder(Integer.toHexString(temp));
                while (string.length() < 8) {
                    string.insert(0, '0');
                }
                out.print(string);
            }
        }

    }
}
