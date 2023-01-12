import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Clap clap = new Clap(50, 2000000000);
        Clap clap2 = new Clap(50, 1000000000);
        //Clap clap3 = new Clap(25, 2000000000);
        LinkedList<Clap> aa = new LinkedList<>();
        aa.add(clap);
        aa.add(clap2);
       // aa.add(clap3);
        QualityClass qualityClass = new QualityClass(aa);
        qualityClass.calculateQuality();

    }


}