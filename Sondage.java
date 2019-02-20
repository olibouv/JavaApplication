public class Sondage {

    private int id;
    private String question;
    private String[] choix;
    private Vote[] listeVote;

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

    
}