//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2020.02.02 於 10:43:57 PM CST 
//


package uia.csv.in.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CsvType complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="CsvType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rowKey" type="{http://csv.uia/in/xml}SqlColumnType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="const" type="{http://csv.uia/in/xml}ParameterType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="plan" type="{http://csv.uia/in/xml}PlanType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="firstRow" type="{http://www.w3.org/2001/XMLSchema}int" default="0" />
 *       &lt;attribute name="columnCount" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CsvType", propOrder = {
    "rowKey",
    "_const",
    "plan"
})
public class CsvType {

    protected List<SqlColumnType> rowKey;
    @XmlElement(name = "const")
    protected List<ParameterType> _const;
    protected List<PlanType> plan;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "firstRow")
    protected Integer firstRow;
    @XmlAttribute(name = "columnCount", required = true)
    protected int columnCount;

    /**
     * Gets the value of the rowKey property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rowKey property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRowKey().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SqlColumnType }
     * 
     * 
     */
    public List<SqlColumnType> getRowKey() {
        if (rowKey == null) {
            rowKey = new ArrayList<SqlColumnType>();
        }
        return this.rowKey;
    }

    /**
     * Gets the value of the const property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the const property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConst().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterType }
     * 
     * 
     */
    public List<ParameterType> getConst() {
        if (_const == null) {
            _const = new ArrayList<ParameterType>();
        }
        return this._const;
    }

    /**
     * Gets the value of the plan property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plan property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlan().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlanType }
     * 
     * 
     */
    public List<PlanType> getPlan() {
        if (plan == null) {
            plan = new ArrayList<PlanType>();
        }
        return this.plan;
    }

    /**
     * 取得 name 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 設定 name 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 取得 firstRow 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getFirstRow() {
        if (firstRow == null) {
            return  0;
        } else {
            return firstRow;
        }
    }

    /**
     * 設定 firstRow 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFirstRow(Integer value) {
        this.firstRow = value;
    }

    /**
     * 取得 columnCount 特性的值.
     * 
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * 設定 columnCount 特性的值.
     * 
     */
    public void setColumnCount(int value) {
        this.columnCount = value;
    }

}
