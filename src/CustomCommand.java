public class CustomCommand implements ICodeGenerator {

    public final String command;

    public CustomCommand(String command) {
        this.command = command;
    }

    @Override
    public String getCode() {
        return command;
    }

}
