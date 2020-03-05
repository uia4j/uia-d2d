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
 * <p>CsvColumnType complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="CsvColumnType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="argument" type="{http://csv.uia/in/xml}ParameterType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="index" type="{http://www.w3.org/2001/XMLSchema}int" default="-1" />
 *       &lt;attribute name="conv" type="{http://www.w3.org/2001/XMLSchema}string" default="String" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CsvColumnType", propOrder = {
    "argument"
})
public class CsvColumnType {

    protected List<ParameterType> argument;
    @XmlAttribute(name = "index")
    protected Integer index;
    @XmlAttribute(name = "conv")
    protected String conv;

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
     * 取得 index 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getIndex() {
        if (index == null) {
            return -1;
        } else {
            return index;
        }
    }

    /**
     * 設定 index 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIndex(Integer value) {
        this.index = value;
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
            return "String";
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
