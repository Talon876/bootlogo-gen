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
        sleep(7000);
        setColor(HighColor.GREEN);
        drawLine(0, 0, 1919, 1079);
        setColor(HighColor.BLUE);
        drawLine(1919, 0, 0, 1079);
        setColor(HighColor.RED);

        LineChain zigZag = new LineChain(0, 540);
        for (int i = 1; i < 19; i++) {
            zigZag.to(Math.min(1919, 101 * i), 540 + randomRange(-50, 50));
        }
        lineChain(zigZag);

        sleep(1000);
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

    public void custom(String cmd) {
        commands.add(new CustomCommand(cmd));
    }

    public int randomRange(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
