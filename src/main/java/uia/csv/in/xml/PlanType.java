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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PlanType complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="PlanType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sql" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="filter" type="{http://csv.uia/in/xml}FilterType" minOccurs="0"/>
 *         &lt;element name="sqlColumns" type="{http://csv.uia/in/xml}SqlColumnsType"/>
 *         &lt;element name="post" type="{http://csv.uia/in/xml}PostType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanType", propOrder = {
    "sql",
    "filter",
    "sqlColumns",
    "post"
})
public class PlanType {

    @XmlElement(required = true)
    protected String sql;
    protected FilterType filter;
    @XmlElement(required = true)
    protected SqlColumnsType sqlColumns;
    @XmlElement(required = true)
    protected PostType post;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * 取得 sql 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSql() {
        return sql;
    }

    /**
     * 設定 sql 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSql(String value) {
        this.sql = value;
    }

    /**
     * 取得 filter 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link FilterType }
     *     
     */
    public FilterType getFilter() {
        return filter;
    }

    /**
     * 設定 filter 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterType }
     *     
     */
    public void setFilter(FilterType value) {
        this.filter = value;
    }

    /**
     * 取得 sqlColumns 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link SqlColumnsType }
     *     
     */
    public SqlColumnsType getSqlColumns() {
        return sqlColumns;
    }

    /**
     * 設定 sqlColumns 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link SqlColumnsType }
     *     
     */
    public void setSqlColumns(SqlColumnsType value) {
        this.sqlColumns = value;
    }

    /**
     * 取得 post 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link PostType }
     *     
     */
    public PostType getPost() {
        return post;
    }

    /**
     * 設定 post 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link PostType }
     *     
     */
    public void setPost(PostType value) {
        this.post = value;
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

}
