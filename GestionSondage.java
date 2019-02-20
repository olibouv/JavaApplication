public class GestionSondage {

    private Sondage[] listeSondage;

    public Sondage[] getListeSondage(){
        return listeSondage;
    }

    public void setListeSondage(Sondage[] listeSondage1){
        listeSondage=listeSondage1;
    }

    public void creerSondage(String question, String[] choix){
        Sondage sondage = new Sondage();
        sondage.setQuestion(question);
        sondage.setChoix(choix);
        listeSondage[sondage.getId()]=sondage;
    }


    public Sondage getSondageParId(int id){
        return listeSondage[id];
    }



}