import java.util.Stack;

public class LineChain implements ICodeGenerator {

    private final Stack<Line> lines;
    private final int startX;
    private final int startY;

    public LineChain(int x, int y) {
        lines = new Stack<>();
        startX = x;
        startY = y;
    }

    public LineChain to(int destX, int destY) {
        if (lines.isEmpty()) {
            lines.push(new Line(startX, startY, destX, destY));
        }
        Line top = lines.peek();
        lines.push(new Line(top.x1, top.y1, destX, destY));
        return this;
    }

    @Override
    public String getCode() {
        StringBuilder code = new StringBuilder();

        for (Line line : lines) {
            code.append(line.getCode());
            code.append("\n");
        }

        return code.toString();
    }
}
