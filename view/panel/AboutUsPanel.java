package view.panel;

import javax.swing.*;
import java.awt.*;


public class AboutUsPanel extends JPanel {

    //单例模式
    public static AboutUsPanel instance = new AboutUsPanel();

    public AboutUsPanel() {
        JLabel aboutUs = new JLabel();
        aboutUs.setIcon(new ImageIcon(AboutUsPanel.class.getResource("/images/Hebut_Icon.jpg")));
        //aboutUs.setSize(icon.getIconWidth(), icon.getIconHeight());
        //aboutUs.setBackground(Color.darkGray);
        add(aboutUs, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.add(AboutUsPanel.instance);
        frame.setVisible(true);
    }
}
