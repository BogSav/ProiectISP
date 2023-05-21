// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package model;

public class MeniuGestionareSedinta extends Meniu {
	private int idSedintaSelectata; //am salvata sedinta pe care o modific

	public void afisare() {
		this.afisareOptiuni();
		for(int i=0; i<nrSedinte; i++) {
			sedinta[i].afisare();
		}
	}

	private void afisareOptiuni() {
		System.out.println("Selectare sedinta");
		System.out.println("Acceptare sedinta");
		System.out.println("Refuz, propunere data si ora nou:");
	}

	private void alegereDataOraNoua(String data, String ora) {
		sedinta[idSedintaSelectata].propunereNoua(data, ora);
	}

	private void acceptareSedinta(String status) {
		sedinta[idSedintaSelectata].setStatus(status);
	}

	public void SelectareSedinta(int idSedintaSelectata) {
		this.idSedintaSelectata = idSedintaSelectata; //modific sedinta selectata de angajat pentru verificare
	}
	
	
}