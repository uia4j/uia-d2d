//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2019.08.21 於 11:43:44 AM CST 
//


package uia.csv.in.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PkType complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="PkType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="conv" type="{http://www.w3.org/2001/XMLSchema}string" default="String" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PkType")
public class PkType {

    @XmlAttribute(name = "conv")
    protected String conv;

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
