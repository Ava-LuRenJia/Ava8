package view.examinee_frame;

import entity.AdmissionInfo;
import service.AdmissionInfoService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdmissionQueryFrame extends JFrame {
    public static AdmissionQueryFrame instance = new AdmissionQueryFrame();

    private JLabel resultLabel;
    private AdmissionInfoService admissionInfoService = new AdmissionInfoService();

    private AdmissionQueryFrame() {
        setTitle("录取查询");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        setupLayout();
        queryAndShowAdmissionStatus();

        setVisible(true);
    }

    private void initComponents() {
        resultLabel = new JLabel();
    }

    private void setupLayout() {
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        JPanel centerPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        contentPane.add(centerPanel, BorderLayout.CENTER);

        centerPanel.add(resultLabel);
    }

    public void queryAndShowAdmissionStatus() {
        List<AdmissionInfo> admissionInfos = admissionInfoService.list();
        StringBuilder resultText = new StringBuilder("<html><font size='5'>录取信息如下：<br>");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy - MM - dd HH:mm:ss");
        for (AdmissionInfo info : admissionInfos) {
            resultText.append("录取信息ID: ").append(info.getAdmissionId()).append("<br>");
            resultText.append("考生ID: ").append(info.getExamineeId()).append("<br>");
            resultText.append("是否被录取: ").append(info.isAdmitted()? "是" : "否").append("<br>");
            resultText.append("录取时间: ").append(sdf.format(info.getAdmissionTime())).append("<br>");
        }
        resultText.append("</font></html>");
        resultLabel.setText(resultText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdmissionQueryFrame.instance = new AdmissionQueryFrame();
        });
    }
}