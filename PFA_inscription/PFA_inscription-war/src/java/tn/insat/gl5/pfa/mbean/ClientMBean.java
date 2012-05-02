/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.gl5.pfa.mbean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import tn.insat.gl5.pfa.entity.Client;
import tn.insat.gl5.pfa.entity.PasswdOublie;
import tn.insat.gl5.pfa.entity.Users;
import tn.insat.gl5.pfa.session.ClientBeanLocal;
import tn.insat.gl5.pfa.session.PasswdBeanLocal;

/**
 *
 * @author marwen
 */
@Named(value = "clientMBean")
@SessionScoped
public class ClientMBean implements Serializable {

    @EJB
    private PasswdBeanLocal passwdBean;
    @EJB
    private ClientBeanLocal clientBean;
    private Client currentClient;
    private Users currentUser;
    private PasswdOublie currentPass;

    /**
     * Creates a new instance of ClientMBean
     */
    public ClientMBean() {
    }

    /**
     * @return the client
     */
    public Client getSelectedClient() {
        if (currentClient == null) {
            currentClient = new Client();
        }
        return currentClient;
    }

    /**
     * @return the user
     */
    public Users getSelectedUser() {
        if (currentUser == null) {
            currentUser = new Users();
        }
        return currentUser;
    }

    public PasswdOublie getSelectedPass() {
        if (currentPass == null) {
            currentPass = new PasswdOublie();
        }
        return currentPass;
    }

    public String prepareCreate() {
        currentClient = new Client();
        currentUser = new Users();
        return "/users/validation?faces-redirect=true";
    }

    /**
     * ajouter le client courant dans la base de donnée
     *
     * @return String pour la redirection au espace utilisateur du client
     * inscrit
     */
    public String create() {
        try {
            currentUser.setIdClient(currentClient.getIdClient());
            currentClient.setUsers(currentUser);
            clientBean.creerClient(currentClient);
            return prepareCreate();
        } catch (Exception e) {
            return "/users/erreur?faces-redirect=true";
        }
    }

    /**
     * ajoute le mot de passe et envoi un mail pour le client si ce dernier est
     * inscrit déjà
     *
     * @return
     */
    public String envoiMail() {

        currentClient = clientBean.findbyEmail(currentClient.getEmailClient());
        if (currentClient != null) {
            String pass = passwdBean.envoiMail(currentClient.getEmailClient());
            PasswdOublie passwd = new PasswdOublie(currentClient.getIdClient(), pass, new Date(), new Time(Calendar.getInstance().getTime().getTime()));
            passwdBean.creerPasswd(passwd);
            return "confirmation?faces-redirect=true";
        } else {
            return "erreur?faces-redirect=true";
        }
    }

    public String loginPassOublie() throws ServletException {
        
        currentUser = clientBean.findUserById(passwdBean.findIdClient(currentPass.getPasswdGenere()));
        currentPass = passwdBean.findPasswd(currentPass.getPasswdGenere()) ;
       // if (currentUser != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
          //  try {
                request.login(currentUser.getUsername(), currentUser.getPasswd());
                currentUser = new Users(currentUser.getIdClient(),currentUser.getUsername()) ;
                currentPass = new PasswdOublie(currentPass.getIdClient(),currentPass.getPasswdGenere(), currentPass.getDateGenere(), currentPass.getTimeGenere()) ;
                return "/users/NouveauPass?faces-redirect=true";
          //  } catch (ServletException e) {
                // Handle unknown username/password in request.login().
                //context.addMessage(null, new FacesMessage("Unknown login"));
            //    return "erreur?faces-redirect=true";
        //    }
        //} else {
            // currentPass = new PasswdOublie() ;
       //     return "erreur?faces-redirect=true";
       // }
    }

    public String changerPass() {
        clientBean.updateUser(currentUser);
        
        //currentPass.setIdClient(currentUser.getIdClient());
        // traitement sur la date 
        currentPass =  passwdBean.updatePass(currentPass);
        passwdBean.supprimerPass(currentPass);
        return "/users/profile?faces-redirect=true";
    }
}
