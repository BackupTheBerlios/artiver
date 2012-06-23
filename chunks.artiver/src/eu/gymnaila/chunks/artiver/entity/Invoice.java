/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "Invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findByIdInvoice", query = "SELECT i FROM Invoice i WHERE i.idInvoice = :idInvoice"),
    @NamedQuery(name = "Invoice.findByInvoiceNumber", query = "SELECT i FROM Invoice i WHERE i.invoiceNumber = :invoiceNumber"),
    @NamedQuery(name = "Invoice.findByPrice", query = "SELECT i FROM Invoice i WHERE i.price = :price"),
    @NamedQuery(name = "Invoice.findByModifier", query = "SELECT i FROM Invoice i WHERE i.modifier = :modifier"),
    @NamedQuery(name = "Invoice.findByModificationDate", query = "SELECT i FROM Invoice i WHERE i.modificationDate = :modificationDate")})
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idInvoice")
    private Integer idInvoice;
    @Basic(optional = false)
    @Column(name = "invoiceNumber")
    private String invoiceNumber;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @Column(name = "modifier")
    private String modifier;
    @Column(name = "modificationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;
    @JoinColumn(name = "customer", referencedColumnName = "idCustomer")
    @ManyToOne(optional = false)
    private Customer customer;
    
    @ManyToMany
    @JoinTable(name="IntersectionInvoiceArticle",
               joinColumns=@JoinColumn(name="idInvoice"),
               inverseJoinColumns=@JoinColumn(name="idArticle")
               )
    private List<DepictionArticle> articles;

    
    public Invoice() {
    }

    public Invoice(Integer idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Invoice(Integer idInvoice, String invoiceNumber, double price, List<DepictionArticle> articles) {
        this.idInvoice = idInvoice;
        this.invoiceNumber = invoiceNumber;
        this.price = price;
        this.articles = articles;
    }

    public Integer getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Integer idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
     public List<DepictionArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<DepictionArticle> articles) {
        this.articles = articles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInvoice != null ? idInvoice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.idInvoice == null && other.idInvoice != null) || (this.idInvoice != null && !this.idInvoice.equals(other.idInvoice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.gymnaila.chunks.artiver.entity.Invoice[ idInvoice=" + idInvoice + " ]";
    }
    
}
