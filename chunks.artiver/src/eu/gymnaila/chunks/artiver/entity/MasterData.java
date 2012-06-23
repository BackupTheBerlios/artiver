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
@Table(name = "MasterData")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterData.findAll", query = "SELECT m FROM MasterData m"),
    @NamedQuery(name = "MasterData.findByIdMasterData", query = "SELECT m FROM MasterData m WHERE m.idMasterData = :idMasterData"),
    @NamedQuery(name = "MasterData.findByCompanyName", query = "SELECT m FROM MasterData m WHERE m.companyName = :companyName"),
    @NamedQuery(name = "MasterData.findByCorporateForm", query = "SELECT m FROM MasterData m WHERE m.corporateForm = :corporateForm"),
    @NamedQuery(name = "MasterData.findByContact", query = "SELECT m FROM MasterData m WHERE m.contact = :contact"),
    @NamedQuery(name = "MasterData.findByStreet", query = "SELECT m FROM MasterData m WHERE m.street = :street"),
    @NamedQuery(name = "MasterData.findByPostalCode", query = "SELECT m FROM MasterData m WHERE m.postalCode = :postalCode"),
    @NamedQuery(name = "MasterData.findByInvoicePrefix", query = "SELECT m FROM MasterData m WHERE m.invoicePrefix = :invoicePrefix"),
    @NamedQuery(name = "MasterData.findByOfferPrefix", query = "SELECT m FROM MasterData m WHERE m.offerPrefix = :offerPrefix"),
    @NamedQuery(name = "MasterData.findByDeliveryNotePrefix", query = "SELECT m FROM MasterData m WHERE m.deliveryNotePrefix = :deliveryNotePrefix"),
    @NamedQuery(name = "MasterData.findByTaxID", query = "SELECT m FROM MasterData m WHERE m.taxID = :taxID"),
    @NamedQuery(name = "MasterData.findByUstID", query = "SELECT m FROM MasterData m WHERE m.ustID = :ustID"),
    @NamedQuery(name = "MasterData.findByVat", query = "SELECT m FROM MasterData m WHERE m.vat = :vat"),
    @NamedQuery(name = "MasterData.findByTerms", query = "SELECT m FROM MasterData m WHERE m.terms = :terms"),
    @NamedQuery(name = "MasterData.findByModifier", query = "SELECT m FROM MasterData m WHERE m.modifier = :modifier"),
    @NamedQuery(name = "MasterData.findByModificationDate", query = "SELECT m FROM MasterData m WHERE m.modificationDate = :modificationDate")})
public class MasterData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idMasterData")
    private Integer idMasterData;
    @Basic(optional = false)
    @Column(name = "companyName")
    private String companyName;
    @Basic(optional = false)
    @Column(name = "corporateForm")
    private String corporateForm;
    @Basic(optional = false)
    @Column(name = "contact")
    private String contact;
    @Basic(optional = false)
    @Column(name = "street")
    private String street;
    @Basic(optional = false)
    @Column(name = "postalCode")
    private String postalCode;
    @Basic(optional = false)
    @Column(name = "invoicePrefix")
    private String invoicePrefix;
    @Basic(optional = false)
    @Column(name = "offerPrefix")
    private String offerPrefix;
    @Basic(optional = false)
    @Column(name = "deliveryNotePrefix")
    private String deliveryNotePrefix;
    @Basic(optional = false)
    @Column(name = "taxID")
    private String taxID;
    @Basic(optional = false)
    @Column(name = "ustID")
    private String ustID;
    @Basic(optional = false)
    @Column(name = "vat")
    private double vat;
    @Basic(optional = false)
    @Column(name = "terms")
    private String terms;
    @Column(name = "modifier")
    private String modifier;
    @Column(name = "modificationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;

    public MasterData() {
    }

    public MasterData(Integer idMasterData) {
        this.idMasterData = idMasterData;
    }

    public MasterData(Integer idMasterData, String companyName, String corporateForm, String contact, String street, String postalCode, String invoicePrefix, String offerPrefix, String deliveryNotePrefix, String taxID, String ustID, double vat, String terms) {
        this.idMasterData = idMasterData;
        this.companyName = companyName;
        this.corporateForm = corporateForm;
        this.contact = contact;
        this.street = street;
        this.postalCode = postalCode;
        this.invoicePrefix = invoicePrefix;
        this.offerPrefix = offerPrefix;
        this.deliveryNotePrefix = deliveryNotePrefix;
        this.taxID = taxID;
        this.ustID = ustID;
        this.vat = vat;
        this.terms = terms;
    }

    public Integer getIdMasterData() {
        return idMasterData;
    }

    public void setIdMasterData(Integer idMasterData) {
        this.idMasterData = idMasterData;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCorporateForm() {
        return corporateForm;
    }

    public void setCorporateForm(String corporateForm) {
        this.corporateForm = corporateForm;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getInvoicePrefix() {
        return invoicePrefix;
    }

    public void setInvoicePrefix(String invoicePrefix) {
        this.invoicePrefix = invoicePrefix;
    }

    public String getOfferPrefix() {
        return offerPrefix;
    }

    public void setOfferPrefix(String offerPrefix) {
        this.offerPrefix = offerPrefix;
    }

    public String getDeliveryNotePrefix() {
        return deliveryNotePrefix;
    }

    public void setDeliveryNotePrefix(String deliveryNotePrefix) {
        this.deliveryNotePrefix = deliveryNotePrefix;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public String getUstID() {
        return ustID;
    }

    public void setUstID(String ustID) {
        this.ustID = ustID;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
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
        hash += (idMasterData != null ? idMasterData.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterData)) {
            return false;
        }
        MasterData other = (MasterData) object;
        if ((this.idMasterData == null && other.idMasterData != null) || (this.idMasterData != null && !this.idMasterData.equals(other.idMasterData))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.gymnaila.chunks.artiver.entity.MasterData[ idMasterData=" + idMasterData + " ]";
    }
    
}
