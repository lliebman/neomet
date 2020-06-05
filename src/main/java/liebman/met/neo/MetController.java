package liebman.met.neo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class MetController{

    private MetService service;
    JLabel imageLabel;
    JLabel titleLabel;
    JLabel dateLabel;
    BasicArrowButton nextButton;
    BasicArrowButton previousButton;
    JComboBox departmentsComboBox;
    MetFrame frame;

    ArrayList<Integer> listIds;

    public MetController(MetService service, JLabel imageLabel,
                         JLabel titleLabel,
                         JLabel dateLabel,
                         BasicArrowButton nextButton,
                         BasicArrowButton previousButton,
                         JComboBox departmentsComboBox, MetFrame frame) {
        this.service = service;
        this.imageLabel = imageLabel;
        this.titleLabel = titleLabel;
        this.dateLabel = dateLabel;
        this.previousButton = previousButton;
        this.nextButton = nextButton;
        this.departmentsComboBox = departmentsComboBox;
        this.frame = frame;
    }

    public void callDepartment() {
        service.getDepartment().enqueue(getCallDepartment());
    }

    public Callback<MetFeed.DepartmentList> getCallDepartment(){
        return new Callback<MetFeed.DepartmentList>(){
            @Override
            public void onResponse(Call<MetFeed.DepartmentList> call, Response<MetFeed.DepartmentList> response) {
                MetFeed.DepartmentList feed = response.body();
                for(MetFeed.DepartmentList.Departments department : feed.departments)
                {
                    departmentsComboBox.addItem(department);
                }

            }

            @Override
            public void onFailure(Call<MetFeed.DepartmentList> call, Throwable t) {
                t.printStackTrace();
            }
        };

    }

    public void callObjects(int depID) {
        System.out.println("depID is: " + depID);
        service.getObject(depID).enqueue(getCallObjects());
    }

    public Callback<MetFeed.DepObjList> getCallObjects(){
        return new Callback<MetFeed.DepObjList>(){
            @Override
            public void onResponse(Call<MetFeed.DepObjList> call, Response<MetFeed.DepObjList> response) {
                MetFeed.DepObjList feed = response.body();
                listIds = feed.objectIDs;
                frame.setTotalObjects(listIds.size());
                System.out.println("last object#: " + listIds.get(listIds.size()-1));
                callMetadata(0);
            }

            @Override
            public void onFailure(Call<MetFeed.DepObjList> call, Throwable t) {
                t.printStackTrace();
            }
        };

    }

    public void callMetadata(int index) {
        System.out.println("index#:" + index);
//        System.out.println("metadata for object#:" + listIds.get(index));
        service.getMetadata(listIds.get(index)).enqueue(getCallMetadata());
    }

    public Callback<MetFeed> getCallMetadata(){
        return new Callback<MetFeed>(){
            @Override
            public void onResponse(Call<MetFeed> call, Response<MetFeed> response) {
                MetFeed feed = response.body();
                System.out.println("in metadata");
                System.out.println("title: " + String.valueOf(feed.title));
                if (feed.primaryImage.equals("")){
                    imageLabel.setIcon(null);
                    imageLabel.setText("~no image found~");
                }
                else {
                    try {
                        imageLabel.setText("");
                        URL url = new URL(feed.primaryImage);
                        Image image = ImageIO.read(url);
                        image = image.getScaledInstance(250, 250,
                                Image.SCALE_SMOOTH);
                        imageLabel.setIcon(new ImageIcon(image));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                titleLabel.setText(String.valueOf(feed.title));
                dateLabel.setText(String.valueOf(feed.objectDate));
            }

            @Override
            public void onFailure(Call<MetFeed> call, Throwable t) {
                t.printStackTrace();
            }
        };

    }
}
