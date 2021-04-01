package neigesoleil.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Class: InputData
 * Les méthodes static de cette classe remplacent la classe Scanner
 * Nous n'avons pas à les instancier
 * TODO: Refactor à faire -> enlever la répétition de code
 */
public class InputData {
    private static BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));

    //Scanner : nextInt ()
    public static int saisirInt()  {
        int nb = 0;
        String chaine ="";
        boolean ok = false ;
        do {
            ok = false;
            try {
                chaine = clavier.readLine();
                nb = Integer.parseInt (chaine);
                ok = true;
            }
            catch (IOException exp) {
                System.out.println("Erreur de lecture sur le clavier");
            }
            catch (NumberFormatException exp) {
                System.out.println("Format du nombre invalide");
            }
        }while(ok==false);
        return nb;
    }
    //Scanner : nextFloat ()
    public static float saisirFloat()  {
        float nb = 0;
        String chaine ="";
        boolean ok = false ;
        do {
            ok = false;
            try {
                chaine = clavier.readLine();
                nb = Float.parseFloat (chaine);
                ok = true;
            }
            catch (IOException exp) {
                System.out.println("Erreur de lecture sur le clavier");
            }
            catch (NumberFormatException exp) {
                System.out.println("Format du nombre invalide");
            }
        }while(ok==false);
        return nb;
    }
    //Scanner : nextDouble ()
    public static double saisirDouble()  {
        double nb = 0;
        String chaine ="";
        boolean ok = false ;
        do {
            ok = false;
            try {
                chaine = clavier.readLine();
                nb = Double.parseDouble (chaine);
                ok = true;
            }
            catch (IOException exp) {
                System.out.println("Erreur de lecture sur le clavier");
            }
            catch (NumberFormatException exp) {
                System.out.println("Format du nombre invalide");
            }
        }while(ok==false);
        return nb;
    }
    //Scanner : next ()
    public static String saisirString()  {
        String chaine ="";
        try {
            chaine = clavier.readLine();
        }
        catch (IOException exp) {
            System.out.println("Erreur de lecture sur le clavier");
        }
        return chaine;
    }
    //Scanner : nextChar ()
    public static char saisirChar()  {
        String chaine ="";
        try {
            chaine = clavier.readLine();
        }
        catch (IOException exp) {
            System.out.println("Erreur de lecture sur le clavier");
        }
        return chaine.charAt(0);
    }
    public static void afficher (String message)
    {
        System.out.println(message);
    }
}
