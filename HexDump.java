import java.io.*;

/**
 * Example how to implement hexdump (basic approach) command line
 * in Linux system.
 */
public class HexDump {
    public static void main(String[] args) {
        final String filePath = args[0]; // File path
        try {
            hexdump(filePath);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void hexdump(String filePath) throws IOException{
        File f = new File(filePath);
        FileInputStream fis = new FileInputStream(f);

        int i = 0; // Counter.
        int numBytes = 0;
        int c;

        while((c = (int)fis.read()) != -1) {
            if(i != 0 && i % 0x10 == 0)
                System.out.println();
            if(i % 0x10 == 0) {
                System.out.printf("%08x\t", numBytes);
            }
            System.out.printf("%02x ", c);
            i++;
            numBytes++;
        }
        System.out.println();
        if(i % 0x10 != 0)
            System.out.printf("%08x\n", numBytes);

        fis.close();
    }
}
