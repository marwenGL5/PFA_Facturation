/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.gl5.pfa.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marwen
 */
@Entity
@Table(name = "PasswdOublie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PasswdOublie.findAll", query = "SELECT p FROM PasswdOublie p"),
    @NamedQuery(name = "PasswdOublie.findByIdClient", query = "SELECT p FROM PasswdOublie p WHERE p.idClient = :idClient"),
    @NamedQuery(name = "PasswdOublie.findByPasswdGenere", query = "SELECT p FROM PasswdOublie p WHERE p.passwdGenere = :passwdGenere"),
    @NamedQuery(name = "PasswdOublie.findByDateGenere", query = "SELECT p FROM PasswdOublie p WHERE p.dateGenere = :dateGenere"),
    @NamedQuery(name = "PasswdOublie.findByTimeGenere", query = "SELECT p FROM PasswdOublie p WHERE p.timeGenere = :timeGenere")})
public class PasswdOublie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idClient")
    private Integer idClient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "passwdGenere")
    private String passwdGenere;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateGenere")
    @Temporal(TemporalType.DATE)
    private Date dateGenere;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timeGenere")
    @Temporal(TemporalType.TIME)
    private Date timeGenere;
    @JoinColumn(name = "idClient", referencedColumnName = "idClient", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Client client;

    public PasswdOublie() {
    }

    public PasswdOublie(Integer idClient) {
        this.idClient = idClient;
    }
    
    public PasswdOublie(Integer idClient, Date dateGenere, Date timeGenere) {
        this.idClient = idClient;
        
        this.dateGenere = dateGenere;
        this.timeGenere = timeGenere;
    }

    public PasswdOublie(Integer idClient, String passwdGenere, Date dateGenere, Date timeGenere) {
        this.idClient = idClient;
        this.passwdGenere = passwdGenere;
        this.dateGenere = dateGenere;
        this.timeGenere = timeGenere;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getPasswdGenere() {
        return passwdGenere;
    }

    public void setPasswdGenere(String passwdGenere) {
        this.passwdGenere = passwdGenere;
    }

    public Date getDateGenere() {
        return dateGenere;
    }

    public void setDateGenere(Date dateGenere) {
        this.dateGenere = dateGenere;
    }

    public Date getTimeGenere() {
        return timeGenere;
    }

    public void setTimeGenere(Date timeGenere) {
        this.timeGenere = timeGenere;
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
        if (!(object instanceof PasswdOublie)) {
            return false;
        }
        PasswdOublie other = (PasswdOublie) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.insat.gl5.pfa.entity.PasswdOublie[ idClient=" + idClient + " ]";
    }
    
}
