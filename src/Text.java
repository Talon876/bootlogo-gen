
public class Text implements ICodeGenerator {

    private char character;
    private int x;
    private int y;

    public Text(char c, int x, int y) {
        character = c;
        this.x = x;
        this.y = y;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String getCode() {
        StringBuilder code = new StringBuilder();

        code.append(Utils.setArgument("r0", character));
        code.append(Utils.setArgument("r1", x));
        code.append(Utils.setArgument("r2", y));
        code.append("bl DrawCharacter");

        return code.toString();
    }
}
