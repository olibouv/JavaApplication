public class Sondage {

    private int id;
    private String question;
    private String[] choix;
    private Vote[] listeVote;
    private static int nbSondage = 0;

    public Sondage(){
        nbSondage += 1;
        id=nbSondage;
    }

    public int getId(){
        return id;
    }

    public String getQuestion(){
        return question;
    }

    public String[] getChoix(){
        return choix;
    }

    public Vote[] getListeVote(){
        return listeVote;
    }

    public void setId(int id1){
        id=id1;
    }

    public void setQuestion(String question1){
        question=question1;
    }

    public void setChoix(String[] choix1){
        choix=choix1;
    }

    public void setListeVote(Vote[] listeVote1){
        listeVote=listeVote1;
    }

    public void ajouterVote(int id){
        Vote vote = new Vote();
        vote.setId(id);
        listeVote.add(vote);
    }

}