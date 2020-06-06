package liebman.met.neo;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MetControllerTest {

    @Test
    public void callDepartment() {
        //given
        MetService service = mock(MetService.class);
        JLabel label = mock(JLabel.class);
        BasicArrowButton arrowButton = mock(BasicArrowButton.class);
        JComboBox comboBox = mock(JComboBox.class);
        MetFrame frame = mock(MetFrame.class);
        MetController controller = new MetController(service, label, label, label, arrowButton, arrowButton,
                comboBox, frame);
        Call<MetFeed.DepartmentList> call = mock(Call.class);
        doReturn(call).when(service).getDepartment();

        //when
        controller.callDepartment();

        //then
        verify(service).getDepartment();
        verify(service.getDepartment()).enqueue(any());
    }

    @Test
    public void getCallDepartment() {
        //given
        MetService service = mock(MetService.class);
        JLabel label = mock(JLabel.class);
        BasicArrowButton arrowButton = mock(BasicArrowButton.class);
        JComboBox comboBox = mock(JComboBox.class);
        MetFrame frame = mock(MetFrame.class);
        MetController controller = new MetController(service, label, label, label, arrowButton, arrowButton,
                comboBox, frame);
        Call<MetFeed.DepartmentList> call = mock(Call.class);
        Response<MetFeed.DepartmentList> response = mock(Response.class);

        MetFeed.DepartmentList list = new MetFeed.DepartmentList();
        MetFeed.DepartmentList.Departments dep = new MetFeed.DepartmentList.Departments();

        dep.departmentId = 1;
        dep.displayName = "Department 1";

        List<MetFeed.DepartmentList.Departments> depsList = new ArrayList<>();
        depsList.add(dep);

        list.departments = depsList;
        doReturn(list).when(response).body();

        //when
        controller.getCallDepartment().onResponse(call, response);

        //then
        verify(comboBox).addItem(list.departments.get(0));
    }

    @Test
    public void callObjects() {
        //given
        MetService service = mock(MetService.class);
        Call<MetFeed> call = mock(Call.class);
        JLabel label = mock(JLabel.class);
        BasicArrowButton arrowButton = mock(BasicArrowButton.class);
        JComboBox comboBox = mock(JComboBox.class);
        MetFrame frame = mock(MetFrame.class);
        MetController controller = new MetController(service, label, label, label, arrowButton, arrowButton,
                comboBox, frame);
        int id = 1;

        doReturn(call).when(service).getObject(id);


        //when
        controller.callObjects(id);

        //then
        verify(call).enqueue(any());
    }

    @Test
    public void getCallObjects() {
        //if
        MetService service = mock(MetService.class);
        JLabel label = mock(JLabel.class);
        BasicArrowButton arrowButton = mock(BasicArrowButton.class);
        JComboBox comboBox = mock(JComboBox.class);
        MetFrame frame = mock(MetFrame.class);
        MetController controller = new MetController(service, label, label, label, arrowButton, arrowButton,
                comboBox, frame);
        Call<MetFeed.DepObjList> objCall = mock(Call.class);
        Call<MetFeed> metadataCall = mock(Call.class);
        Response<MetFeed.DepObjList> response = mock(Response.class);
        ArrayList<Integer> mockList = mock(ArrayList.class);


        MetFeed.DepObjList list = new MetFeed.DepObjList();
        ArrayList<Integer> objList = new ArrayList<>();
        objList.add(333);
        list.objectIDs = objList;
        doReturn(list).when(response).body();
        doReturn(metadataCall).when(service).getMetadata(objList.get(0));

        //when
        controller.getCallObjects().onResponse(objCall, response);

        //then
        verify(mockList).equals(objList);
    }

    @Test
    public void callMetadata() {
        //if
        MetService service = mock(MetService.class);
        Call<MetFeed> call = mock(Call.class);
        JLabel label = mock(JLabel.class);
        BasicArrowButton arrowButton = mock(BasicArrowButton.class);
        JComboBox comboBox = mock(JComboBox.class);
        MetFrame frame = mock(MetFrame.class);
        MetController controller = new MetController(service, label, label, label, arrowButton, arrowButton,
                comboBox, frame);

        int index = 1;
        ArrayList<Integer> objectIDs = mock(ArrayList.class);
        controller.listIds = objectIDs;
        doReturn(3).when(controller.listIds).size();
        doReturn(1).when(controller.listIds).get(1);
        doReturn(call).when(service).getMetadata(index);

        //when
        controller.callMetadata(index);

        //then
        verify(call).enqueue(any());
    }

    @Test
    public void getCallMetadata() {
        //if
        MetService service = mock(MetService.class);
        JLabel label = mock(JLabel.class);
        BasicArrowButton arrowButton = mock(BasicArrowButton.class);
        JComboBox comboBox = mock(JComboBox.class);
        MetFrame frame = mock(MetFrame.class);
        MetController controller = new MetController(service, label, label, label, arrowButton, arrowButton,
                comboBox, frame);
        Call<MetFeed> call = mock(Call.class);
        Response<MetFeed> response = mock(Response.class);

        MetFeed obj = new MetFeed();
        obj.primaryImage = "";
        obj.objectDate = "date";
        obj.title = "title";

        doReturn(obj).when(response).body();

        //when
        controller.getCallMetadata().onResponse(call, response);

        //then
        verify(label).setText("~no image found~");
        verify(label).setText(obj.objectDate);
        verify(label).setText(obj.title);

    }
}
