/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "DepictionArticle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DepictionArticle.findAll", query = "SELECT d FROM DepictionArticle d"),
    @NamedQuery(name = "DepictionArticle.findByIdDepictionArticle", query = "SELECT d FROM DepictionArticle d WHERE d.idDepictionArticle = :idDepictionArticle"),
    @NamedQuery(name = "DepictionArticle.findByName", query = "SELECT d FROM DepictionArticle d WHERE d.name = :name"),
    @NamedQuery(name = "DepictionArticle.findByNr", query = "SELECT d FROM DepictionArticle d WHERE d.nr = :nr"),
    @NamedQuery(name = "DepictionArticle.findByPrice", query = "SELECT d FROM DepictionArticle d WHERE d.price = :price"),
    @NamedQuery(name = "DepictionArticle.findByEan", query = "SELECT d FROM DepictionArticle d WHERE d.ean = :ean"),
    @NamedQuery(name = "DepictionArticle.findByAmount", query = "SELECT d FROM DepictionArticle d WHERE d.amount = :amount"),
    @NamedQuery(name = "DepictionArticle.findByColourCode", query = "SELECT d FROM DepictionArticle d WHERE d.colourCode = :colourCode"),
    @NamedQuery(name = "DepictionArticle.findByDescription", query = "SELECT d FROM DepictionArticle d WHERE d.description = :description"),
    @NamedQuery(name = "DepictionArticle.findByCategory", query = "SELECT d FROM DepictionArticle d WHERE d.category = :category"),
    @NamedQuery(name = "DepictionArticle.findByStock", query = "SELECT d FROM DepictionArticle d WHERE d.stock = :stock"),
    @NamedQuery(name = "DepictionArticle.findByState", query = "SELECT d FROM DepictionArticle d WHERE d.state = :state"),
    @NamedQuery(name = "DepictionArticle.findByUstr", query = "SELECT d FROM DepictionArticle d WHERE d.ustr = :ustr"),
    @NamedQuery(name = "DepictionArticle.findByModifier", query = "SELECT d FROM DepictionArticle d WHERE d.modifier = :modifier"),
    @NamedQuery(name = "DepictionArticle.findByModificationDate", query = "SELECT d FROM DepictionArticle d WHERE d.modificationDate = :modificationDate")})
public class DepictionArticle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idDepictionArticle")
    private Integer idDepictionArticle;
    @Column(name = "name")
    private String name;
    @Column(name = "nr")
    private String nr;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Column(name = "ean")
    private String ean;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "colourCode")
    private String colourCode;
    @Lob
    @Column(name = "img")
    private byte[] img;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private String category;
    @Column(name = "stock")
    private String stock;
    @Column(name = "state")
    private String state;
    @Column(name = "ustr")
    private Double ustr;
    @Column(name = "modifier")
    private String modifier;
    @Column(name = "modificationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;

    public DepictionArticle() {
    }

    public DepictionArticle(Integer idDepictionArticle) {
        this.idDepictionArticle = idDepictionArticle;
    }

    public Integer getIdDepictionArticle() {
        return idDepictionArticle;
    }

    public void setIdDepictionArticle(Integer idDepictionArticle) {
        this.idDepictionArticle = idDepictionArticle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getColourCode() {
        return colourCode;
    }

    public void setColourCode(String colourCode) {
        this.colourCode = colourCode;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getUstr() {
        return ustr;
    }

    public void setUstr(Double ustr) {
        this.ustr = ustr;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepictionArticle != null ? idDepictionArticle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DepictionArticle)) {
            return false;
        }
        DepictionArticle other = (DepictionArticle) object;
        if ((this.idDepictionArticle == null && other.idDepictionArticle != null) || (this.idDepictionArticle != null && !this.idDepictionArticle.equals(other.idDepictionArticle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.gymnaila.chunks.artiver.entity.DepictionArticle[ idDepictionArticle=" + idDepictionArticle + " ]";
    }
    
}
