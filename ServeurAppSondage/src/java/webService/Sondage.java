/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webService;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Query;
import static webService.WebServiceGestionSondage.em;
import static webService.WebServiceGestionSondage.tx;

/**
 *
 * @author Sarah
 */
@Entity
@javax.persistence.Table(name="Sondage")
public class Sondage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }
    
    public Sondage(){
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
        if (!(object instanceof Sondage)) {
            return false;
        }
        Sondage other = (Sondage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "webService.Sondage[ id=" + id + " ]";
    }
    
    
    public Integer ajouterQuestion (String ques){
        
       Question q = new Question(this.getId(),ques);

       tx.begin();
       em.persist(q);
       tx.commit();
       
       return q.getId();
    }
    
    public Integer ajouterChoix (Integer id_quest, String choix){
        
       Choix c = new Choix(id_quest, this.getId() ,choix);

       tx.begin();
       em.persist(c);
       tx.commit();
       
       return c.getId();
    }
        
    public Integer ajouterVote (Integer id_quest, Integer id_choix){
        
        // construction d'un objet Query	
        Query query = em.createQuery(
            "select choix from Choix choix where choix.idSondage = "+this.getId()+ " AND choix.idQuestion = "+id_quest +" AND choix.id = "+id_choix) ;
        

        // exécution et récupération de la liste résultat
        List<Choix> choixs = query.getResultList() ;
           
        Choix c = (Choix)choixs.get(0);
        
        tx.begin();
        c.incrementerVote();
        tx.commit();
       
       return c.getnbVote();
    }
    
    public String afficherSondage(){
        String res ="Sondage numero" + this.id +"\n ";
        
        // construction d'un objet Query	
        Query query = em.createQuery(
            "select question from Question question where question.idSondage = "+this.getId()) ;
        
         List<Question> questions = query.getResultList() ;
           
         
        // Pour toutes question ayant un numéro de sondage egal au idsondage, on affiche la question
        for (Question question : questions ){
            res += question.afficherQuestion(this.getId())+"\n ";
        }
           
        return res;
    }
    
    public String afficherResultatSondage(){
        String res ="Sondage numero" + this.id +"\n ";
        
        // construction d'un objet Query	
        Query query = em.createQuery(
            "select question from Question question where question.idSondage = "+this.getId()) ;
        
         List<Question> questions = query.getResultList() ;
           
         
        // Pour toutes question ayant un numéro de sondage egal au idsondage, on affiche la question
        for (Question question : questions ){
            res += question.afficherResultatQuestion(this.getId())+"\n ";
        }
           
        return res;
    }
    
    public String nbQuestion(){
        String res;
        
        // construction d'un objet Query	
        Query query = em.createQuery(
            "select count(question) from Question question where question.idSondage = "+this.getId()) ;

        // exécution et récupération de la liste résultat
        Long nbChoix = (Long) query.getSingleResult();
    
        res = String.valueOf(nbChoix);
        
        return res;
    }
}
