import javax.swing.*;
import java.io.IOException;

public class Periodic {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new main_frame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
