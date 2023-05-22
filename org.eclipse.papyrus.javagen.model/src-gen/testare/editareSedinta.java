package testare;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import model.Angajat;
import model.MeniuLogare;
import model.MeniuManager;
import model.Sedinta;

class editareSedinta {

	@Test
	void test() {
		//Caz 1: logare si editare sedinta valida
		Angajat a = new Angajat(true, "Popescu", "Andrei", "PAndrei", "1234", 0); // verificam username si parola
		
		MeniuLogare meniuLogare = new MeniuLogare();
		meniuLogare.logare(a.getUsername(), a.getParola());
		
		assertTrue(meniuLogare.idAngajatLogat != -1);
		
		assertTrue(a.isManager());
		
		MeniuManager meniuManager = new MeniuManager();
		
		Sedinta s = new Sedinta("Ixia Innov", "Sedinta tactica", "18-05-2023", "11:00", "Andrei", 2, "B08");
		
		meniuManager.selectareSedinta(s.getIdSedinta());
		assertTrue(meniuManager.validareSedinta(s));
		
		
	
	}

}
