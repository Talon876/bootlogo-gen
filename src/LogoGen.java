import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogoGen {

    private final List<ICodeGenerator> commands;
    private final Random random = new Random();

    public LogoGen() {
        commands = new ArrayList<>();

        custom("/**\r\n" + " * Draws a boot logo then clears the screen.\r\n"
                + " * Takes the FrameBufferInfo address as the first argument (r0)\r\n" + " * Returns nothing.\r\n"
                + " */\r\n" + ".globl DisplayBootLogo\r\n" + "DisplayBootLogo:\r\n" + "    push {lr}\r\n"
                + "    fbInfoAddr .req r4\r\n" + "    mov fbInfoAddr, r0\r\n" + "    bl SetGraphicsAddress\r\n"
                + "    .unreq fbInfoAddr\r\n" + "\r\n" + "\r\n" + "    mov r0,#0b0000011111100000 //green\r\n"
                + "    bl SetForeColor\r\n" + "    ldr r0,=960\r\n" + "    ldr r1,=540\r\n" + "    bl DrawPixel\r\n"
                + "\r\n" + "    //everything below is generated");
        sleep(6000);

        for (int i = 0; i < 3; i++) {
            ZigZag zigZag = new ZigZag(0, 540, 1919, 540, 25, 60);
            setColor(HighColor.getRandomColor(1, 64, 1));
            zigZag(zigZag);
            sleep(175);
            zigZag = new ZigZag(1919, 540, 0, 540, 25, 60);
            setColor(HighColor.getRandomColor(1, 64, 1));
            zigZag(zigZag);
            sleep(175);
        }

        int offsX = -65;
        int offsY = 110;
        setColor(HighColor.RED);
        letter('U', 960 + offsX, 540 + offsY);
        sleep(250);
        setColor(HighColor.GREEN);
        letter('W', 976 + offsX, 540 + offsY);
        letter('O', 976 + 16 + offsX, 540 + offsY);
        letter('T', 976 + 32 + offsX, 540 + offsY);
        sleep(250);
        setColor(HighColor.BLUE);
        letter('m', 976 + 32 + 16 + offsX, 540 + offsY);
        letter('8', 976 + 64 + offsX, 540 + offsY);
        sleep(250);
        setColor(HighColor.WHITE);
        letter('?', 976 + 64 + 16 + offsX, 540 + offsY);

        int width = 1920 / 12;
        for (int i = 1; i <= 12; i++) {
            setColor(new HighColor(0, (int) Utils.lerp(0f, 63f, i / 12f), 0));
            drawLine((i - 1) * width, 712, i * width, 712);
            drawLine((i - 1) * width, 713, i * width, 713);
            //            drawLine((i - 1) * width, 714, i * width, 714);
            //sleep(300);
        }

        custom("pop {pc}");
        dumpCommands();
    }

    public void dumpCommands() {
        for (ICodeGenerator gen : commands) {
            System.out.println(gen.getCode());
        }
    }

    public static void main(String[] args) {
        new LogoGen();
    }

    public void sleep(int milliseconds) {
        commands.add(new Sleep(milliseconds * 1000));
    }

    public void setColor(HighColor color) {
        commands.add(new HighColor(color));
    }

    public void setColor(int red, int green, int blue) {
        commands.add(new HighColor(red, green, blue));
    }

    public void drawLine(int x0, int y0, int x1, int y1) {
        commands.add(new Line(x0, y0, x1, y1));
    }

    public void lineChain(LineChain chain) {
        commands.add(chain);
    }

    public void zigZag(ZigZag zigZag) {
        commands.add(zigZag);
    }

    public void custom(String cmd) {
        commands.add(new CustomCommand(cmd));
    }

    public void letter(char character, int x, int y) {
        commands.add(new Text(character, x, y));
    }

}
