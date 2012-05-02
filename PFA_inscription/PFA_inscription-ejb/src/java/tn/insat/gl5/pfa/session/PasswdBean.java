/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.gl5.pfa.session;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tn.insat.gl5.pfa.entity.PasswdOublie;

/**
 *
 * @author marwen
 */
@Stateless
public class PasswdBean implements PasswdBeanLocal {
    @PersistenceContext(unitName = "PFA_inscription-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    /**
     * methode qui permet l'envoi du mot de passe generé automatiquement au client qui a oublié son pass
     * @param mail 
     * @return string du mot de passe generé pour l'ajouter dans la table
     */
    
    @Override
    public String envoiMail(String mail) {
        String passwd = GenererPass(6) ;
    Properties props = new Properties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.host", "smtp.gmail.com");
    props.put("mail.port", "587");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");
    
    Authenticator auth =new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                PasswordAuthentication pas = new PasswordAuthentication("benyaflah.marwen@gmail.com", "marwanofski");
                return pas;
            }
        
    };
    
    Session mailSession = Session.getInstance(props,auth);
    Transport transport;
        try {
            transport = mailSession.getTransport();
        
    MimeMessage message = new MimeMessage(mailSession);
    message.setSubject("mot de passe oublié");
    message.setContent(passwd, "text/plain");
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));

    transport.connect();
    transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
    transport.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return passwd ;
    }

    /**
     * methode qui genere les mot de passe automatiquement pour remplacer le pass oublié
     * @return 
     */
    @Override
    public String GenererPass(int length) {
    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    int charLength = chars.length();
    StringBuilder  pass = new StringBuilder (charLength);
        for (int x = 0; x < length; x++) {
            int i = (int) (Math.random() * charLength);
            pass.append(chars.charAt(i));
        }
        return pass.toString();
    
    }

    /**
     * ajouter le passeword dans la table
     * @param passwdOublie 
     */
    @Override
    public void creerPasswd(PasswdOublie passwdOublie) {
        em.persist(passwdOublie);
    }



    @Override
    public void supprimerPass(PasswdOublie passwd) {
        em.remove(passwd);
        
    }

    @Override
    public int findIdClient(String passGenerer) {
        try {
           Query query = em.createNamedQuery("PasswdOublie.findByPasswdGenere");
            query.setParameter("passwdGenere", passGenerer);
            List<PasswdOublie> list = query.getResultList();
            return list.get(0).getIdClient();
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public PasswdOublie findPasswd(String passwd) {
        try {
           Query query = em.createNamedQuery("PasswdOublie.findByPasswdGenere");
            query.setParameter("passwdGenere", passwd);
            List<PasswdOublie> list = query.getResultList();
            return list.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PasswdOublie updatePass(PasswdOublie passwdOublie) {
        em.merge(passwdOublie) ;
        return passwdOublie ;
    }
    
    
    
    
    
    
}
