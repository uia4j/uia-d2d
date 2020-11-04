package uia.d2d;

import org.junit.Assert;
import org.junit.Test;

import uia.utils.JaxbUtils;

public class JaxbTest {

    @Test
    public void testSheet() throws Exception {
        JaxbUtils<uia.d2d.sheet.xml.WorkspaceType> jaxb = new JaxbUtils<>(
                uia.d2d.sheet.xml.WorkspaceType.class,
                "workspace",
                "http://d2d.uia/sheet/xml",
                "uia.d2d.sheet.xml");

        uia.d2d.sheet.xml.WorkspaceType workspace = jaxb.fromXml(JaxbTest.class.getResourceAsStream("/sheet_test.xml"));
        Assert.assertEquals(2, workspace.getSheetSpace().getSheet().size());
        Assert.assertEquals(3, workspace.getSheetSpace().getSheet().get(0).getPropertyOrObjectOrList().size());
        Assert.assertEquals("deptName", workspace.getSheetSpace().getSheet().get(0).getPropertyOrObjectOrList().get(0).getName());
        Assert.assertEquals("deptManager", workspace.getSheetSpace().getSheet().get(0).getPropertyOrObjectOrList().get(1).getName());
        Assert.assertEquals("empList", workspace.getSheetSpace().getSheet().get(0).getPropertyOrObjectOrList().get(2).getName());
    }
}
