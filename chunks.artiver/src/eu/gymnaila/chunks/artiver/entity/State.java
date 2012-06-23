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
@Table(name = "State")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "State.findAll", query = "SELECT s FROM State s"),
    @NamedQuery(name = "State.findByIdState", query = "SELECT s FROM State s WHERE s.idState = :idState"),
    @NamedQuery(name = "State.findByName", query = "SELECT s FROM State s WHERE s.name = :name"),
    @NamedQuery(name = "State.findByDescription", query = "SELECT s FROM State s WHERE s.description = :description"),
    @NamedQuery(name = "State.findByModifier", query = "SELECT s FROM State s WHERE s.modifier = :modifier"),
    @NamedQuery(name = "State.findByModificationDate", query = "SELECT s FROM State s WHERE s.modificationDate = :modificationDate")})
public class State implements Serializable {
    @Column(name =     "modificationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idState")
    private Integer idState;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Column(name = "modifier")
    private String modifier;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    private Collection<Article> articleCollection;

    public State() {
    }

    public State(Integer idState) {
        this.idState = idState;
    }

    public State(Integer idState, String name, String description) {
        this.idState = idState;
        this.name = name;
        this.description = description;
    }

    public Integer getIdState() {
        return idState;
    }

    public void setIdState(Integer idState) {
        this.idState = idState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @XmlTransient
    public Collection<Article> getArticleCollection() {
        return articleCollection;
    }

    public void setArticleCollection(Collection<Article> articleCollection) {
        this.articleCollection = articleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idState != null ? idState.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof State)) {
            return false;
        }
        State other = (State) object;
        if ((this.idState == null && other.idState != null) || (this.idState != null && !this.idState.equals(other.idState))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }
    
}
