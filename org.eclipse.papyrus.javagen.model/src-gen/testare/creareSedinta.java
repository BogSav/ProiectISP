package testare;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Angajat;
import model.MeniuLogare;
import model.MeniuManager;
import model.Sedinta;
import model.MeniuCreareSedinte;

class creareSedinta {
	/////////////////////////////////////////////////////
	// TEST VERIFICARE DATE NECOMPLETATE
	/////////////////////////////////////////////////////
	@Test
	void testDateNecompletate() {
		Angajat a = new Angajat(true, "Popescu", "Andrei", "PAndrei", "1234", 0);	
		MeniuLogare meniuLogare = new MeniuLogare();
		meniuLogare.logare(a.getUsername(), a.getParola());
		assertTrue(meniuLogare.idAngajatLogat != -1);
		assertTrue(a.isManager());
		
		MeniuCreareSedinte menu = new MeniuCreareSedinte();
		MeniuCreareSedinte.Flags flags = menu.creareSedinta("", "", "", "", "", "");
		
		// Verificam daca intr-adevar toate campurile au fost gasite drept necompletate
		assertTrue(flags.has(MeniuCreareSedinte.Flags.DATA_NECOMPLETATA) && 
				flags.has(MeniuCreareSedinte.Flags.TIMP_NECOMPLETAT) &&
				flags.has(MeniuCreareSedinte.Flags.DESCRIERE_INCORECTA) &&
				flags.has(MeniuCreareSedinte.Flags.TITLU_INCORECT) &&
				flags.has(MeniuCreareSedinte.Flags.SALA_NECOMPLETATA));
	}

	/////////////////////////////////////////////////////
	// TEST VERIFICARE DATA GRESITA
	/////////////////////////////////////////////////////
	@Test
	void testDataGresita() {
		Angajat a = new Angajat(true, "Popescu", "Andrei", "PAndrei", "1234", 0); // verificam username si parola		
		MeniuLogare meniuLogare = new MeniuLogare();
		meniuLogare.logare(a.getUsername(), a.getParola());
		assertTrue(meniuLogare.idAngajatLogat != -1);
		assertTrue(a.isManager());
		
		MeniuCreareSedinte menu = new MeniuCreareSedinte();
		MeniuCreareSedinte.Flags flags = menu.creareSedinta("Titlu sedinta", "O descriere foarte explicita", "32-14-2027", "", "", "");
		
		// Verificam mai intai daca titlu, descriere si data au fost completate
		assertFalse(flags.has(MeniuCreareSedinte.Flags.TITLU_INCORECT) || 
				flags.has(MeniuCreareSedinte.Flags.DESCRIERE_INCORECTA) ||
				flags.has(MeniuCreareSedinte.Flags.DATA_NECOMPLETATA));
		
		// Verificam daca ziua, luna si anul au fost gasite incorecte
		assertTrue(flags.has(MeniuCreareSedinte.Flags.ZI_INCORECTA) && 
				flags.has(MeniuCreareSedinte.Flags.LUNA_INCORECTA) && 
				flags.has(MeniuCreareSedinte.Flags.AN_INCORECT));
	}
	
	/////////////////////////////////////////////////////
	// TEST VERIFICARE DATA IN WEEKEND
	/////////////////////////////////////////////////////
	@Test
	void testWeekend() {
		Angajat a = new Angajat(true, "Popescu", "Andrei", "PAndrei", "1234", 0); // verificam username si parola		
		MeniuLogare meniuLogare = new MeniuLogare();
		meniuLogare.logare(a.getUsername(), a.getParola());
		assertTrue(meniuLogare.idAngajatLogat != -1);
		assertTrue(a.isManager());
		
		MeniuCreareSedinte menu = new MeniuCreareSedinte();
		MeniuCreareSedinte.Flags flags = menu.creareSedinta("Titlu sedinta", "O descriere foarte explicita", "03-06-2023", "", "", "");
		
		// Verificam daca titlu si descrierea sunt corecte
		assertFalse(flags.has(MeniuCreareSedinte.Flags.TITLU_INCORECT) || 
				flags.has(MeniuCreareSedinte.Flags.DESCRIERE_INCORECTA));
		
		// Verificam daca data este corecta si completata
		assertFalse(flags.has(MeniuCreareSedinte.Flags.ZI_INCORECTA) ||
				flags.has(MeniuCreareSedinte.Flags.LUNA_INCORECTA) || 
				flags.has(MeniuCreareSedinte.Flags.AN_INCORECT) ||
				flags.has(MeniuCreareSedinte.Flags.DATA_NECOMPLETATA));
		
		// Verificam daca data a fost gasita in weekend
		assertTrue(flags.has(MeniuCreareSedinte.Flags.WEEKEND));
	}
	
