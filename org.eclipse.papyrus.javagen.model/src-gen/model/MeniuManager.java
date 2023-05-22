// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package model;

import java.util.Scanner;

public class MeniuManager extends MeniuAngajat {
    private int idSedintaSelectata;
    
    public MeniuManager() {
        super();
    }
    
    private void afisareOptiuni() {
        System.out.println("Alege o optiune:");
        System.out.println("1 - Selectare Sedinta");
        System.out.println("2 - Editare Sedinta");
        System.out.println("3 - Inregistrare Angajat Nou");
        System.out.println("X - Intoarcere la meniul anterior");

        Scanner kbd = new Scanner(System.in);
        String optiune = kbd.nextLine().toUpperCase();

        switch(optiune) {
            case "1":
                System.out.println("Introduceti ID-ul sedintei pe care doriti sa o selectati:");
                int idSedinta = kbd.nextInt();
                this.selectareSedinta(idSedinta);
                break;
            case "2":
                kbd.nextLine(); // consumes the '\n' left from nextInt()
                System.out.println("Introduceti noile detalii pentru sedinta:");
                System.out.print("Titlu: ");
                String titlu = kbd.nextLine();
                System.out.print("Descriere: ");
                String descriere = kbd.nextLine();
                System.out.print("Data: ");
                String data = kbd.nextLine();
                System.out.print("Ora: ");
                String ora = kbd.nextLine();
                System.out.print("Sala: ");
                String sala = kbd.nextLine();
                this.editareSedinta(titlu, descriere, data, ora, sala);
                break;
            case "3":
                kbd.nextLine(); // consumes the '\n' left from nextInt()
                System.out.println("Introduceti detalii pentru noul angajat:");
                System.out.print("Nume: ");
                String nume = kbd.nextLine();
                System.out.print("Prenume: ");
                String prenume = kbd.nextLine();
                System.out.print("Username: ");
                String username = kbd.nextLine();
                System.out.print("Parola: ");
                String parola = kbd.nextLine();
                System.out.print("Rol (Introduceti '1' pentru manager si '0' pentru angajat): ");
                boolean isManager = (kbd.nextInt() == 1);
                Angajat a = new Angajat(isManager, nume, prenume, username, parola);
                this.inregistrareAngajatNou(a);
                break;
            case "X":
                return;
            default:
                System.out.println("Optiune invalida. Te rog sa introduci '1', '2', '3' sau 'X'.");
                afisareOptiuni();
        }
        kbd.close();
    }



    public void afisare() {
        this.afisareOptiuni();
    }

    private void inregistrareAngajatNou(Angajat a) {
        super.angajati[nrAngajati++] = a;
    }
    
    public void editareSedinta(String titlu, String descriere, String data, String ora, String sala) {
        super.sedinte[idSedintaSelectata].setTitlu(titlu);
        super.sedinte[idSedintaSelectata].setDescriere(descriere);
        super.sedinte[idSedintaSelectata].setData(data);
        super.sedinte[idSedintaSelectata].setOra(ora);
        super.sedinte[idSedintaSelectata].setSala(sala);
    }

    public void selectareSedinta(int idSedintaSelectata) {
        this.idSedintaSelectata = idSedintaSelectata;
    }
}
