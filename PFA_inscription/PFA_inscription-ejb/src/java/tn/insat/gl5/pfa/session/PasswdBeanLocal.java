/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.gl5.pfa.session;

import javax.ejb.Local;
import tn.insat.gl5.pfa.entity.PasswdOublie;

/**
 *
 * @author marwen
 */
@Local
public interface PasswdBeanLocal {

    String envoiMail(String mail);

    String GenererPass(int length);

    void creerPasswd(PasswdOublie passwdOublie);

   
    void supprimerPass(PasswdOublie Passwd);

    int findIdClient(String passGenerer);

    PasswdOublie findPasswd(String passwd);

    PasswdOublie updatePass(PasswdOublie passwdOublie);
    
}
