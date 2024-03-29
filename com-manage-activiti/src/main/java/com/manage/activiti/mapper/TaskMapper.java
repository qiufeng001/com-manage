package com.manage.activiti.mapper;

import com.manage.activiti.domain.InstanceBusiness;
import com.manage.activiti.domain.TaskVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 一只闲鹿
 */
@Repository
public interface TaskMapper {

    @Select(" <script>SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tA.*\n" +
            "\t\tFROM\n" +
            "\t\t\t(\n" +
            "\t\t\t\tSELECT DISTINCT\n" +
            "\t\t\t\t\tRES.*\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tACT_RU_TASK RES\n" +
            "\t\t\t\tLEFT JOIN act_ru_identitylink I ON I.TASK_ID_ = RES.ID_\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tRES.SUSPENSION_STATE_ = 1\n" +
            "\t\t\t\tAND (\n" +
            "\t\t\t\t\tRES.ASSIGNEE_ = #{userId}\n" +
            "\t\t\t\t\tOR (\n" +
            "\t\t\t\t\t\tRES.ASSIGNEE_ IS NULL\n" +
            "\t\t\t\t\t\tAND (\n" +
            "\t\t\t\t\t\t\tI.USER_ID_ = #{userId}\n" +
            "\t\t\t\t\t\t\tOR I.GROUP_ID_ IN (\n" +
            "\t\t\t\t\t\t\t\tSELECT\n" +
            "\t\t\t\t\t\t\t\t\tG.GROUP_ID_\n" +
            "\t\t\t\t\t\t\t\tFROM\n" +
            "\t\t\t\t\t\t\t\t\tact_id_membership G\n" +
            "\t\t\t\t\t\t\t\tWHERE\n" +
            "\t\t\t\t\t\t\t\t\tG.USER_ID_ = #{userId}\n" +
            "\t\t\t\t\t\t\t)\n" +
            "\t\t\t\t\t\t)\n" +
            "\t\t\t\t\t)\n" +
            "\t\t\t\t)\n" +
            "\t\t\t\tORDER BY\n" +
            "\t\t\t\t\tRES.CREATE_TIME_ DESC\n" +
            "\t\t\t) A\n" +
            "\t) A\n" +
            "WHERE\n" +
            "\t1 = 1 <if test='taskId != null'> AND ID_ = #{taskId} </if><if test='taskName != null'> AND NAME_ = #{taskName} </if><if test='instanceId != null'> AND PROC_INST_ID_ = #{instanceId} </if> \n" +
            "LIMIT #{offset},\n" +
            " #{pageSize}</script> ")
    List<Map> findTodoList(TaskVo taskVo);

    @Select(" <script>SELECT\n" +
            "\tCOUNT(*) AS COUNT_\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tA.*\n" +
            "\t\tFROM\n" +
            "\t\t\t(\n" +
            "\t\t\t\tSELECT DISTINCT\n" +
            "\t\t\t\t\tRES.*\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tACT_RU_TASK RES\n" +
            "\t\t\t\tLEFT JOIN act_ru_identitylink I ON I.TASK_ID_ = RES.ID_\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tRES.SUSPENSION_STATE_ = 1\n" +
            "\t\t\t\tAND (\n" +
            "\t\t\t\t\tRES.ASSIGNEE_ = #{userId}\n" +
            "\t\t\t\t\tOR (\n" +
            "\t\t\t\t\t\tRES.ASSIGNEE_ IS NULL\n" +
            "\t\t\t\t\t\tAND (\n" +
            "\t\t\t\t\t\t\tI.USER_ID_ = #{userId}\n" +
            "\t\t\t\t\t\t\tOR I.GROUP_ID_ IN (\n" +
            "\t\t\t\t\t\t\t\tSELECT\n" +
            "\t\t\t\t\t\t\t\t\tG.GROUP_ID_\n" +
            "\t\t\t\t\t\t\t\tFROM\n" +
            "\t\t\t\t\t\t\t\t\tact_id_membership G\n" +
            "\t\t\t\t\t\t\t\tWHERE\n" +
            "\t\t\t\t\t\t\t\t\tG.USER_ID_ = #{userId}\n" +
            "\t\t\t\t\t\t\t)\n" +
            "\t\t\t\t\t\t)\n" +
            "\t\t\t\t\t)\n" +
            "\t\t\t\t)\n" +
            "\t\t\t\tORDER BY\n" +
            "\t\t\t\t\tRES.CREATE_TIME_ DESC\n" +
            "\t\t\t) A\n" +
            "\t) A\n" +
            "WHERE\n" +
            "\t1 = 1 <if test='taskId != null'> AND ID_ = #{taskId} </if><if test='taskName != null'> AND NAME_ = #{taskName} </if><if test='instanceId != null'> AND PROC_INST_ID_ = #{instanceId} </if> \n" +
            " </script> ")
    Integer findTodoCount(TaskVo taskVo);

