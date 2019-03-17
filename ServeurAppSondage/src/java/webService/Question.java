/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webService;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import static webService.WebServiceGestionSondage.em;

/**
 *
 * @author Sarah
 */
@Entity
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer id;
    @Id
    private Integer idSondage;
    
    private String question;
    
    public Question(){}
    
    public Question(Integer id_sondage, String ques ){
        idSondage = id_sondage;
        // construction d'un objet Query	
        Query query = em.createQuery(
            "select count(question) from Question question where question.idSondage = "+id_sondage) ;

        // exécution et récupération de la liste résultat
        Long nbQuestion = (Long) query.getSingleResult();
    
        id = nbQuestion.intValue()+1;
    
        question = ques;
    }
    

    public Integer getId() {
        return id;
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
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "webService.Question[ id=" + id + " ]";
    }
    
    public String afficherQuestion(Integer id_sondage){
        String res="Question numero" + this.id +" : "+this.question +"\n     ";
        
        // construction d'un objet Query	
        Query query = em.createQuery(
            "select choix from Choix choix where choix.idSondage = "+id_sondage + " AND choix.idQuestion = "+ this.getId()) ;
        
         List<Choix> choixs = query.getResultList() ;
        
        for (Choix choix : choixs ){
            res += choix.afficherChoix() +"\n     ";
        }
        
        
        return res;
    }
    
    public String afficherResultatQuestion(Integer id_sondage){
        String res="Question numero" + this.id +" : "+this.question +"\n     ";
        
        // construction d'un objet Query	
        Query query = em.createQuery(
            "select choix from Choix choix where choix.idSondage = "+id_sondage + " AND choix.idQuestion = "+ this.getId()) ;
        
         List<Choix> choixs = query.getResultList() ;
        
        for (Choix choix : choixs ){
            res += choix.afficherResultatChoix() +"\n     ";
        }
        
        
        return res;
    }
}
