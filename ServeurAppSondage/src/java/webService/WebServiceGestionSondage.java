/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webService;

import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Sarah
 */
@Path("GestionSondage")
public class WebServiceGestionSondage {

    @Context
    private UriInfo context;
    
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceSondage");
    protected static EntityManager em = emf.createEntityManager();
    protected static EntityTransaction tx = em.getTransaction();
    

    /**
     * Creates a new instance of WebServiceGestionSondage
     */
    public WebServiceGestionSondage() {
    }

    /**
     * Retrieves representation of an instance of webService.WebServiceGestionSondage
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        return "Bonjour vous etes dans la methode getJson() sur le web service REST";
    }

    @POST
    @Path("creerSondage")
    @Produces("application/json")
    public String creerSondage(){
        Sondage sondage = new Sondage();
        
        //Persistance
        tx.begin();
        em.persist(sondage);
        tx.commit();
        
        String res = "" + sondage.getId();

        return res;
    }
    
    @POST
    @Path("ajouterQuestion/{id}/question/{question}")
    @Produces("application/json")
    public String ajouterQuestion(@PathParam("id")int idSondage, @PathParam("question")String question){
        String res;
        
        Query query = em.createQuery(
            "select sondage from Sondage sondage where sondage.id = "+idSondage) ;

        // exécution et récupération de la liste résultat
        List<Sondage> sondages = query.getResultList() ;
           
        Sondage sondage = (Sondage)sondages.get(0);
        res = ""+sondage.ajouterQuestion(question);
        
        return res;
    }
    
    @POST
    @Path("ajouterChoix/{idSondage}/question/{idQuestion}/choix/{choix}")
    @Produces("application/json")
    public String ajouterChoix(@PathParam("idSondage")int idSondage, @PathParam("idQuestion")int idQuestion, @PathParam("choix")String choix){
        String res;
        
        Query query = em.createQuery(
            "select sondage from Sondage sondage where sondage.id = "+idSondage) ;

        // exécution et récupération de la liste résultat
        List<Sondage> sondages = query.getResultList() ;
           
        Sondage sondage = (Sondage)sondages.get(0);
        res = ""+sondage.ajouterChoix(idQuestion, choix);
        
       return res;
    }

    @GET
    @Path("AfficherSondage/{id}")
    @Produces("application/json")
    public String AfficherSondage(@PathParam("id")int id){
        String res;
        
         Query query = em.createQuery(
            "select sondage from Sondage sondage where sondage.id = "+id) ;

        // exécution et récupération de la liste résultat
        List<Sondage> sondages = query.getResultList() ;
           
        Sondage sondage = (Sondage)sondages.get(0);
        
        res = sondage.afficherSondage();
       return res;
    }
    
    //Saisie d'un vote
    @POST
    @Path("AjouterVote/{idSondage}/question/{idQuestion}/vote/{vote}")
    @Produces("application/json")
    public String ajouterVote(@PathParam("idSondage") int idSondage, @PathParam("idQuestion") int idQuestion,@PathParam("vote") int vote){
        String res;
        
        Query query = em.createQuery(
            "select sondage from Sondage sondage where sondage.id = "+idSondage) ;

        // exécution et récupération de la liste résultat
        List<Sondage> sondages = query.getResultList() ;
           
        Sondage sondage = (Sondage)sondages.get(0);
        res = ""+sondage.ajouterVote(idQuestion, vote);
       
        return res;
    }
    
    //afficher résultats du sondage
    @GET
    @Path("AfficherResultatSondage/{id}")
    @Produces("application/json")
    public String AfficherResultatSondage(@PathParam("id")int id){
        String res;
        
         Query query = em.createQuery(
            "select sondage from Sondage sondage where sondage.id = "+id) ;

        // exécution et récupération de la liste résultat
        List<Sondage> sondages = query.getResultList() ;
           
        Sondage sondage = (Sondage)sondages.get(0);
        
        res = sondage.afficherResultatSondage();
       return res;
    }
    
    
    @GET
    @Path("nbQuestion/{id}")
    @Produces("application/json")
    public String nbQuestion(@PathParam("id")int id){
        String res;
        
         Query query = em.createQuery(
            "select sondage from Sondage sondage where sondage.id = "+id) ;

        // exécution et récupération de la liste résultat
        List<Sondage> sondages = query.getResultList() ;
           
        Sondage sondage = (Sondage)sondages.get(0);
        
        res = sondage.nbQuestion();
       return res;
    }

}
