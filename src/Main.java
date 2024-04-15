import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        GPanel panel = new GPanel();
        GFrame frame = new GFrame(panel);

        panel.startThread();

    }
}