package mars.mips.dump;

import mars.Globals;
import mars.mips.hardware.AddressErrorException;
import mars.mips.hardware.Memory;
import mars.simulator.Exceptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class COEHexDumpFormat extends AbstractDumpFormat {
    public COEHexDumpFormat() {
        super("Vivado COE hex format", "HEX", "Written as Vivado COE Hex Memory File", "coe");
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
        if (lastAddress > 32768 * Memory.WORD_LENGTH_BYTES)
            throw new AddressErrorException("file larger than 32768 instructions", Exceptions.ADDRESS_EXCEPTION_STORE, lastAddress);
        try (PrintStream out = new PrintStream(new FileOutputStream(file))) {
            out.println("memory_initialization_radix = 16;");
            out.println("memory_initialization_vector =");
            System.out.println(firstAddress);
            System.out.println(lastAddress);
            for (int i = 0; i < firstAddress; i += Memory.WORD_LENGTH_BYTES) {
                out.println("00000000,");
            }
            for (int address = firstAddress; address <= lastAddress; address += Memory.WORD_LENGTH_BYTES) {
                Integer temp = Globals.memory.getRawWordOrNull(address);
                if (temp == null)
                    break;
                StringBuilder string = new StringBuilder(Integer.toHexString(temp));
                while (string.length() < 8) {
                    string.insert(0, '0');
                }
                string.append(',');
                out.println(string);
            }
            for (int i = lastAddress + Memory.WORD_LENGTH_BYTES; i < 32767 * Memory.WORD_LENGTH_BYTES; i += Memory.WORD_LENGTH_BYTES) {
                out.println("00000000,");
            }
            out.println("00000000;");
        }

    }
}
