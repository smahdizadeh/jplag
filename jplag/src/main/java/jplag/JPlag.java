package jplag;

import jplag.options.CommandLineOptions;

public class JPlag {
    public static void main(String[] args) {
        if (args.length == 0)
            CommandLineOptions.usage();
        else {
            try {
                Program program = new Program();
                CommandLineOptions options = new CommandLineOptions(args);
                System.out.println("initialize ok");
                program.run(options);
            } catch (ExitException exception) {
                System.out.println("Error: " + exception.getReport());
                System.exit(1);
            }
        }
    }
}
