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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SqlColumnType complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="SqlColumnType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="csvColumn" type="{http://csv.uia/in/xml}CsvColumnType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="argument" type="{http://csv.uia/in/xml}ParameterType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}int" default="0" />
 *       &lt;attribute name="nullable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="empty2Null" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="conv" type="{http://www.w3.org/2001/XMLSchema}string" default="Varchar" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SqlColumnType", propOrder = {
    "csvColumn",
    "argument"
})
public class SqlColumnType {

    protected List<CsvColumnType> csvColumn;
    protected List<ParameterType> argument;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "length")
    protected Integer length;
    @XmlAttribute(name = "nullable")
    protected Boolean nullable;
    @XmlAttribute(name = "empty2Null")
    protected Boolean empty2Null;
    @XmlAttribute(name = "conv")
    protected String conv;

    /**
     * Gets the value of the csvColumn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the csvColumn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCsvColumn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CsvColumnType }
     * 
     * 
     */
    public List<CsvColumnType> getCsvColumn() {
        if (csvColumn == null) {
            csvColumn = new ArrayList<CsvColumnType>();
        }
        return this.csvColumn;
    }

    /**
     * Gets the value of the argument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the argument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArgument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterType }
     * 
     * 
     */
    public List<ParameterType> getArgument() {
        if (argument == null) {
            argument = new ArrayList<ParameterType>();
        }
        return this.argument;
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
     * 取得 length 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getLength() {
        if (length == null) {
            return  0;
        } else {
            return length;
        }
    }

    /**
     * 設定 length 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLength(Integer value) {
        this.length = value;
    }

    /**
     * 取得 nullable 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isNullable() {
        if (nullable == null) {
            return true;
        } else {
            return nullable;
        }
    }

    /**
     * 設定 nullable 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNullable(Boolean value) {
        this.nullable = value;
    }

    /**
     * 取得 empty2Null 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isEmpty2Null() {
        if (empty2Null == null) {
            return true;
        } else {
            return empty2Null;
        }
    }

    /**
     * 設定 empty2Null 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEmpty2Null(Boolean value) {
        this.empty2Null = value;
    }

    /**
     * 取得 conv 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConv() {
        if (conv == null) {
            return "Varchar";
        } else {
            return conv;
        }
    }

    /**
     * 設定 conv 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConv(String value) {
        this.conv = value;
    }

}
