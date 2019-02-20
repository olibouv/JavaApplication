public class Vote {

    private int id;
    private int reponse;
    private static int nbVote = 0;

    public Vote(){
        nbVote+=1;
        id=nbVote;
    }

    public int getId(){
        return id;
    }

    public int getReponse(){
        return reponse;
    }

    public void setId(int id1){
        id=id1;
    }

    public void setReponse(int reponse1){
        reponse=reponse1;
    }
}
