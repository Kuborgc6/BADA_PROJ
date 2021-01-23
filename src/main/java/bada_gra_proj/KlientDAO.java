package bada_gra_proj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
@Repository
public class KlientDAO {
	
	@Autowired
	/* Import org.springframework.jd..Template */
	private JdbcTemplate jdbcTemplate;

	/* Constructor for jdbcTemplate */
	public KlientDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	/* List with information with data from database */
	public List<Klient> list() {
		String sql = "SELECT * FROM KLIENCI";

		List<Klient> listKlient = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Klient.class));
		return listKlient;
	}

	/* Insert */
	public void save(Klient klient) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("klienci").usingColumns("nr_klienta", "numer_kontaktu", "adres_email",
				"data_zalozenia_konta","nr_adresu");
		/**/

		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(klient);
		insertActor.execute(param);
	}

	/* Read */
	public Klient get(int nr_klienta) {
		Object[] args = { nr_klienta };
		String sql = "SELECT * FROM KLIENCI WHERE NR_KLIENTA = " + args[0];
		Klient klient = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Klient.class));
		return klient;
	}

	/* Update */
	public void update(Klient klient) {
		String sql = "UPDATE KLIENCI SET numer_kontaktu=:numer_kontaktu, adres_email=:adres_email, data_zalozenia_konta=:data_zalozenia_konta WHERE nr_klienta=:nr_klienta";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(klient);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.update(sql, param);
	}

	/* Delete */
	public void delete(int nr_klienta) {
		String sql = "DELETE FROM KLIENCI WHERE nr_klienta = ?";
		jdbcTemplate.update(sql, nr_klienta);
	}

	/* Show all klients in certain Usluga */
	public List<Klient> connectUslugi(int nr_uslugi) {
		Object[] args = { nr_uslugi };
		String sql = "select klienci.nr_klienta, klienci.NUMER_KONTAKTU, klienci.ADRES_EMAIL, klienci.DATA_ZALOZENIA_KONTA, klienci.nr_adresu from klienci\r\n"
				+ "join Klient_Usługa\r\n" + "on klienci.nr_klienta = Klient_Usługa.nr_klienta\r\n" + "join uslugi\r\n"
				+ "on Klient_Usługa.nr_uslugi = uslugi.nr_uslugi\r\n" + "where uslugi.nr_uslugi = " + args[0];

		List<Klient> listUslugaKlient = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Klient.class));
		return listUslugaKlient;
	}

	/* Show all klients in certain Operatorzy */
	public List<Klient> connectOperatorzy(int nr_operatora) {
		Object[] args = { nr_operatora };
		String sql = "select klienci.nr_klienta, klienci.NUMER_KONTAKTU, klienci.ADRES_EMAIL, klienci.DATA_ZALOZENIA_KONTA, klienci.nr_adresu from klienci\r\n"
				+ "join Klient_Usługa\r\n" + "on klienci.nr_klienta = Klient_Usługa.nr_klienta\r\n" + "join uslugi\r\n"
				+ "on Klient_Usługa.nr_uslugi = uslugi.nr_uslugi\r\n" + "join operatorzy\r\n"
				+ "on uslugi.NR_OPERATORA = operatorzy.NR_OPERATORA\r\n" + "where operatorzy.NR_OPERATORA = " + args[0];

		List<Klient> listOperatorKlient = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Klient.class));
		return listOperatorKlient;
	}

}
