public class ZigZag implements ICodeGenerator {

    LineChain zigzag;

    public ZigZag(int x0, int y0, int x1, int y1, int numSegments, int jitterness) {
        zigzag = new LineChain(x0, y0);
        int xDistance = x1 - x0;
        int yDistance = y1 - y0;
        int xDelta = xDistance / numSegments;
        int yDelta = yDistance / numSegments;
        for (int i = 1; i < numSegments; i++) {
            zigzag.to(x0 + i * xDelta + Utils.randomRange(-jitterness, jitterness),
                    y0 + i * yDelta + Utils.randomRange(-jitterness, jitterness));
        }
        zigzag.to(x1, y1);
    }

    @Override
    public String getCode() {
        return zigzag.getCode();
    }

}
