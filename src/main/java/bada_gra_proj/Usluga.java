package bada_gra_proj;

public class Usluga {
	private int nr_uslugi;
	private String nazwa;
	private int koszt;
	private int nr_operatora;

	public Usluga() {

	}

	public Usluga(int nr_uslugi, String nazwa, int koszt, int nr_operatora) {
		super();
		this.nr_uslugi = nr_uslugi;
		this.nazwa = nazwa;
		this.koszt = koszt;
		this.nr_operatora = nr_operatora;
	}

	/* Getters and Setters - methods for fields access */

	public int getNr_uslugi() {
		return nr_uslugi;
	}

	public void setNr_uslugi(int nr_uslugi) {
		this.nr_uslugi = nr_uslugi;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public int getKoszt() {
		return koszt;
	}

	public void setKoszt(int koszt) {
		this.koszt = koszt;
	}

	public int getNr_operatora() {
		return nr_operatora;
	}

	public void setNr_operatora(int nr_operatora) {
		this.nr_operatora = nr_operatora;
	}

	@Override
	public String toString() {
		return "Usluga [nr_uslugi=" + nr_uslugi + ", nazwa=" + nazwa + ", koszt=" + koszt + ", nr_operatora="
				+ nr_operatora + "]";
	}
}
