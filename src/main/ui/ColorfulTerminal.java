package ui;

/**
 * Acts as a store for colors to use to make the terminal colorful.
 */
public class ColorfulTerminal {
    // Usage learnt from
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public final String ansiReset;
    public final String ansiBlack;
    public final String ansiYellow;
    public final String ansiBlue;
    public final String ansiPurple;
    public final String ansiCyan;
    public final String ansiWhite;
    public final String ansiGreen;

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> Initialises all the colors to their ansi value
     */
    public ColorfulTerminal() {
        ansiReset = "\u001B[0m";
        ansiBlack = "\u001B[30m";
        ansiYellow = "\u001B[33m";
        ansiBlue = "\u001B[34m";
        ansiPurple = "\u001B[35m";
        ansiCyan = "\u001B[36m";
        ansiWhite = "\u001B[37m";
        ansiGreen = "\u001B[32m";
    }

}
