public class Line implements ICodeGenerator {

    public int x0, y0, x1, y1;

    public Line(int x0, int y0, int x1, int y1) {
        this.x0 = Utils.clamp(x0, 0, 1919);
        this.x1 = Utils.clamp(x1, 0, 1919);
        this.y0 = Utils.clamp(y0, 0, 1079);
        this.y1 = Utils.clamp(y1, 0, 1079);
    }

    @Override
    public String getCode() {
        StringBuilder code = new StringBuilder();

        code.append(Utils.setArgument("r0", x0));
        code.append(Utils.setArgument("r1", y0));
        code.append(Utils.setArgument("r2", x1));
        code.append(Utils.setArgument("r3", y1));
        code.append("bl DrawLine");
        code.append("\n");
        return code.toString();
    }

}
