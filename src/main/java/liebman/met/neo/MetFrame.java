package liebman.met.neo;

import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class MetFrame extends JFrame {

    private JPanel leftPanel;
    JComboBox departmentsComboBox;

    private JPanel rightPanel;
    private JLabel imageLabel;
    private JLabel titleLabel;
    private JLabel dateLabel;

    private JPanel bottomPanel;
    private BasicArrowButton nextButton;
    private BasicArrowButton previousButton;

    MetController controller;

    int depID;
    int index;

    public MetFrame() {
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Met Archives Search Tool");
        setLayout(new BorderLayout());

        controller.callDepartment();

        leftPanel = new JPanel();
        departmentsComboBox = new JComboBox();
        leftPanel.add(departmentsComboBox);
        departmentsComboBox.addActionListener(ActionEvent -> {getObject();});

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        imageLabel = new JLabel();
        titleLabel = new JLabel();
        dateLabel = new JLabel();
        rightPanel.add(imageLabel);
        rightPanel.add(titleLabel);
        rightPanel.add(dateLabel);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        nextButton = new BasicArrowButton(SwingConstants.EAST);
        previousButton = new BasicArrowButton(SwingConstants.WEST);
        bottomPanel.add(previousButton);
        bottomPanel.add(nextButton);

        add(leftPanel, BorderLayout.LINE_START);
        add(rightPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private void getObject(){
        controller.callObjects(Integer.toString(depID));
    }

    public static void main(String[] args) {
        new MetFrame().setVisible(true);
    }


}
