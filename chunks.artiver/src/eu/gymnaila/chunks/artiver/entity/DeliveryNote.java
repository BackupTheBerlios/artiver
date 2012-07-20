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
@Table(name = "DeliveryNote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeliveryNote.findAll", query = "SELECT d FROM DeliveryNote d"),
    @NamedQuery(name = "DeliveryNote.findByIdDeliveryNote", query = "SELECT d FROM DeliveryNote d WHERE d.idDeliveryNote = :idDeliveryNote"),
    @NamedQuery(name = "DeliveryNote.findByDeliveryNoteNumber", query = "SELECT d FROM DeliveryNote d WHERE d.deliveryNoteNumber = :deliveryNoteNumber"),
    @NamedQuery(name = "DeliveryNote.findByDelivery", query = "SELECT d FROM DeliveryNote d WHERE d.delivery = :delivery"),
    @NamedQuery(name = "DeliveryNote.findByDeliveryState", query = "SELECT d FROM DeliveryNote d WHERE d.deliveryState = :deliveryState"),
    @NamedQuery(name = "DeliveryNote.findByDeliveryDate", query = "SELECT d FROM DeliveryNote d WHERE d.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "DeliveryNote.findByModifier", query = "SELECT d FROM DeliveryNote d WHERE d.modifier = :modifier"),
    @NamedQuery(name = "DeliveryNote.findByModificationDate", query = "SELECT d FROM DeliveryNote d WHERE d.modificationDate = :modificationDate")})
public class DeliveryNote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idDeliveryNote")
    private Integer idDeliveryNote;
    @Basic(optional = false)
    @Column(name = "deliveryNoteNumber")
    private String deliveryNoteNumber;
    @Basic(optional = false)
    @Column(name = "delivery")
    private String delivery;
    @Basic(optional = false)
    @Column(name = "deliveryState")
    private String deliveryState;
    @Column(name = "deliveryDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;
    @Column(name = "modifier")
    private String modifier;
    @Column(name = "modificationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;
    @JoinColumn(name = "customer", referencedColumnName = "idCustomer")
    @ManyToOne
    private Customer customer;

    @ManyToMany
    @JoinTable(name="IntersectionDeliveryArticle",
               joinColumns=@JoinColumn(name="idDelivery"),
               inverseJoinColumns=@JoinColumn(name="idArticleIDA")
               )
    private List<DepictionArticle> articles;
    
    
    public DeliveryNote() {
    }

    public DeliveryNote(Integer idDeliveryNote) {
        this.idDeliveryNote = idDeliveryNote;
    }

    public DeliveryNote(Integer idDeliveryNote, String deliveryNoteNumber, String delivery, String deliveryState) {
        this.idDeliveryNote = idDeliveryNote;
        this.deliveryNoteNumber = deliveryNoteNumber;
        this.delivery = delivery;
        this.deliveryState = deliveryState;
    }

    public Integer getIdDeliveryNote() {
        return idDeliveryNote;
    }

    public void setIdDeliveryNote(Integer idDeliveryNote) {
        this.idDeliveryNote = idDeliveryNote;
    }

    public String getDeliveryNoteNumber() {
        return deliveryNoteNumber;
    }

    public void setDeliveryNoteNumber(String deliveryNoteNumber) {
        this.deliveryNoteNumber = deliveryNoteNumber;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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
        hash += (idDeliveryNote != null ? idDeliveryNote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeliveryNote)) {
            return false;
        }
        DeliveryNote other = (DeliveryNote) object;
        if ((this.idDeliveryNote == null && other.idDeliveryNote != null) || (this.idDeliveryNote != null && !this.idDeliveryNote.equals(other.idDeliveryNote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.gymnaila.chunks.artiver.entity.DeliveryNote[ idDeliveryNote=" + idDeliveryNote + " ]";
    }
    
}
