package bada_gra_proj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
		
		List<Operator> listSale = jdbcTemplate.query(sql, 
				BeanPropertyRowMapper.newInstance(Operator.class));
		return listSale;
	}
	
	/* Insert */
	public void save(Operator sale) {
		
	}
	
	/* Read */
	public Operator get(int id) {
		return null;
	}
	
	/* Update */
	public void update(Operator sale) {
		
	}
	
	/* Delete */
	public void delete(int id) {
		
	}
}
