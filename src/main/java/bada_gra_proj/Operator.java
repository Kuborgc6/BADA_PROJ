package bada_gra_proj;

public class Operator {
	private int nr_operatora;
	private String nazwa;
	private String data_zalozenia;
	private String kraj_centrali;
	private String NIP;
	
	
	
	public Operator() {

	}



	public Operator(int nr_operatora, String nazwa, String data_zalozenia, String kraj_centrali, String NIP) {
		super();
		this.nr_operatora = nr_operatora;
		this.nazwa = nazwa;
		this.data_zalozenia = data_zalozenia;
		this.kraj_centrali = kraj_centrali;
		this.NIP = NIP;
	}


	/* Getters and Setters - methods for fields access */
	public int getNr_operatora() {
		return nr_operatora;
	}



	public void setNr_operatora(int nr_operatora) {
		this.nr_operatora = nr_operatora;
	}



	public String getNazwa() {
		return nazwa;
	}



	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}



	public String getKraj_centrali() {
		return kraj_centrali;
	}



	public void setKraj_centrali(String kraj_centrali) {
		this.kraj_centrali = kraj_centrali;
	}



	public String getNIP() {
		return NIP;
	}



	public void setNIP(String nIP) {
		NIP = nIP;
	}


	
	public String getData_zalozenia() {
		return data_zalozenia;
	}



	public void setData_zalozenia(String data_zalozenia) {
		this.data_zalozenia = data_zalozenia;
	}



	@Override
	public String toString() {
		return "Operator [nr_operatora=" + nr_operatora + ", nazwa=" + nazwa + ", data_zalozenia=" + data_zalozenia
				+ ", kraj_centrali=" + kraj_centrali + ", NIP=" + NIP + "]";
	}


	
	
}

