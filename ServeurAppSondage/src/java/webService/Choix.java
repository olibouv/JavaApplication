/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webService;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlRootElement;
import static webService.WebServiceGestionSondage.em;

/**
 *
 * @author Sarah
 */
@Entity
@XmlRootElement
public class Choix implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer id;
    @Id
    private Integer idQuestion;
    @Id
    private Integer idSondage;
    
    private String intituleChoix;
    private int nbVote;
    
    public Choix(){
        
    }
    
    public Choix(Integer id_question, Integer id_sondage, String choix){
        // construction d'un objet Query	
        Query query = em.createQuery(
            "select count(choix) from Choix choix where choix.idSondage = "+id_sondage+ " AND choix.idQuestion = "+id_question) ;

        // exécution et récupération de la liste résultat
        Long nbChoix = (Long) query.getSingleResult();
    
        id = nbChoix.intValue()+1;
        
        idQuestion = id_question;
        idSondage = id_sondage;
        intituleChoix = choix;
        nbVote = 0;
    }



    public int getId(){
        return id;
    }
    
    public int getnbVote(){
        return nbVote;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Choix)) {
            return false;
        }
        Choix other = (Choix) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "webService.Vote2[ id=" + id + " ]";
    }
    
    public void incrementerVote(){
    
        this.nbVote++;
    }
    
    public String afficherChoix(){
       String res = "Choix numero"+ this.id+ " : "+ this.intituleChoix;
       
       return res;        
    }
    
    public String afficherResultatChoix (){
       String res = "Choix numero"+ this.id+ " : "+ this.intituleChoix;
       
       if (this.nbVote==1 || this.nbVote==0){
           res += "\n          " + this.nbVote + " vote";
       }
       else {
           res += "\n          " + this.nbVote + " votes";
       }
       
       return res;    
    }
    
}
