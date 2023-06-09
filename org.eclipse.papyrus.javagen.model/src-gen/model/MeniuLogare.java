// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package model;

import java.util.Scanner;

public class MeniuLogare extends Meniu {
    
    public MeniuLogare() {
        super();
    }

    public boolean logare(String username, String parola) {
        for(int i = 0; i < nrAngajati; i++) {
            if(super.angajati[i].getUsername().equals(username) && super.angajati[i].getParola().equals(parola)) {
                this.idAngajatLogat = i;
                return true;
            }
        }
        return false;
    }
    
    public void afisare() {
        this.afisareOptiuni();
    }
    
    @SuppressWarnings("resource")
	private void afisareOptiuni() {
        System.out.println("Alege o optiune:");
        System.out.println("X - Intoarcere la meniul precedent");
        System.out.println("Y - Logare");

        Scanner kbd = new Scanner(System.in);
        String optiune = kbd.nextLine().toUpperCase();

        switch(optiune) {
            case "Y":
                System.out.println("Introduceti username:");
                String username = kbd.nextLine();
                System.out.println("Introduceti parola:");
                String parola = kbd.nextLine();
                boolean succesLogare = this.logare(username, parola);
                if (!succesLogare) {
                    System.out.println("Username sau parola incorecte. Incercati din nou.");
                    afisareOptiuni();
                }
                super.urmatorulMeniu = TipuriMeniu.MeniuAngajat;
                break;
            case "X":
                return;
            default:
                System.out.println("Optiune invalida. Te rog sa introduci 'X' sau 'Y'.");
                afisareOptiuni();
        }
        kbd.close();
    }
}