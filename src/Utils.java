import java.util.Random;

public class Utils {

    public static Random random = new Random();

    public static String setArgument(String argument, int value) {
        return String.format("ldr %s,=%d\n", argument, value);
    }

    public static int randomRange(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    public static float lerp(float value1, float value2, float amount) {
        if (value1 == value2) {
            return value2;
        } else {
            float result = value1 + (value2 - value1) * amount;
            return result;
        }
    }
}
