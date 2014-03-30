import java.util.Random;

public class HighColor implements ICodeGenerator {

    public static final HighColor WHITE = new HighColor(31, 63, 31);
    public static final HighColor BLACK = new HighColor(0, 0, 0);
    public static final HighColor RED = new HighColor(31, 0, 0);
    public static final HighColor GREEN = new HighColor(0, 63, 0);
    public static final HighColor BLUE = new HighColor(0, 0, 31);


    private int red;
    private int green;
    private int blue;

    public HighColor(int red, int green, int blue) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    public HighColor(HighColor color) {
        setRed(color.getRed());
        setGreen(color.getGreen());
        setBlue(color.getBlue());
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        if (red > 31) {
            throw new IllegalArgumentException("red must be < 32, was " + red);
        }
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        if (green > 63) {
            throw new IllegalArgumentException("green must be < 64, was " + green);
        }
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        if (blue > 31) {
            throw new IllegalArgumentException("blue must be < 32, was " + blue);
        }
        this.blue = blue;
    }

    public static HighColor getRandomColor() {
        return getRandomColor(32, 64, 32);
    }

    public static HighColor getRandomColor(int redMax, int greenMax, int blueMax) {
        Random r = new Random();
        return new HighColor(r.nextInt(redMax), r.nextInt(greenMax), r.nextInt(blueMax));
    }

    public int getColor() {
        int r = red << 11;
        int g = green << 5;

        return r | g | blue;
    }

    @Override
    public String getCode() {
        StringBuilder code = new StringBuilder();

        String binaryString = String.format("%16s", Integer.toBinaryString(getColor())).replace(' ', '0');

        code.append(String.format("%s0b%16s", getQuickestCommand("r0"), binaryString));
        code.append("\n");
        code.append("bl SetForeColor");
        code.append("\n");
        return code.toString();
    }

    private String getQuickestCommand(String register) {
        return getColor() > 4096 ? String.format("ldr %s,=", register) : String.format("mov %s,#", register);
    }

}
