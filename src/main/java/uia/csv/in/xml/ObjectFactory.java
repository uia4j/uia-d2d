//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2020.02.02 於 10:43:57 PM CST 
//


package uia.csv.in.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uia.csv.in.xml package. 
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

    private final static QName _Workspace_QNAME = new QName("http://csv.uia/in/xml", "workspace");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uia.csv.in.xml
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
     * Create an instance of {@link JobType }
     * 
     */
    public JobType createJobType() {
        return new JobType();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link WorkspaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://csv.uia/in/xml", name = "workspace")
    public JAXBElement<WorkspaceType> createWorkspace(WorkspaceType value) {
        return new JAXBElement<WorkspaceType>(_Workspace_QNAME, WorkspaceType.class, null, value);
    }

}
