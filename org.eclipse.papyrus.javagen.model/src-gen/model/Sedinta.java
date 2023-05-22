// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package model;

public class Sedinta {
	private String titlu;
	private String descriere;
	private String data;
	private String ora;
	private String status;
	private String sala;
	
	private final String manager;
	private final int idSedinta;
	
	private int nrPropuneri;
	private String[] propuneriData = new String[100];
	private String[] propuneriOra = new String[100];
	
	private int nrAngajati = 0;
	private Angajat[] angajat = new Angajat[100];
	
	private static int nrSedinte = 0;
	
	public Sedinta(String titlu, String descriere, String data, String ora, String manager, String sala) {
		this(titlu, descriere, data, ora, manager, nrSedinte, sala);
	}
	
	public Sedinta(String titlu, String descriere, String data, String ora, String manager, int idSedinta, String sala) {
		this.titlu = titlu;
		this.descriere = descriere;
		this.data = data;
		this.ora = ora;
		this.manager = manager;
		this.idSedinta = idSedinta;
		this.sala = sala;
		this.status = "pending";
		
		nrSedinte++;
	}
	
	// Lista interminabila de Gertteri si Setteri
	public int getIdSedinta() {
		return idSedinta;
	}
	
	public void setTitlu(String titlu) {
		this.titlu = titlu;
	}

	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTitlu() {
		return titlu;
	}

	public String getDescriere() {
		return descriere;
	}

	public String getData() {
		return data;
	}

	public String getOra() {
		return ora;
	}

	public String getManager() {
		return manager;
	}

	public String getSala() {
		return sala;
	}
	
	// Adaugare o propunere noua de data si timp
	public void propunereNoua(String dataNoua, String oraNoua) {
		propuneriData[nrPropuneri] = dataNoua;
		propuneriOra[nrPropuneri] = oraNoua;
		nrPropuneri++;
	}
	
	// Adaugare un nou participant
	public void adaugareAngajatSedinta(Angajat a) {
		angajat[nrAngajati++] = a;
	}

	// Functiile de afisare normnala si afisare drfept notificare
	public void afisare() {
	    System.out.println("---------------------------------------------------");
	    System.out.println("Titlu Sedinta: " + titlu);
	    System.out.println("Descriere: " + descriere);
	    System.out.println("Data: " + data);
	    System.out.println("Ora: " + ora);
	    System.out.println("Sala: " + sala);
	    System.out.println("Status: " + status);
	    System.out.println("---------------------------------------------------");
	}
	
	public void afisareDreptNotificare() {
	    System.out.println("-----------------------------------------------------------------------");
		System.out.println("Sedinta noua creata de " + this.manager + " cu titlul " + this.titlu);
	    System.out.println("-----------------------------------------------------------------------");
	}

	// Verificam daca este in pending
	public boolean isPending() {
		return this.status.toUpperCase().equals("PENDING");
	}
	
	// Verificam daca participantul cu ID-ul respectiv este participant ala sedintei
	public boolean isParticipant(int id) {
		for(Angajat a : this.angajat) {
			if(a.getID() == id) {
				return true;
			}
		}
		return false;
	}
}
