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
@Table(name = "Offer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Offer.findAll", query = "SELECT o FROM Offer o"),
    @NamedQuery(name = "Offer.findByIdOffer", query = "SELECT o FROM Offer o WHERE o.idOffer = :idOffer"),
    @NamedQuery(name = "Offer.findByOfferNumber", query = "SELECT o FROM Offer o WHERE o.offerNumber = :offerNumber"),
    @NamedQuery(name = "Offer.findByPrice", query = "SELECT o FROM Offer o WHERE o.price = :price"),
    @NamedQuery(name = "Offer.findByModifier", query = "SELECT o FROM Offer o WHERE o.modifier = :modifier"),
    @NamedQuery(name = "Offer.findByModificationDate", query = "SELECT o FROM Offer o WHERE o.modificationDate = :modificationDate")})
public class Offer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idOffer")
    private Integer idOffer;
    @Basic(optional = false)
    @Column(name = "offerNumber")
    private String offerNumber;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @Column(name = "modifier")
    private String modifier;
    @Column(name = "modificationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;
    @JoinColumn(name = "customer", referencedColumnName = "idCustomer")
    @ManyToOne
    private Customer customer;

    @ManyToMany
    @JoinTable(name="IntersectionOfferArticle",
               joinColumns=@JoinColumn(name="idOffer"),
               inverseJoinColumns=@JoinColumn(name="idArticleIOA")
               )
    private List<DepictionArticle> articles;
 
    
    public Offer() {
    }

    public Offer(Integer idOffer) {
        this.idOffer = idOffer;
    }

    public Offer(Integer idOffer, String offerNumber, double price) {
        this.idOffer = idOffer;
        this.offerNumber = offerNumber;
        this.price = price;
    }

    public Integer getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(Integer idOffer) {
        this.idOffer = idOffer;
    }

    public String getOfferNumber() {
        return offerNumber;
    }

    public void setOfferNumber(String offerNumber) {
        this.offerNumber = offerNumber;
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
        hash += (idOffer != null ? idOffer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Offer)) {
            return false;
        }
        Offer other = (Offer) object;
        if ((this.idOffer == null && other.idOffer != null) || (this.idOffer != null && !this.idOffer.equals(other.idOffer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.gymnaila.chunks.artiver.entity.Offer[ idOffer=" + idOffer + " ]";
    }
    
}
