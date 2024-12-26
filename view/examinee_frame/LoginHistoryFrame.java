package view.examinee_frame;

import javax.swing.*;


public class LoginHistoryFrame extends JFrame {

    //单例模式
    public static LoginHistoryFrame instance = new LoginHistoryFrame();

    public LoginHistoryFrame() {
        //设置窗体信息
        setTitle("查询登录历史");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setResizable(false);






        //设置窗体可见性
        setVisible(true);
    }

    public static void main(String[] args) {

    }
}

