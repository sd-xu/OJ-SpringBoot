package com.coding.oj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.oj.pojo.entity.Judge;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface JudgeMapper extends BaseMapper<Judge> {
    int deleteByPrimaryKey(@Param("submitId") Long submitId, @Param("pid") Long pid, @Param("uid") Integer uid);

    int insert(Judge record);

    Judge selectByPrimaryKey(@Param("submitId") Long submitId, @Param("pid") Long pid, @Param("uid") Integer uid);

    Judge selectBySubmitId(Long submitId);

    List<Judge> selectAll();

    int updateByPrimaryKey(Judge record);

    int updateBySubmitId(Long submitId);

    List<Judge> selectByStatus(int status);

    List<Judge> selectByUserIdAndStatus(@Param("userId")int userId,@Param("status")int status);

    List<Judge> selectByUserId(int userId);

    List<Judge> selectByUserIdAndLanguage(@Param("userId")int userId,@Param("lid")Long lid);

    List<Judge> selectByUserIdAndPid(@Param("userId")int userId,@Param("pid")Long pid);

    int getProblemNum(int userId);

    Date getLastSubmit(int userId);

    String getUsualLanguage(int userId);

    int getMaxSubmit(int userId);

    int getDifficulty(int userId);


    int lastDateCount(int userId);

    List<Judge> selectByPidAndStatus(@Param("userId")int userId, @Param("pid")Long pid, @Param("status")int status);

    List<Judge> selectByPidAndLanguage(@Param("userId")int userId, @Param("pid")Long pid, @Param("lid")Long lid);

    List<Judge> selectByAllParam(@Param("userId")int userId, @Param("pid")Long pid, @Param("status")int status, @Param("lid")Long lid, @Param("title")String title);

    @MapKey("uid")
    List<Map<?, ?>> getRank(@Param("rankSize")int rankSize);

    @MapKey("date")
    List<Map<?, ?>> selectSolvedProblemNumInDate(@Param("userId") int userId);

}