/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author privat
 */
@Entity
@Table(name = "Article")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findByIdArticle", query = "SELECT a FROM Article a WHERE a.idArticle = :idArticle"),
    @NamedQuery(name = "Article.findByName", query = "SELECT a FROM Article a WHERE a.name = :name"),
    @NamedQuery(name = "Article.findByNr", query = "SELECT a FROM Article a WHERE a.nr = :nr"),
    @NamedQuery(name = "Article.findByPrice", query = "SELECT a FROM Article a WHERE a.price = :price"),
    @NamedQuery(name = "Article.findByEan", query = "SELECT a FROM Article a WHERE a.ean = :ean"),
    @NamedQuery(name = "Article.findByAmount", query = "SELECT a FROM Article a WHERE a.amount = :amount"),
    @NamedQuery(name = "Article.findByColourCode", query = "SELECT a FROM Article a WHERE a.colourCode = :colourCode"),
    @NamedQuery(name = "Article.findByDescription", query = "SELECT a FROM Article a WHERE a.description = :description"),
    @NamedQuery(name = "Article.findByModifier", query = "SELECT a FROM Article a WHERE a.modifier = :modifier"),
    @NamedQuery(name = "Article.findByModificationDate", query = "SELECT a FROM Article a WHERE a.modificationDate = :modificationDate")})
public class Article implements Serializable {
    @Lob
    @Column(name = "img")
    private byte[] img;
    @Column(name =     "modificationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idArticle")
    private Integer idArticle;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "nr")
    private String nr;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @Column(name = "ean")
    private String ean;
    @Basic(optional = false)
    @Column(name = "amount")
    private int amount;
    @Basic(optional = false)
    @Column(name = "colourCode")
    private String colourCode;
    @Column(name = "description")
    private String description;
    @Column(name = "modifier")
    private String modifier;
    @JoinColumn(name = "stock", referencedColumnName = "idStock")
    @ManyToOne(optional = false)
    private Stock stock;
    @JoinColumn(name = "state", referencedColumnName = "idState")
    @ManyToOne(optional = false)
    private State state;
    @JoinColumn(name = "category", referencedColumnName = "idCategory")
    @ManyToOne(optional = false)
    private Category category;

    public Article() {
    }

    public Article(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public Article(Integer idArticle, String name, String nr, double price, String ean, int amount, String colourCode) {
        this.idArticle = idArticle;
        this.name = name;
        this.nr = nr;
        this.price = price;
        this.ean = ean;
        this.amount = amount;
        this.colourCode = colourCode;
    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getColourCode() {
        return colourCode;
    }

    public void setColourCode(String colourCode) {
        this.colourCode = colourCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticle != null ? idArticle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.idArticle == null && other.idArticle != null) || (this.idArticle != null && !this.idArticle.equals(other.idArticle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.gymnaila.chunks.artiver.entity.Article[ idArticle=" + idArticle + " ]";
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }
    
}
