package bada_gra_proj;

public class Login {
	private int nr_klienta;

	public Login() {

	}

	public Login(int nr_klienta) {
		super();
		this.nr_klienta = nr_klienta;
	}

	/* Getters and Setters - methods for fields access */

	public int getNr_klienta() {
		return nr_klienta;
	}

	public void setNr_klienta(int nr_klienta) {
		this.nr_klienta = nr_klienta;
	}

	@Override
	public String toString() {
		return "Login [nr_klienta=" + nr_klienta + "]";
	}

}
