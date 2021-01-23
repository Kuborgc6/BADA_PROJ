package bada_gra_proj;

public class Klient_usluga {
	private int nr_klienta;
	private int nr_uslugi;

	public Klient_usluga() {

	}

	public Klient_usluga(int nr_klienta, int nr_uslugi) {
		super();
		this.nr_klienta = nr_klienta;
		this.nr_uslugi = nr_uslugi;
	}

	public int getNr_klienta() {
		return nr_klienta;
	}

	public void setNr_klienta(int nr_klienta) {
		this.nr_klienta = nr_klienta;
	}

	public int getNr_uslugi() {
		return nr_uslugi;
	}

	public void setNr_uslugi(int nr_uslugi) {
		this.nr_uslugi = nr_uslugi;
	}

	@Override
	public String toString() {
		return "Klient_usluga [nr_klienta=" + nr_klienta + ", nr_uslugi=" + nr_uslugi + "]";
	}
	
}
