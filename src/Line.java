public class Line implements ICodeGenerator {

    public int x0, y0, x1, y1;

    public Line(int x0, int y0, int x1, int y1) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
    }

    @Override
    public String getCode() {
        StringBuilder code = new StringBuilder();

        code.append(buildArgumentSetter(x0, "r0"));
        code.append("\n");
        code.append(buildArgumentSetter(y0, "r1"));
        code.append("\n");
        code.append(buildArgumentSetter(x1, "r2"));
        code.append("\n");
        code.append(buildArgumentSetter(y1, "r3"));
        code.append("\n");
        code.append("bl DrawLine");
        code.append("\n");
        return code.toString();
    }

    private String buildArgumentSetter(int value, String register) {
        return value > 1 ? String.format("ldr %s,=%d", register, value) : String.format("mov %s,#%d", register,
                value);
    }

}
