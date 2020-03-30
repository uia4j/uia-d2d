//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.03.25 at 10:10:51 PM CST 
//


package uia.d2d.in.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uia.d2d.in.xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Workspace_QNAME = new QName("http://d2d.uia/in/xml", "workspace");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uia.d2d.in.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WorkspaceType }
     * 
     */
    public WorkspaceType createWorkspaceType() {
        return new WorkspaceType();
    }

    /**
     * Create an instance of {@link ParameterType }
     * 
     */
    public ParameterType createParameterType() {
        return new ParameterType();
    }

    /**
     * Create an instance of {@link CsvColumnType }
     * 
     */
    public CsvColumnType createCsvColumnType() {
        return new CsvColumnType();
    }

    /**
     * Create an instance of {@link PostType }
     * 
     */
    public PostType createPostType() {
        return new PostType();
    }

    /**
     * Create an instance of {@link CsvType }
     * 
     */
    public CsvType createCsvType() {
        return new CsvType();
    }

    /**
     * Create an instance of {@link SCConvType }
     * 
     */
    public SCConvType createSCConvType() {
        return new SCConvType();
    }

    /**
     * Create an instance of {@link SCConvSpaceType }
     * 
     */
    public SCConvSpaceType createSCConvSpaceType() {
        return new SCConvSpaceType();
    }

    /**
     * Create an instance of {@link CCConvSpaceType }
     * 
     */
    public CCConvSpaceType createCCConvSpaceType() {
        return new CCConvSpaceType();
    }

    /**
     * Create an instance of {@link SqlColumnsType }
     * 
     */
    public SqlColumnsType createSqlColumnsType() {
        return new SqlColumnsType();
    }

    /**
     * Create an instance of {@link SqlColumnType }
     * 
     */
    public SqlColumnType createSqlColumnType() {
        return new SqlColumnType();
    }

    /**
     * Create an instance of {@link CCConvType }
     * 
     */
    public CCConvType createCCConvType() {
        return new CCConvType();
    }

    /**
     * Create an instance of {@link CsvSpaceType }
     * 
     */
    public CsvSpaceType createCsvSpaceType() {
        return new CsvSpaceType();
    }

    /**
     * Create an instance of {@link FilterType }
     * 
     */
    public FilterType createFilterType() {
        return new FilterType();
    }

    /**
     * Create an instance of {@link SimpleJobType }
     * 
     */
    public SimpleJobType createSimpleJobType() {
        return new SimpleJobType();
    }

    /**
     * Create an instance of {@link ConstSpaceType }
     * 
     */
    public ConstSpaceType createConstSpaceType() {
        return new ConstSpaceType();
    }

    /**
     * Create an instance of {@link PlanType }
     * 
     */
    public PlanType createPlanType() {
        return new PlanType();
    }

    /**
     * Create an instance of {@link SheetJobType }
     * 
     */
    public SheetJobType createSheetJobType() {
        return new SheetJobType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WorkspaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://d2d.uia/in/xml", name = "workspace")
    public JAXBElement<WorkspaceType> createWorkspace(WorkspaceType value) {
        return new JAXBElement<WorkspaceType>(_Workspace_QNAME, WorkspaceType.class, null, value);
    }

}