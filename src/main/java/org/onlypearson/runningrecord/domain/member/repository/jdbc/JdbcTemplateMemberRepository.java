package org.onlypearson.runningrecord.domain.member.repository.jdbc;

import org.onlypearson.runningrecord.domain.member.Member;
import org.onlypearson.runningrecord.domain.member.MemberDto;
import org.onlypearson.runningrecord.domain.member.repository.MemberRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateMemberRepository implements MemberRepository {

    private final NamedParameterJdbcTemplate template;

    private final SimpleJdbcInsert jdbcInsert;


    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<Member> rowMapper = ((rs, rowNum) -> {
        if(rowNum !=0){
            return null;
        }
        Member member = new Member();
        member.setId(rs.getLong("id"));
        member.setLoginId(rs.getString("login_id"));
        member.setPassword(rs.getString("password"));
        return member;
    });

    @Override
    public Member save(Member member) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(member);
        long key = jdbcInsert.executeAndReturnKey(param).longValue();
        member.setId(key);
        return member;
    }



    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = :id";

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        try {
            Member member = template.queryForObject(sql, param, rowMapper);
            return Optional.of(member);

        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member order by id asc";
        return template.query(sql, rowMapper);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        String sql = "select * from member where login_id = :loginId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("loginId", loginId);

        try {
            Member member = template.queryForObject(sql, param, rowMapper);
            return Optional.of(member);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void update(Long id, MemberDto memberDto) {
        String sql = "update member set login_id = :loginId, password = :password where id  = :id";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("loginId", memberDto.getLoginId())
                .addValue("password", memberDto.getPassword())
                .addValue("id", id);

        template.update(sql, param);
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from member where id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }

}
