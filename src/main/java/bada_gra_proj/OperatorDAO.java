package bada_gra_proj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository

public class OperatorDAO {
	
	@Autowired
	/* Import org.springframework.jd..Template */
	private JdbcTemplate jdbcTemplate;
	
	
	/* Constructor for jdbcTemplate */
	public OperatorDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	/* List with information with data from database */
	public List<Operator> list(){
		String sql = "SELECT * FROM OPERATORZY";
		
		List<Operator> listOperator = jdbcTemplate.query(sql, 
				BeanPropertyRowMapper.newInstance(Operator.class));
		return listOperator;
	}
	
	/* Insert */
	public void save(Operator operator) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("operatorzy").usingColumns("nr_operatora","nazwa","data_zalozenia","kraj_centrali","NIP");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(operator);
		insertActor.execute(param);
	}
	
	/* Read */
	public Operator get(int id) {
		return null;
	}
	
	/* Update */
	public void update(Operator operator) {
		
	}
	
	/* Delete */
	public void delete(int id) {
		
	}
}
