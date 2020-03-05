//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2020.02.02 於 10:43:57 PM CST 
//


package uia.csv.in.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WorkspaceType complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="WorkspaceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="csvSpace" type="{http://csv.uia/in/xml}CsvSpaceType"/>
 *         &lt;element name="scConvSpace" type="{http://csv.uia/in/xml}SCConvSpaceType"/>
 *         &lt;element name="ccConvSpace" type="{http://csv.uia/in/xml}CCConvSpaceType"/>
 *         &lt;element name="constSpace" type="{http://csv.uia/in/xml}ConstSpaceType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkspaceType", propOrder = {
    "csvSpace",
    "scConvSpace",
    "ccConvSpace",
    "constSpace"
})
public class WorkspaceType {

    @XmlElement(required = true)
    protected CsvSpaceType csvSpace;
    @XmlElement(required = true)
    protected SCConvSpaceType scConvSpace;
    @XmlElement(required = true)
    protected CCConvSpaceType ccConvSpace;
    @XmlElement(required = true)
    protected ConstSpaceType constSpace;

    /**
     * 取得 csvSpace 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link CsvSpaceType }
     *     
     */
    public CsvSpaceType getCsvSpace() {
        return csvSpace;
    }

    /**
     * 設定 csvSpace 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link CsvSpaceType }
     *     
     */
    public void setCsvSpace(CsvSpaceType value) {
        this.csvSpace = value;
    }

    /**
     * 取得 scConvSpace 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link SCConvSpaceType }
     *     
     */
    public SCConvSpaceType getScConvSpace() {
        return scConvSpace;
    }

    /**
     * 設定 scConvSpace 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link SCConvSpaceType }
     *     
     */
    public void setScConvSpace(SCConvSpaceType value) {
        this.scConvSpace = value;
    }

    /**
     * 取得 ccConvSpace 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link CCConvSpaceType }
     *     
     */
    public CCConvSpaceType getCcConvSpace() {
        return ccConvSpace;
    }

    /**
     * 設定 ccConvSpace 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link CCConvSpaceType }
     *     
     */
    public void setCcConvSpace(CCConvSpaceType value) {
        this.ccConvSpace = value;
    }

    /**
     * 取得 constSpace 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link ConstSpaceType }
     *     
     */
    public ConstSpaceType getConstSpace() {
        return constSpace;
    }

    /**
     * 設定 constSpace 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link ConstSpaceType }
     *     
     */
    public void setConstSpace(ConstSpaceType value) {
        this.constSpace = value;
    }

}
