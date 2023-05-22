// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package model;

import java.util.Scanner;

public class MeniuSedinte extends Meniu {
	private Sedinta sedintaCurenta;
	
	public MeniuSedinte() {
		super();
	}
	
	public void afisare() {
		System.out.println("Aici sunt informatiile complete despre sedintele la care veti participa:");
		
		for(Sedinta s : super.sedinte) {
			s.afisare();
		}
		
		System.out.println("");
		
		this.afisareOptiuni();
	}

	@SuppressWarnings("resource")
	private void afisareOptiuni() {
	    System.out.println("Alege o optiune:");
	    System.out.println("X - Intoarcere la meniul precedent");
	    System.out.println("Y - Selecteaza o sedinta");

	    Scanner kbd = new Scanner(System.in);
	    String optiune = kbd.nextLine().toUpperCase();

	    switch(optiune) {
	        case "Y":
	            System.out.println("Introduceti ID-ul uneia dintre sedintele afisate mai sus:");
	            try {
	                int idSedinta = Integer.parseInt(kbd.nextLine());
	                this.selectareSedinta(idSedinta);
		            super.urmatorulMeniu = TipuriMeniu.MeniuGestionareSedinta;
	            } catch (NumberFormatException e) {
	                System.out.println("Input invalid. Va rugam introduceti un numar intreg.");
	                afisareOptiuni();
	            } catch (IllegalArgumentException e) {
	                System.out.println(e.getMessage());
	                afisareOptiuni();
	            }
	            break;
	        case "X":
	            super.urmatorulMeniu = TipuriMeniu.MeniuDefault;
	            return;
	        default:
	            System.out.println("Optiune invalida. Te rog sa introduci 'X' sau 'Y'.");
	            afisareOptiuni();
	    }
	    kbd.close();
	}


	public void selectareSedinta(int idSedinta) throws IllegalArgumentException {
	    this.sedintaCurenta = super.getSedintaByID(idSedinta);
	    
	    if (this.sedintaCurenta == null) {
	        throw new IllegalArgumentException("Nu exista nicio sedinta cu ID-ul dat.");
	    }
	}

	
	public Sedinta getSedinta() {
		return sedintaCurenta;
	}
}
