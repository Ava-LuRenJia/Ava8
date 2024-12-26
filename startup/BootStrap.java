package startup;

import view.Loginframe.LoginFrame;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;


public class BootStrap {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
      
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                LoginFrame.instance.setVisible(true);
            }
        });

    }
}
