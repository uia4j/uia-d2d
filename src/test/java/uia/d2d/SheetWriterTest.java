package uia.d2d;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import uia.d2d.sheet.SheetWriter;

public class SheetWriterTest {

    @Test
    public void test() throws Exception {
        SheetWriter writer = new SheetWriter(JaxbTest.class.getResourceAsStream("/sheet_test.xml"));
        writer.write("Dept", sample1());
    }

    private static Dept sample1() {
        Dept dept = new Dept();
        dept.setDeptName("RD");
        dept.setDeptManager(new Emp("K", "4981", "M"));
        dept.getEmpList().add(new Emp("A", "1234", "F"));
        dept.getEmpList().add(new Emp("B", "5678", "F"));
        return dept;
    }

    public static class Dept {

        private String deptName;

        private Emp deptManager;

        private List<Emp> empList;

        public Dept() {
            this.deptManager = new Emp();
            this.empList = new ArrayList<>();
        }

        public String getDeptName() {
            return this.deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public Emp getDeptManager() {
            return this.deptManager;
        }

        public void setDeptManager(Emp deptManager) {
            this.deptManager = deptManager;
        }

        public List<Emp> getEmpList() {
            return this.empList;
        }

        public void setEmpList(List<Emp> empList) {
            this.empList = empList;
        }

    }

    public static class Emp {

        private String name;

        private String empNo;

        private String sex;

        public Emp() {
        }

        public Emp(String name, String empNo, String sex) {
            this.name = name;
            this.empNo = empNo;
            this.sex = sex;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmpNo() {
            return this.empNo;
        }

        public void setEmpNo(String empNo) {
            this.empNo = empNo;
        }

        public String getSex() {
            return this.sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

    }
}
