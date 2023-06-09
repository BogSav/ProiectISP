package testare;

import static org.junit.jupiter.api.Assertions.*;
import model.Angajat;
import model.MeniuGestionareSedinta;
import model.MeniuLogare;
import model.Sedinta;

import org.junit.jupiter.api.Test;

class gestionareSedinta {

	@Test
	void testAcceptare() {
		Angajat a = new Angajat(false, "Ionescu", "Andrei", "IAndrei", "1334", 1);
		MeniuLogare ml = new MeniuLogare();
		ml.logare(a.getUsername(), a.getParola());
		assertTrue(ml.idAngajatLogat != -1); // verificam ca angajatul este logat corect
		
		MeniuGestionareSedinta mgs = new MeniuGestionareSedinta();
		Sedinta s1 = new Sedinta("NXP Cup", "Discutie despre organizarea", "18-05-2023", "16:00", "Popescu", 0, "C310");
		
		mgs.selectareSedinta(s1.getIdSedinta()); // angajatul selecteaza sedinta din meniul gestionare getIdSedinte()
		
		assertTrue(mgs.getSedinte()[mgs.getIdSedintaSelectata()].isPending()); //verificam ca sedinta nu este inca acceptata sau refuzata
		
		mgs.acceptareSedinta("accepted"); // angajatul accepta sedinta selectata din meniul gestionare sedinte
		assertFalse(mgs.getSedinte()[mgs.getIdSedintaSelectata()].isPending()); // verific daca acceptarea sedintei a functionat, adica sedinta nu mai este in pending
		
	}
	
	@Test
	void testRefuzare() {
		Angajat a = new Angajat(false, "Iliescu", "Alexandru", "IlieA", "4321", 5);
		MeniuLogare ml = new MeniuLogare();
		ml.logare(a.getUsername(), a.getParola());
		assertTrue(ml.idAngajatLogat != -1); // verificam ca angajatul este logat corect
		
		MeniuGestionareSedinta mgs = new MeniuGestionareSedinta();
		Sedinta s1 = new Sedinta("NXP Cup", "Discutie despre organizarea", "18-05-2023", "16:00", "Popescu", 0, "C310");
		
		mgs.selectareSedinta(s1.getIdSedinta()); // angajatul selecteaza sedinta din meniul gestionare getIdSedinte()
		
		assertTrue(mgs.getSedinte()[mgs.getIdSedintaSelectata()].isPending()); //verificam ca sedinta nu este inca acceptata sau refuzata
		
		mgs.acceptareSedinta("declined"); // angajatul refuza sedinta selectata din meniul gestionare sedinte
		assertFalse(mgs.getSedinte()[mgs.getIdSedintaSelectata()].isPending());
		
		String dataPropusa = "22-05-2023";
		String oraPropusa = "16:00";
		
		Sedinta s2 = new Sedinta("NXP Cup", "Discutie despre organizarea", dataPropusa, oraPropusa, "Popescu", 0, "C310");
		
		assertTrue(mgs.validareSedinta(s2)); // verificam ca data si ora propusa sunt valide
		
		mgs.alegereDataOraNoua(dataPropusa, oraPropusa); // salvam propunerile in noile detalii ale sedintei
		
	}
	
	@Test
	void testRefuzarePropunereInvalida() {
		Angajat a = new Angajat(false, "Iliescu", "Alexandru", "IlieA", "4321", 5);
		MeniuLogare ml = new MeniuLogare();
		ml.logare(a.getUsername(), a.getParola());
		assertTrue(ml.idAngajatLogat != -1); // verificam ca angajatul este logat corect
		
		MeniuGestionareSedinta mgs = new MeniuGestionareSedinta();
		Sedinta s1 = new Sedinta("NXP Cup", "Discutie despre organizarea", "18-05-2023", "16:00", "Popescu", 0, "C310");
		
		mgs.selectareSedinta(s1.getIdSedinta()); // angajatul selecteaza sedinta din meniul gestionare sedinte
		
		assertTrue(mgs.getSedinte()[mgs.getIdSedintaSelectata()].isPending()); //verificam ca sedinta nu este inca acceptata sau refuzata
		
		mgs.acceptareSedinta("declined"); // angajatul refuza sedinta selectata din meniul gestionare sedinte
		assertFalse(mgs.getSedinte()[mgs.getIdSedintaSelectata()].isPending());
		
		String dataPropusa = "21-05-2023"; //data este invalida (in weekend)
		String oraPropusa = "16:00";
		
		Sedinta s2 = new Sedinta("NXP Cup", "Discutie despre organizarea", dataPropusa, oraPropusa, "Popescu", 0, "C310");
		
		assertFalse(mgs.validareSedinta(s2)); //verificam ca data si ora propusa sunt valide (aici invalide)
		
		mgs.alegereDataOraNoua(dataPropusa, oraPropusa); // salvam propunerile in noile detalii ale sedintei
		
	}
	
	@Test
	void testLogareInvalida() {
		Angajat a = new Angajat(false, "Ionescu", "Andrei", "IAndrei", "1234", 1); //parola este incorecta (vezi baza de date)
		MeniuLogare ml = new MeniuLogare();
		ml.logare(a.getUsername(), a.getParola());
		assertFalse(ml.idAngajatLogat != -1); // verificam daca angajatul este logat corect
	}
	
	@Test
	void testSelectareSedintaDejaAcceptata() {
		Angajat a = new Angajat(false, "Sandu", "Dorel", "DoruS", "1222", 3);
		MeniuLogare ml = new MeniuLogare();
		ml.logare(a.getUsername(), a.getParola());
		assertTrue(ml.idAngajatLogat != -1); // verificam ca angajatul este logat corect
		
		MeniuGestionareSedinta mgs = new MeniuGestionareSedinta();
		Sedinta s1 = new Sedinta("NXP Cup", "Discutie despre organizarea", "18-05-2023", "16:00", "Popescu", 0, "C310");
		
		mgs.selectareSedinta(s1.getIdSedinta()); // angajatul selecteaza sedinta din meniul gestionare sedinte
		mgs.getSedinte()[mgs.getIdSedintaSelectata()].setStatus("accepted"); // sedinta este acceptata
		assertFalse(mgs.getSedinte()[mgs.getIdSedintaSelectata()].isPending()); //verificam ca sedinta nu este inca acceptata sau refuzata (aici este deja)
		
	}
	
	@Test
	void testSelectareSedintaDejaRefuzata() {
		Angajat a = new Angajat(false, "Sandu", "Dorel", "DoruS", "1222", 3);
		MeniuLogare ml = new MeniuLogare();
		ml.logare(a.getUsername(), a.getParola());
		assertTrue(ml.idAngajatLogat != -1); // verificam ca angajatul este logat corect
		
		MeniuGestionareSedinta mgs1 = new MeniuGestionareSedinta();
		Sedinta s1 = new Sedinta("NXP Cup", "Discutie despre organizarea", "18-05-2023", "16:00", "Popescu", 0, "C310");
		
		mgs1.selectareSedinta(s1.getIdSedinta()); // angajatul selecteaza sedinta din meniul gestionare sedinte
		mgs1.getSedinte()[mgs1.getIdSedintaSelectata()].setStatus("declined"); // sedinta este refuzata
		assertFalse(mgs1.getSedinte()[mgs1.getIdSedintaSelectata()].isPending()); //verificam ca sedinta nu este inca acceptata sau refuzata (aici este deja)
		
	}

}
