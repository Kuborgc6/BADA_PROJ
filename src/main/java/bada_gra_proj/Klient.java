package bada_gra_proj;

public class Klient {
	private int nr_klienta;
	private String numer_kontaktu;
	private String adres_email;
	private String data_zalozenia_konta;
	private int nr_adresu;

	public Klient() {

	}

	public Klient(int nr_klienta, String numer_kontaktu, String adres_email, String data_zalozenia_konta, int nr_adresu) {
		super();
		this.nr_klienta = nr_klienta;
		this.numer_kontaktu = numer_kontaktu;
		this.adres_email = adres_email;
		this.data_zalozenia_konta = data_zalozenia_konta;
		this.nr_adresu = 1;
	}

	/* Getters and Setters - methods for fields access */
	public int getNr_klienta() {
		return nr_klienta;
	}

	public void setNr_klienta(int nr_klienta) {
		this.nr_klienta = nr_klienta;
	}

	public String getNumer_kontaktu() {
		return numer_kontaktu;
	}

	public void setNumer_kontaktu(String numer_kontaktu) {
		this.numer_kontaktu = numer_kontaktu;
	}

	public String getAdres_email() {
		return adres_email;
	}

	public void setAdres_email(String adres_email) {
		this.adres_email = adres_email;
	}

	public String getData_zalozenia_konta() {
		return data_zalozenia_konta;
	}

	public void setData_zalozenia_konta(String data_zalozenia_konta) {
		this.data_zalozenia_konta = data_zalozenia_konta;
	}

	public int getNr_adresu() {
		return nr_adresu;
	}

	public void setNr_adresu(int nr_adresu) {
		this.nr_adresu = nr_adresu;
	}

	@Override
	public String toString() {
		return "Klient [nr_klienta=" + nr_klienta + ", numer_kontaktu=" + numer_kontaktu + ", adres_email="
				+ adres_email + ", data_zalozenia_konta=" + data_zalozenia_konta + ", nr_adresu=" + nr_adresu + "]";
	}

}