/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.gl5.pfa.session;

import javax.ejb.Local;
import tn.insat.gl5.pfa.entity.Client;
import tn.insat.gl5.pfa.entity.Users;

/**
 *
 * @author marwen
 */
@Local
public interface ClientBeanLocal {

    void creerClient(Client client);

    Client findbyEmail(String email);

    

    Users findUserById(int idClient);

    void updateUser(Users user);
    
}
