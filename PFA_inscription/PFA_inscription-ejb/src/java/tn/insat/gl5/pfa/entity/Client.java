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
@Table(name = "Client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findByIdClient", query = "SELECT c FROM Client c WHERE c.idClient = :idClient"),
    @NamedQuery(name = "Client.findByNomClient", query = "SELECT c FROM Client c WHERE c.nomClient = :nomClient"),
    @NamedQuery(name = "Client.findByPrenomClient", query = "SELECT c FROM Client c WHERE c.prenomClient = :prenomClient"),
    @NamedQuery(name = "Client.findByAgeClient", query = "SELECT c FROM Client c WHERE c.ageClient = :ageClient"),
    @NamedQuery(name = "Client.findByAdresseClient", query = "SELECT c FROM Client c WHERE c.adresseClient = :adresseClient"),
    @NamedQuery(name = "Client.findByVilleClient", query = "SELECT c FROM Client c WHERE c.villeClient = :villeClient"),
    @NamedQuery(name = "Client.findByCodePClient", query = "SELECT c FROM Client c WHERE c.codePClient = :codePClient"),
    @NamedQuery(name = "Client.findByEmailClient", query = "SELECT c FROM Client c WHERE c.emailClient = :emailClient")})
public class Client implements Serializable {
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "client")
    private PasswdOublie passwdOublie;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idClient")
    private Integer idClient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomClient")
    private String nomClient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "prenomClient")
    private String prenomClient;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ageClient")
    private int ageClient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "adresseClient")
    private String adresseClient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "villeClient")
    private String villeClient;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codePClient")
    private int codePClient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "emailClient")
    private String emailClient;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "client")
    private Users users;

    public Client() {
    }

//    public Client(Integer idClient) {
//        this.idClient = idClient;
//    }

    public Client( String nomClient, String prenomClient, int ageClient, String adresseClient, String villeClient, int codePClient, String emailClient) {
      //  this.idClient = idClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.ageClient = ageClient;
        this.adresseClient = adresseClient;
        this.villeClient = villeClient;
        this.codePClient = codePClient;
        this.emailClient = emailClient;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public int getAgeClient() {
        return ageClient;
    }

    public void setAgeClient(int ageClient) {
        this.ageClient = ageClient;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public String getVilleClient() {
        return villeClient;
    }

    public void setVilleClient(String villeClient) {
        this.villeClient = villeClient;
    }

    public int getCodePClient() {
        return codePClient;
    }

    public void setCodePClient(int codePClient) {
        this.codePClient = codePClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.insat.gl5.pfa.entity.Client[ idClient=" + idClient + " ]";
    }

    public PasswdOublie getPasswdOublie() {
        return passwdOublie;
    }

    public void setPasswdOublie(PasswdOublie passwdOublie) {
        this.passwdOublie = passwdOublie;
    }
    
}
