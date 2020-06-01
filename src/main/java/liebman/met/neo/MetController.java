package liebman.met.neo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

public class MetController{

    private MetService service;
    JLabel imageLabel;
    JLabel titleLabel;
    JLabel dateLabel;
    BasicArrowButton nextButton;
    BasicArrowButton previousButton;
    JComboBox departmentsComboBox;

    public MetController(MetService service, JLabel imageLabel,
                         JLabel titleLabel,
                         JLabel dateLabel,
                         BasicArrowButton nextButton,
                         BasicArrowButton previousButton,
                         JComboBox departmentsComboBox) {
        this.service = service;
        this.imageLabel = imageLabel;
        this.titleLabel = titleLabel;
        this.dateLabel = dateLabel;
        this.previousButton = previousButton;
        this.nextButton = nextButton;
        this.departmentsComboBox = departmentsComboBox;
    }

    public void callDepartment() {
        service.getDepartment().enqueue(getCallDepartment());
    }

    public Callback<MetFeed> getCallDepartment(){
        return new Callback<MetFeed>(){
            @Override
            public void onResponse(Call<MetFeed> call, Response<MetFeed> response) {

            }

            @Override
            public void onFailure(Call<MetFeed> call, Throwable t) {
                t.printStackTrace();
            }
        };

    }

    public void callObjects(String depID) {
        service.getObject(depID).enqueue(getCallObjects());
    }

    public Callback<MetFeed> getCallObjects(){
        return new Callback<MetFeed>(){
            @Override
            public void onResponse(Call<MetFeed> call, Response<MetFeed> response) {

            }

            @Override
            public void onFailure(Call<MetFeed> call, Throwable t) {
                t.printStackTrace();
            }
        };

    }

    public void callMetadata(String index) {
        service.getMetadata(index).enqueue(getCallMetadata());
    }

    public Callback<MetFeed> getCallMetadata(){
        return new Callback<MetFeed>(){
            @Override
            public void onResponse(Call<MetFeed> call, Response<MetFeed> response) {

            }

            @Override
            public void onFailure(Call<MetFeed> call, Throwable t) {
                t.printStackTrace();
            }
        };

    }
}
