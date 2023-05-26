package testare;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import model.Angajat;
import model.MeniuLogare;
import model.MeniuManager;
import model.Sedinta;

class editareSedinta {

	@Test
	void test1() {
		//Caz 1: logare si editare sedinta valida
		Angajat a = new Angajat(true, "Popescu", "Andrei", "PAndrei", "1234", 0); // verificam username si parola
		
		MeniuLogare meniuLogare = new MeniuLogare();
		meniuLogare.logare(a.getUsername(), a.getParola());
		
		assertTrue(meniuLogare.idAngajatLogat != -1); //Parola si username valide, mergem mai departe
		
		assertTrue(a.isManager());
		
		MeniuManager meniuManager = new MeniuManager();
		
		Sedinta s = new Sedinta("Ixia Innov", "Sedinta tactica", "18-05-2023", "11:00", "Andrei", 2, "B08"); //sedinta editata
		
		meniuManager.selectareSedinta(s.getIdSedinta()); //am selectat sedinta
		assertTrue(meniuManager.validareSedinta(s));  //am validat schimbarile
		meniuManager.editareSedinta(s.getTitlu(), s.getDescriere(), s.getData(), s.getOra(), s.getSala()); //am incarcat schimbarile in sistem
	}
	
	@Test
	void test2() {
		//Caz 2: logare esuata
		Angajat a = new Angajat(true, "Popescu", "Andrei", "PAndrei", "134", 0); // verificam username si parola
		
		MeniuLogare meniuLogare = new MeniuLogare();
		meniuLogare.logare(a.getUsername(), a.getParola());
		
		assertFalse(meniuLogare.idAngajatLogat != -1); // parola invalida => logare esuata
	}
	
	@Test
	void test3() {
		//Caz 3: logare valida, dar angajatul nu este manager
		Angajat a = new Angajat(false, "Ionescu", "Andrei", "IAndrei", "1334", 1); // verificam username si parola
		
		MeniuLogare meniuLogare = new MeniuLogare();
		meniuLogare.logare(a.getUsername(), a.getParola());
		
		assertTrue(meniuLogare.idAngajatLogat != -1); // parola valida
		
		assertFalse(a.isManager()); // utilizatorul nu este manager
	}
	
	@Test
	void test4() {
		//Caz 4: logare valida, sedinta invalida
		Angajat a = new Angajat(true, "Andrei", "Andrei", "AndreiAndrei", "Andrei", 6); // verificam username si parola
		
		MeniuLogare meniuLogare = new MeniuLogare();
		meniuLogare.logare(a.getUsername(), a.getParola());
		
		assertTrue(meniuLogare.idAngajatLogat != -1); 
		
		assertTrue(a.isManager());
		
		MeniuManager meniuManager = new MeniuManager();
		
		Sedinta s = new Sedinta("Ixia Innov", "Sedinta tactica", "18-05-2023", "07:00", "Andrei", 2, "B08"); //sedinta editata
		
		meniuManager.selectareSedinta(s.getIdSedinta()); //am selectat sedinta
		assertFalse(meniuManager.validareSedinta(s));  // schimbarile nu sunt valide
	}

}
