package liebman.met.neo;

import java.util.ArrayList;
import java.util.List;

public class MetFeed {

    static class DepartmentList{
        List<Departments> departments;


        static class Departments {
            int departmentId;
            String displayName;

            @Override
            public String toString() {
                return displayName;
            }
        }
    }

    static class DepObjList{
        ArrayList<Integer> objectIDs;
    }

    String primaryImage;

    String title;

    String objectDate;

}
