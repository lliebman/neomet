package liebman.met.neo;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class MetFrame extends JFrame {

    private JPanel topPanel;
    JComboBox departmentsComboBox;

    private JPanel rightPanel;
    private JLabel imageLabel;
    private JLabel titleLabel;
    private JLabel dateLabel;

    private JPanel bottomPanel;
    private BasicArrowButton nextButton;
    private BasicArrowButton previousButton;

    MetController controller;
    MetService service;

    int index;
    int totalObjects;
    int depID;

    public MetFrame() {
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Met Archives Search Tool");
        setLayout(new BorderLayout());

        topPanel = new JPanel();
        departmentsComboBox = new JComboBox();
        topPanel.add(departmentsComboBox);
        departmentsComboBox.addActionListener(ActionEvent -> {getObject();});

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        imageLabel = new JLabel();
        titleLabel = new JLabel();
        dateLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(imageLabel);
        rightPanel.add(titleLabel);
        rightPanel.add(dateLabel);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        nextButton = new BasicArrowButton(SwingConstants.EAST);
        previousButton = new BasicArrowButton(SwingConstants.WEST);
        nextButton.addActionListener(ActionEvent -> {nextIndex();});
        previousButton.addActionListener(ActionEvent -> {previousIndex();});
        bottomPanel.add(previousButton);
        bottomPanel.add(nextButton);

        add(topPanel, BorderLayout.PAGE_START);
        add(rightPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);

        service = new MetFactory().getInstance();
        controller = new MetController(service, imageLabel, titleLabel, dateLabel, nextButton, previousButton,
                departmentsComboBox, this);
        controller.callDepartment();
    }

    private void getObject(){
        MetFeed.DepartmentList.Departments thisDep = (MetFeed.DepartmentList.Departments)
                departmentsComboBox.getSelectedItem();
        depID = thisDep.departmentId;
        index = 0;
        controller.callObjects(depID);
    }

    protected void setTotalObjects(int total){
        this.totalObjects = total;
    }

    private void nextIndex(){
        if (index == totalObjects - 1){
            index = 0;
        }
        else {
        index++;
        }
        controller.callMetadata(index);
    }

    private void previousIndex(){
        if (index == 0) {
            index = totalObjects - 1;
        }
        else{
            index--;
        }
        controller.callMetadata(index);
    }

    public static void main(String[] args) {
        new MetFrame().setVisible(true);
    }


}
