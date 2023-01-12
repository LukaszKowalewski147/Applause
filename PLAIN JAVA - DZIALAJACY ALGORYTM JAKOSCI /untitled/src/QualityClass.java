import java.util.LinkedList;

public class QualityClass {

    private LinkedList<Clap> listaKlasniec;

    public QualityClass(LinkedList<Clap> otrzymaneKlasniecia) {
        listaKlasniec = otrzymaneKlasniecia;
    }

    private double getTimeInSeconds(long time){
        Double divider = Double.valueOf(1000000000);
        Double t = time / divider;
        return t;
    }

    public int calculateQuality(){
        int i = 0;
        Double sumaCzasuKlasniec = 0.0;
        Double sumaMocyKlasniec =  0.0;
        for(Clap clap : listaKlasniec){
            i++;
            sumaCzasuKlasniec = sumaCzasuKlasniec + getTimeInSeconds(clap.getTime());
            sumaMocyKlasniec = sumaMocyKlasniec +  clap.getzAcceleration();
        }
        System.out.println("SUMA: " + sumaCzasuKlasniec + " sekund");
        System.out.println("SUMA: " + sumaMocyKlasniec + " moc");
        Double sredniaSumaCzasuKlasniec = sumaCzasuKlasniec / i;
        Double sredniaSumaMocyKlasniec = sumaMocyKlasniec / i;




// jakosc czasu !!!!
        LinkedList<Double> roznicaProcentowaCzasu = new LinkedList<>();
        for(Clap clap : listaKlasniec){
            Double klasniecieCzas = getTimeInSeconds(clap.getTime());

            if(klasniecieCzas == sredniaSumaCzasuKlasniec ){
                roznicaProcentowaCzasu.add(1.0);
            } else if (klasniecieCzas > sredniaSumaCzasuKlasniec) {
                roznicaProcentowaCzasu.add((klasniecieCzas - sredniaSumaCzasuKlasniec) / klasniecieCzas * 100);
            }else {
                roznicaProcentowaCzasu.add((sredniaSumaCzasuKlasniec - klasniecieCzas) / sredniaSumaCzasuKlasniec * 100);
            }
        }

        double jakoscCzasu = 0;
        for(int x =0; x < roznicaProcentowaCzasu.size(); x++){
            jakoscCzasu = jakoscCzasu + roznicaProcentowaCzasu.get(x);
            System.out.println("ROZNICA CZASU % " + roznicaProcentowaCzasu.get(x));
        }
        jakoscCzasu = 100 - (jakoscCzasu   / roznicaProcentowaCzasu.size());
       System.out.println("JAKOSC CZASU % " + (jakoscCzasu));


        // jakosc sily  !!!!
        LinkedList<Double> roznicaProcentowaSily = new LinkedList<>();
        for(Clap clap : listaKlasniec){
            Double silaMoc = clap.getzAcceleration();

            if(silaMoc == sredniaSumaMocyKlasniec ){
                roznicaProcentowaSily.add(1.0);
            } else if (silaMoc > sredniaSumaMocyKlasniec) {
                roznicaProcentowaSily.add((silaMoc - sredniaSumaMocyKlasniec) / silaMoc * 100);
            }else {
                roznicaProcentowaSily.add((sredniaSumaMocyKlasniec - silaMoc) / sredniaSumaMocyKlasniec * 100);
            }
        }

        double jakoscMocy = 0;
        for(int x =0; x < roznicaProcentowaSily.size(); x++){
            jakoscMocy = jakoscMocy + roznicaProcentowaSily.get(x);
            System.out.println("ROZNICA MOCY % " + roznicaProcentowaSily.get(x));
        }
        jakoscMocy = 100 - (jakoscMocy   / roznicaProcentowaSily.size()) ;
        System.out.println("JAKOSC MOCY % " + (jakoscMocy));



        double ogolnaJakosc = (jakoscCzasu + jakoscMocy) / 2;
        System.out.println("OGOLA JAKOSC: " + ogolnaJakosc);



        return (int)ogolnaJakosc;
    }




}
