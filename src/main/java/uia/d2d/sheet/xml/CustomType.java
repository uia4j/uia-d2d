//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.08.04 at 12:41:19 PM CST 
//


package uia.d2d.sheet.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://d2d.uia/sheet/xml}CellType">
 *       &lt;sequence>
 *         &lt;element name="propertyRef" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="row" type="{http://www.w3.org/2001/XMLSchema}int" default="0" />
 *       &lt;attribute name="col" type="{http://www.w3.org/2001/XMLSchema}int" default="0" />
 *       &lt;attribute name="formatter" type="{http://www.w3.org/2001/XMLSchema}string" default="simple" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomType", propOrder = {
    "propertyRef"
})
public class CustomType
    extends CellType
{

    protected List<String> propertyRef;
    @XmlAttribute(name = "row")
    protected Integer row;
    @XmlAttribute(name = "col")
    protected Integer col;
    @XmlAttribute(name = "formatter")
    protected String formatter;

    /**
     * Gets the value of the propertyRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propertyRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPropertyRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPropertyRef() {
        if (propertyRef == null) {
            propertyRef = new ArrayList<String>();
        }
        return this.propertyRef;
    }

    /**
     * Gets the value of the row property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getRow() {
        if (row == null) {
            return  0;
        } else {
            return row;
        }
    }

    /**
     * Sets the value of the row property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRow(Integer value) {
        this.row = value;
    }

    /**
     * Gets the value of the col property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getCol() {
        if (col == null) {
            return  0;
        } else {
            return col;
        }
    }

    /**
     * Sets the value of the col property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCol(Integer value) {
        this.col = value;
    }

    /**
     * Gets the value of the formatter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatter() {
        if (formatter == null) {
            return "simple";
        } else {
            return formatter;
        }
    }

    /**
     * Sets the value of the formatter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatter(String value) {
        this.formatter = value;
    }

}