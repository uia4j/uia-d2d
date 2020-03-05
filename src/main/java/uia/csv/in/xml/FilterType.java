//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2020.02.02 於 10:43:57 PM CST 
//


package uia.csv.in.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>FilterType complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="FilterType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="csvColumnIndex" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="op" type="{http://www.w3.org/2001/XMLSchema}string" default="eq" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterType", propOrder = {
    "value"
})
public class FilterType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "csvColumnIndex")
    protected Integer csvColumnIndex;
    @XmlAttribute(name = "op")
    protected String op;

    /**
     * 取得 value 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * 設定 value 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 取得 csvColumnIndex 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCsvColumnIndex() {
        return csvColumnIndex;
    }

    /**
     * 設定 csvColumnIndex 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCsvColumnIndex(Integer value) {
        this.csvColumnIndex = value;
    }

    /**
     * 取得 op 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOp() {
        if (op == null) {
            return "eq";
        } else {
            return op;
        }
    }

    /**
     * 設定 op 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOp(String value) {
        this.op = value;
    }

}
