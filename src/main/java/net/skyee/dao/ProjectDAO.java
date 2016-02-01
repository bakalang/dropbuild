package net.skyee.dao;

import net.skyee.bean.Project;
import net.skyee.dao.mapper.ProjectMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Define;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import java.math.BigDecimal;
import java.util.List;

@UseStringTemplate3StatementLocator
public interface ProjectDAO {

    @SqlUpdate("insert into st values (:sno, :datetime, :close_price, :increase, :increase_percentage, :in_count, :out_count, :total_count)")
    void insert(@Bind("sno") String sno,
                @Bind("datetime") String datetime,
                @Bind("close_price") BigDecimal close_price,
                @Bind("increase") BigDecimal increase,
                @Bind("increase_percentage") BigDecimal increase_percentage,
                @Bind("in_count") BigDecimal in_count,
                @Bind("out_count") BigDecimal out_count,
                @Bind("total_count") BigDecimal total_count);

    @SqlUpdate("CREATE TABLE IF NOT EXISTS <new_table> LIKE template_table")
    int findTableExist(@Define("new_table") String new_table);

    @SqlUpdate("UPDATE project SET last_version=:last_version WHERE project_no=:project_no")
    int updateProjectVersion(@Bind("last_version") long lastVersion,
                             @Bind("project_no") int projectNo);

    @SqlQuery(" SELECT project_no, module, repository, last_version FROM project WHERE module = :module ")
    @Mapper(ProjectMapper.class)
    Project getProjectByModule(@Bind("module") String module);

    @SqlQuery(" SELECT project_no, module, repository, last_version FROM project WHERE project_no = :project_no ")
    @Mapper(ProjectMapper.class)
    Project getProjectByNo(@Bind("project_no") int projectNo);

}
