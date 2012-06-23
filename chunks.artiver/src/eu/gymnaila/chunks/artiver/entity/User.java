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
@Table(name = "User")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByPersonalNr", query = "SELECT u FROM User u WHERE u.personalNr = :personalNr"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByUid", query = "SELECT u FROM User u WHERE u.uid = :uid"),
    @NamedQuery(name = "User.findByModifier", query = "SELECT u FROM User u WHERE u.modifier = :modifier"),
    @NamedQuery(name = "User.findByModificationDate", query = "SELECT u FROM User u WHERE u.modificationDate = :modificationDate")
})
public class User implements Serializable
{
    @Column(name = "uid")
    private String uid;
    @Column(name = "modificationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idUser")
    private Integer idUser;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "personalNr")
    private int personalNr;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "modifier")
    private String modifier;
    @JoinColumn(name = "groups", referencedColumnName = "idGroups")
    @ManyToOne(optional = false)
    private Groups groups;

    public User()
    {
    }

    public User(Integer idUser)
    {
        this.idUser = idUser;
    }

    public User(Integer idUser, String name, int personalNr, String password)
    {
        this.idUser = idUser;
        this.name = name;
        this.personalNr = personalNr;
        this.password = password;
    }

    public Integer getIdUser()
    {
        return idUser;
    }

    public void setIdUser(Integer idUser)
    {
        this.idUser = idUser;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPersonalNr()
    {
        return personalNr;
    }

    public void setPersonalNr(int personalNr)
    {
        this.personalNr = personalNr;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getModifier()
    {
        return modifier;
    }

    public void setModifier(String modifier)
    {
        this.modifier = modifier;
    }

    public Groups getGroups()
    {
        return groups;
    }

    public void setGroups(Groups groups)
    {
        this.groups = groups;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User))
        {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public Date getModificationDate()
    {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate)
    {
        this.modificationDate = modificationDate;
    }
    
}
