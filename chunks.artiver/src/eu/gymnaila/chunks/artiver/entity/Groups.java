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
 * @author privat
 */
@Entity
@Table(name = "Groups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Groups g"),
    @NamedQuery(name = "Groups.findByIdGroups", query = "SELECT g FROM Groups g WHERE g.idGroups = :idGroups"),
    @NamedQuery(name = "Groups.findByName", query = "SELECT g FROM Groups g WHERE g.name = :name"),
    @NamedQuery(name = "Groups.findByCheckIn", query = "SELECT g FROM Groups g WHERE g.checkIn = :checkIn"),
    @NamedQuery(name = "Groups.findByCheckOut", query = "SELECT g FROM Groups g WHERE g.checkOut = :checkOut"),
    @NamedQuery(name = "Groups.findByArticle", query = "SELECT g FROM Groups g WHERE g.article = :article"),
    @NamedQuery(name = "Groups.findByCategory", query = "SELECT g FROM Groups g WHERE g.category = :category"),
    @NamedQuery(name = "Groups.findByStock", query = "SELECT g FROM Groups g WHERE g.stock = :stock"),
    @NamedQuery(name = "Groups.findByState", query = "SELECT g FROM Groups g WHERE g.state = :state"),
    @NamedQuery(name = "Groups.findByInvoice", query = "SELECT g FROM Groups g WHERE g.invoice = :invoice"),
    @NamedQuery(name = "Groups.findByOffer", query = "SELECT g FROM Groups g WHERE g.offer = :offer"),
    @NamedQuery(name = "Groups.findByDeliveryNote", query = "SELECT g FROM Groups g WHERE g.deliveryNote = :deliveryNote"),
    @NamedQuery(name = "Groups.findByGroups", query = "SELECT g FROM Groups g WHERE g.groups = :groups"),
    @NamedQuery(name = "Groups.findByUser", query = "SELECT g FROM Groups g WHERE g.user = :user"),
    @NamedQuery(name = "Groups.findByCustomer", query = "SELECT g FROM Groups g WHERE g.customer = :customer"),
    @NamedQuery(name = "Groups.findByMasterData", query = "SELECT g FROM Groups g WHERE g.masterData = :masterData"),
    @NamedQuery(name = "Groups.findByAdmin", query = "SELECT g FROM Groups g WHERE g.admin = :admin"),
    @NamedQuery(name = "Groups.findByVoc", query = "SELECT g FROM Groups g WHERE g.voc = :voc"),
    @NamedQuery(name = "Groups.findByModifier", query = "SELECT g FROM Groups g WHERE g.modifier = :modifier"),
    @NamedQuery(name = "Groups.findByModificationDate", query = "SELECT g FROM Groups g WHERE g.modificationDate = :modificationDate")})
public class Groups implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idGroups")
    private Integer idGroups;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "checkIn")
    private Boolean checkIn;
    @Column(name = "checkOut")
    private Boolean checkOut;
    @Column(name = "article")
    private Boolean article;
    @Column(name = "category")
    private Boolean category;
    @Column(name = "stock")
    private Boolean stock;
    @Column(name = "state")
    private Boolean state;
    @Column(name = "invoice")
    private Boolean invoice;
    @Column(name = "offer")
    private Boolean offer;
    @Column(name = "deliveryNote")
    private Boolean deliveryNote;
    @Column(name = "groups")
    private Boolean groups;
    @Column(name = "user")
    private Boolean user;
    @Column(name = "customer")
    private Boolean customer;
    @Column(name = "masterData")
    private Boolean masterData;
    @Column(name = "admin")
    private Boolean admin;
    @Column(name = "voc")
    private Boolean voc;
    @Column(name = "modifier")
    private String modifier;
    @Column(name = "modificationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;

    public Groups() {
        checkIn = Boolean.FALSE;
        checkOut = Boolean.FALSE;
        article = Boolean.FALSE;
        category = Boolean.FALSE;
        stock = Boolean.FALSE;
        state = Boolean.FALSE;
        invoice = Boolean.FALSE;
        offer = Boolean.FALSE;
        deliveryNote = Boolean.FALSE;
        groups = Boolean.FALSE;
        user = Boolean.FALSE;
        customer = Boolean.FALSE;
        masterData = Boolean.FALSE;
        admin = Boolean.FALSE;
        voc = Boolean.FALSE;      
    }

    public Groups(Integer idGroups) {
        this.idGroups = idGroups;
        
        checkIn = Boolean.FALSE;
        checkOut = Boolean.FALSE;
        article = Boolean.FALSE;
        category = Boolean.FALSE;
        stock = Boolean.FALSE;
        state = Boolean.FALSE;
        invoice = Boolean.FALSE;
        offer = Boolean.FALSE;
        deliveryNote = Boolean.FALSE;
        groups = Boolean.FALSE;
        user = Boolean.FALSE;
        customer = Boolean.FALSE;
        masterData = Boolean.FALSE;
        admin = Boolean.FALSE;
        voc = Boolean.FALSE;
    }

    public Groups(Integer idGroups, String name) {
        this.idGroups = idGroups;
        this.name = name;
        
        checkIn = Boolean.FALSE;
        checkOut = Boolean.FALSE;
        article = Boolean.FALSE;
        category = Boolean.FALSE;
        stock = Boolean.FALSE;
        state = Boolean.FALSE;
        invoice = Boolean.FALSE;
        offer = Boolean.FALSE;
        deliveryNote = Boolean.FALSE;
        groups = Boolean.FALSE;
        user = Boolean.FALSE;
        customer = Boolean.FALSE;
        masterData = Boolean.FALSE;
        admin = Boolean.FALSE;
        voc = Boolean.FALSE;
    }

    public Integer getIdGroups() {
        return idGroups;
    }

    public void setIdGroups(Integer idGroups) {
        this.idGroups = idGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Boolean checkIn) {
        this.checkIn = checkIn;
    }

    public Boolean getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Boolean checkOut) {
        this.checkOut = checkOut;
    }

    public Boolean getArticle() {
        return article;
    }

    public void setArticle(Boolean article) {
        this.article = article;
    }

    public Boolean getCategory() {
        return category;
    }

    public void setCategory(Boolean category) {
        this.category = category;
    }

    public Boolean getStock() {
        return stock;
    }

    public void setStock(Boolean stock) {
        this.stock = stock;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getInvoice() {
        return invoice;
    }

    public void setInvoice(Boolean invoice) {
        this.invoice = invoice;
    }

    public Boolean getOffer() {
        return offer;
    }

    public void setOffer(Boolean offer) {
        this.offer = offer;
    }

    public Boolean getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(Boolean deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public Boolean getGroups() {
        return groups;
    }

    public void setGroups(Boolean groups) {
        this.groups = groups;
    }

    public Boolean getUser() {
        return user;
    }

    public void setUser(Boolean user) {
        this.user = user;
    }

    public Boolean getCustomer() {
        return customer;
    }

    public void setCustomer(Boolean customer) {
        this.customer = customer;
    }

    public Boolean getMasterData() {
        return masterData;
    }

    public void setMasterData(Boolean masterData) {
        this.masterData = masterData;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getVoc() {
        return voc;
    }

    public void setVoc(Boolean voc) {
        this.voc = voc;
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
        hash += (idGroups != null ? idGroups.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.idGroups == null && other.idGroups != null) || (this.idGroups != null && !this.idGroups.equals(other.idGroups))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