	/////////////////////////////////////////////////////
	// TEST VERIFICARE TIMP GRESIT
	/////////////////////////////////////////////////////
	@Test
	void testTimpGresit() {
		Angajat a = new Angajat(true, "Popescu", "Andrei", "PAndrei", "1234", 0); // verificam username si parola		
		MeniuLogare meniuLogare = new MeniuLogare();
		meniuLogare.logare(a.getUsername(), a.getParola());
		assertTrue(meniuLogare.idAngajatLogat != -1);
		assertTrue(a.isManager());
		
		MeniuCreareSedinte menu = new MeniuCreareSedinte();
		MeniuCreareSedinte.Flags flags = menu.creareSedinta("Titlu sedinta", "O descriere foarte explicita", "12-09-2023", "26:92", "", "");
		
		// Verificam daca titlu si descrierea sunt corecte
		assertFalse(flags.has(MeniuCreareSedinte.Flags.TITLU_INCORECT) || 
				flags.has(MeniuCreareSedinte.Flags.DESCRIERE_INCORECTA));
		
		// Verificam daca data este corecta si completata
		assertFalse(flags.has(MeniuCreareSedinte.Flags.ZI_INCORECTA) ||
				flags.has(MeniuCreareSedinte.Flags.LUNA_INCORECTA) || 
				flags.has(MeniuCreareSedinte.Flags.AN_INCORECT) ||
				flags.has(MeniuCreareSedinte.Flags.DATA_NECOMPLETATA));
		
		// Verificam daca timpul a fost gasit drept completat
		assertFalse(flags.has(MeniuCreareSedinte.Flags.TIMP_NECOMPLETAT));
		
		// Verificam daca ora si minutul au fost gasite drept incorecte
		assertTrue(flags.has(MeniuCreareSedinte.Flags.ORA_INCORECTA) && 
				flags.has(MeniuCreareSedinte.Flags.MINUT_INCORECT));
	}
	
	/////////////////////////////////////////////////////
	// TEST VERIFICARE ANGAJATI INSUFICIENTI
	/////////////////////////////////////////////////////
	@Test
	void testAngajatiInsuficienti() {
		Angajat a = new Angajat(true, "Popescu", "Andrei", "PAndrei", "1234", 0); // verificam username si parola		
		MeniuLogare meniuLogare = new MeniuLogare();
		meniuLogare.logare(a.getUsername(), a.getParola());
		assertTrue(meniuLogare.idAngajatLogat != -1);
		assertTrue(a.isManager());
		
		MeniuCreareSedinte menu = new MeniuCreareSedinte();
		MeniuCreareSedinte.Flags flags = menu.creareSedinta("Titlu sedinta", "O descriere foarte explicita", "12-09-2023", "12:32", "Sala2", "Andrei", null);
		
		assertFalse(flags.getValue() == 0);
		assertTrue(flags.has(MeniuCreareSedinte.Flags.ANGAJATI_INSUFICIENTI));
	}
	
	/////////////////////////////////////////////////////
	// TEST VERIFICARE CREARE SEDINTA COMPLET
	/////////////////////////////////////////////////////
	@Test
	void testCreareSedinta() {
		Angajat a = new Angajat(true, "Popescu", "Andrei", "PAndrei", "1234", 0); // verificam username si parola		
		MeniuLogare meniuLogare = new MeniuLogare();
		meniuLogare.logare(a.getUsername(), a.getParola());
		assertTrue(meniuLogare.idAngajatLogat != -1);
		assertTrue(a.isManager());
		
		Angajat[] angajati = new Angajat[3];
		angajati[0] = new Angajat(true, "Popescu", "Andrei", "PAndrei", "1234", 0);
		angajati[1] = new Angajat(false, "Ionescu", "Andrei", "IAndrei", "1334", 1);
		angajati[2] = new Angajat(false, "Matei", "Corvin", "MCRV", "1111", 2);
		
		MeniuCreareSedinte menu = new MeniuCreareSedinte();
		MeniuCreareSedinte.Flags flags = menu.creareSedinta("Titlu sedinta", "O descriere foarte explicita", "12-09-2023", "12:32", "Sala2", "Andrei", angajati);
		
		assertTrue(flags.getValue() == 0);
	}
}
