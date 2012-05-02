/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.gl5.pfa.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marwen
 */
@Entity
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPasswd", query = "SELECT u FROM Users u WHERE u.passwd = :passwd"),
    @NamedQuery(name = "Users.findByIdClient", query = "SELECT u FROM Users u WHERE u.idClient = :idClient")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "passwd")
    private String passwd;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idClient")
    private Integer idClient;
    @JoinColumn(name = "idClient", referencedColumnName = "idClient", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Client client;

    public Users() {
    }

    public Users(Integer idClient) {
        this.idClient = idClient;
    }

    public Users(Integer idClient, String username) {
        this.idClient = idClient;
         this.username = username;
    }
    public Users(Integer idClient, String username, String passwd) {
        this.idClient = idClient;
        this.username = username;
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.insat.gl5.pfa.entity.Users[ idClient=" + idClient + " ]";
    }
    
}
