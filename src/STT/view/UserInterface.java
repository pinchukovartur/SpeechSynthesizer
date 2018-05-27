package STT.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class UserInterface {

    /**
     * Кнопка на окне интерфейса
     */
    private JButton m_runPlayButton;

    private JButton m_stopPlayButton;

    /**
     * Лейбел на окне интерфейса
     */
    private JLabel m_logLabel;


    /**
     * Создает окно пользовательского интерфейса
     *
     * @param windowWidth    ширина окна интерфейса
     * @param windowHeight   высота окна интерфейса
     * @param runButtonName  текст на кнопке run
     * @param stopButtonName текст на кнопке stop
     * @param labelText      текст на лейбле
     */
    public UserInterface(int windowWidth, int windowHeight, String runButtonName, String stopButtonName, String labelText) {

        // настройки окна
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new GridBagLayout());
        window.setSize(new Dimension(windowWidth, windowHeight));

        m_runPlayButton = new JButton(runButtonName);
        m_logLabel = new JLabel(labelText);
        m_stopPlayButton = new JButton(stopButtonName);

        window.add(m_runPlayButton);
        window.add(m_stopPlayButton);
        window.add(m_logLabel);

        window.setVisible(true);

        // настройки расположения объектов окна
        window.add(m_runPlayButton, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        window.add(m_logLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        window.add(m_stopPlayButton, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
    }

    /**
     * Позволяет навесить экшен на кнопку run интерфейса
     *
     * @param action дейстивие после нажатия на кнопку
     */
    public void addActionOnRunButton(ActionListener action) {
        if (action == null)
            return;

        m_runPlayButton.addActionListener(action);
    }

    /**
     * Позволяет навесить экшен на кнопку stop интерфейса
     *
     * @param action дейстивие после нажатия на кнопку
     */
    public void addActionOnStopButton(ActionListener action) {
        if (action == null)
            return;

        m_stopPlayButton.addActionListener(action);
    }

    /**
     * Задает текст лога на окне интерфейса
     *
     * @param text текст для лога
     */
    public void setLogText(String text) {
        if (text == null)
            return;

        m_logLabel.setText(text);
    }
}
