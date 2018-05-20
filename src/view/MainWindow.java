package view;

import controller.ParsString;
import controller.PlaySound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class MainWindow {

    public MainWindow() {
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final PlaySound playSound = new PlaySound();
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setSize(new Dimension(600, 200));
        JButton gButton = new JButton("Do");
        final JTextField jTextFieldFirst = new JTextField(50);
        final JLabel jLabelFirst = new JLabel("");
        mainFrame.add(jTextFieldFirst);
        mainFrame.add(jLabelFirst);
        mainFrame.add(gButton);
        mainFrame.setVisible(true);

        mainFrame.add(jTextFieldFirst, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        mainFrame.add(gButton, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        mainFrame.add(jLabelFirst, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        gButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String prs = jTextFieldFirst.getText();

                ParsString parsString = new ParsString();

                List<String> result = parsString.getPhoneme(prs);
                String res = "";

                for (int i = 0; i < result.size(); i++) {
                    res = res + result.get(i);
                    System.out.println(result.get(i));
                }
                jLabelFirst.setText(res);
                try {
                    playSound.playSound(result);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