    @Select(" <script>SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tA.*\n" +
            "\t\tFROM\n" +
            "\t\t\t(\n" +
            "\t\t\t\tSELECT DISTINCT\n" +
            "\t\t\t\t\tRES.*\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tACT_HI_TASKINST RES\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tRES.ASSIGNEE_ = #{userId}\n" +
            "\t\t\t\tAND RES.END_TIME_ IS NOT NULL\n" +
            "\t\t\t\tORDER BY\n" +
            "\t\t\t\t\tRES.END_TIME_ DESC\n" +
            "\t\t\t) A\n" +
            "\t\tWHERE\n" +
            "\t\t\t1 = 1\n" +
            "\t) A\n" +
            "WHERE\n" +
            "\t1 = 1 <if test='taskId != null'> AND ID_ = #{taskId} </if><if test='taskName != null'> AND NAME_ = #{taskName} </if><if test='instanceId != null'> AND PROC_INST_ID_ = #{instanceId} </if> \n" +
            "LIMIT #{offset},\n" +
            " #{pageSize}</script> ")
    List<Map> findDoneList(TaskVo taskVo);

    @Select(" <script>SELECT\n" +
            "\tCOUNT(*) AS COUNT_\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tA.*\n" +
            "\t\tFROM\n" +
            "\t\t\t(\n" +
            "\t\t\t\tSELECT DISTINCT\n" +
            "\t\t\t\t\tRES.*\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tACT_HI_TASKINST RES\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tRES.ASSIGNEE_ = 'ry'\n" +
            "\t\t\t\tAND RES.END_TIME_ IS NOT NULL\n" +
            "\t\t\t\tORDER BY\n" +
            "\t\t\t\t\tRES.END_TIME_ DESC\n" +
            "\t\t\t) A\n" +
            "\t\tWHERE\n" +
            "\t\t\t1 = 1\n" +
            "\t) A\n" +
            "WHERE\n" +
            "\t1 = 1 <if test='taskId != null'> AND ID_ = #{taskId} </if><if test='taskName != null'> AND NAME_ = #{taskName} </if><if test='instanceId != null'> AND PROC_INST_ID_ = #{instanceId} </if> \n" +
            " </script> ")
    Integer findDoneCount(TaskVo taskVo);

    @Insert(" INSERT INTO instance_business (instance_id, business_key, module) VALUES (#{instanceId}, #{businessKey}, #{module}) ")
    int insertInstanceBusiness(InstanceBusiness instanceBusiness);

    @Select(" SELECT * FROM instance_business WHERE 1=1 AND INSTANCE_ID = #{instanceId} LIMIT 1 ")
    Map<String, Object> selectInstanceBusinessByInstanceId(String instanceId);

    @Select(" SELECT * FROM ${module} WHERE ID = #{id} ")
    Map<String, Object> selectBusinessByBusinessKeyAndModule(@Param("id") String id, @Param("module") String module);

}
