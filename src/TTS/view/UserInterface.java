package TTS.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class UserInterface {

    /**
     * Кнопка на окне интерфейса
     */
    private JButton m_runPlayButton;
    /**
     * Поля ввода на окне интерфейса
     */
    private JTextField m_inputField;
    /**
     * Лейбел на окне интерфейса
     */
    private JLabel m_logLabel;


    /**
     * Создает окно пользовательского интерфейса
     *
     * @param windowWidth    ширина окна интерфейса
     * @param windowHeight   высота окна интерфейса
     * @param buttonName     текст на кнопке
     * @param inputFieldSize размер поля ввода
     * @param labelText      текст на лейбле
     */
    public UserInterface(int windowWidth, int windowHeight, String buttonName, int inputFieldSize, String labelText) {

        // настройки окна
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new GridBagLayout());
        window.setSize(new Dimension(windowWidth, windowHeight));

        m_runPlayButton = new JButton(buttonName);
        m_inputField = new JTextField(inputFieldSize);
        m_logLabel = new JLabel(labelText);

        window.add(m_runPlayButton);
        window.add(m_inputField);
        window.add(m_logLabel);

        window.setVisible(true);

        // настройки расположения объектов окна
        window.add(m_inputField, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        window.add(m_runPlayButton, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        window.add(m_logLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
    }

    /**
     * Позволяет навесить экшен на кнопку интерфейса
     *
     * @param action дейстивие после нажатия на кнопку
     */
    public void addActionOnButton(ActionListener action) {
        if (action == null)
            return;

        m_runPlayButton.addActionListener(action);
    }

    /**
     * Получить текст с поля ввода
     *
     * @return возвращает текст в поле вода
     */
    public String getInputFieldText() {
        if (m_inputField == null)
            return "";
        else {
            return m_inputField.getText();
        }
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
