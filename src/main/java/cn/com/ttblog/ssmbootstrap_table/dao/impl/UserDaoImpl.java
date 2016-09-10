package cn.com.ttblog.ssmbootstrap_table.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import cn.com.ttblog.ssmbootstrap_table.dao.IUserDao;
import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.util.BeanMapUtil;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private JdbcTemplate jdbcTemple;

	@Override
	public int insert(User record) {
		return jdbcTemple
				.update("insert into user(name,sex,age,phone,deliveryaddress,adddate) values(?,?,?,?,?,?)",
						new Object[] { record.getName(), record.getSex(),
								record.getAge(), record.getPhone(),
								record.getDeliveryaddress(),
								record.getAdddate() });
	}

	@Override
	public long getUserListCount() {
		return jdbcTemple.queryForObject("select count(*) from user",
				Long.class);
	}

	@Override
	public int getNewData() {
		return jdbcTemple
				.queryForObject(
						"select count(id) from user where DATE_FORMAT(NOW(),'%Y-%m-%d')=FROM_UNIXTIME(adddate,'%Y-%m-%d')",
						Integer.class);
	}

	@Override
	public List<Map<String, Object>> getDataSum() {
		return jdbcTemple
				.queryForList("select count(id) num,FROM_UNIXTIME(adddate,'%Y-%m-%d') adddate from user group by FROM_UNIXTIME(adddate,'%Y-%m-%d')");
	}

	@Override
	public List<User> getUsersByIds(List<Long> longids) {
		StringBuilder ids = new StringBuilder();
		for (Long id : longids) {
			ids.append("?").append(",");
		}
		int size = longids.size();
		Object[] obj = new Object[size];
		for (int i = 0; i < size; i++) {
			obj[i] = longids.get(i);
		}
		return jdbcTemple.query(
				"select * from user where id in("
						+ ids.substring(0, ids.length() - 1) + ")", obj,
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User u = new User();
						u.setId(rs.getLong("id"));
						u.setAdddate(rs.getInt("adddate"));
						u.setName(rs.getString("name"));
						u.setSex(rs.getString("sex"));
						u.setAge(rs.getInt("age"));
						u.setDeliveryaddress(rs.getString("deliveryaddress"));
						u.setPhone(rs.getString("phone"));
						return u;
					}
				});
	}

	@Override
	public List<User> getUserList(Map<String, Object> params) {
		String sql = null;
		Object[] param = null;
		if (params.get("search") == null
				|| params.get("search").toString().equals("")) {
			sql = "select * from user order by adddate "
					+ params.get("order").toString() + " limit ?,?";
			param = new Object[] { params.get("offset"), params.get("limit") };
		} else {
			sql = "select * from user where name like ? or fristPinyin(name)=?  order by adddate "
					+ params.get("order").toString() + " limit ?,?";
			;
			param = new Object[] { "%" + params.get("search").toString() + "%",
					params.get("search").toString(), params.get("offset"),
					params.get("limit") };
		}
		return jdbcTemple.query(sql, param, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u = new User();
				u.setId(rs.getLong("id"));
				u.setAdddate(rs.getInt("adddate"));
				u.setName(rs.getString("name"));
				u.setSex(rs.getString("sex"));
				u.setAge(rs.getInt("age"));
				u.setDeliveryaddress(rs.getString("deliveryaddress"));
				u.setPhone(rs.getString("phone"));
				return u;
			}
		});
	}

	@Override
	public User getUserById(long userId) {
		final User u = new User();
		// 1.使用map转bean
		// BeanMapUtil
		// .transMap2Bean(jdbcTemple.queryForMap(
		// "select * from user where id=?", userId),u);

		// 2.使用rowcallback自己组装bean
		// jdbcTemple.query("select * from user where id=?", new
		// Object[]{userId},new RowCallbackHandler() {
		// @Override
		// public void processRow(ResultSet rs) throws SQLException {
		// u.setId(rs.getLong("id"));
		// u.setAdddate(rs.getInt("adddate"));
		// u.setName(rs.getString("name"));
		// u.setSex(rs.getString("sex"));
		// u.setAge(rs.getInt("age"));
		// u.setDeliveryaddress(rs.getString("deliveryaddress"));
		// u.setPhone(rs.getString("phone"));
		// }
		// });

		// 3.使用RowMapper自己组装bean
		return jdbcTemple.query("select * from user where id=?",
				new Object[] { userId }, new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User u = new User();
						u.setId(rs.getLong("id"));
						u.setAdddate(rs.getInt("adddate"));
						u.setName(rs.getString("name"));
						u.setSex(rs.getString("sex"));
						u.setAge(rs.getInt("age"));
						u.setDeliveryaddress(rs.getString("deliveryaddress"));
						u.setPhone(rs.getString("phone"));
						return u;
					}
				}).get(0);
		// return u;
	}

	@Override
	public User findByUserName(String username) {
		return jdbcTemple.query("select * from user where name=?",
				new Object[] { username }, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				User u = new User();
				u.setId(rs.getLong("id"));
				u.setAdddate(rs.getInt("adddate"));
				u.setName(rs.getString("name"));
				u.setSex(rs.getString("sex"));
				u.setAge(rs.getInt("age"));
				u.setDeliveryaddress(rs.getString("deliveryaddress"));
				u.setPhone(rs.getString("phone"));
				u.setPassword(rs.getString("password"));
				u.setIsLock(rs.getShort("islock"));
				return u;
			}
		}).get(0);
	}

}