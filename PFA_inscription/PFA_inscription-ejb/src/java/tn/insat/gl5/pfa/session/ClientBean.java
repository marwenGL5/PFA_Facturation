/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.gl5.pfa.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tn.insat.gl5.pfa.entity.Client;
import tn.insat.gl5.pfa.entity.Users;

/**
 *
 * @author marwen
 */
@Stateless
public class ClientBean implements ClientBeanLocal, ClientBeanRemote {
    @PersistenceContext(unitName = "PFA_inscription-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }


    @Override
    public void creerClient(Client client) {
        em.persist(client);
    
    }

    @Override
    public Client findbyEmail(String email) {
        try{
        Query query = em.createNamedQuery("Client.findByEmailClient");
        query.setParameter("emailClient", email) ;
        List<Client> list = query.getResultList() ;
        return list.get(0);
        }catch(Exception e){
            return null;
        }
    }

    

    @Override
    public Users findUserById(int idClient) {
        try{
        Query query = em.createNamedQuery("Users.findByIdClient");
        query.setParameter("idClient", idClient) ;
        List<Users> list = query.getResultList() ;
        return list.get(0);
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public void updateUser(Users user) {
        em.merge(user) ;
    }

    
    
    
    
    
}
