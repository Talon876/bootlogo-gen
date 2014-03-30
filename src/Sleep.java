public class Sleep implements ICodeGenerator {

    private final int delayInMicroseconds;

    public Sleep(int delay) {
        delayInMicroseconds = delay;
    }

    @Override
    public String getCode() {
        StringBuilder code = new StringBuilder();

        code.append(getQuickestCommand("r0"));
        code.append("\n");
        code.append("bl Sleep");
        code.append("\n");
        return code.toString();
    }

    private String getQuickestCommand(String register) {
        return delayInMicroseconds > 4096 ? String.format("ldr %s,=%d", register, delayInMicroseconds) : String.format(
                "mov %s,#%d", register, delayInMicroseconds);
    }
}
