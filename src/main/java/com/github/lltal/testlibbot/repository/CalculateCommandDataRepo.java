package com.github.lltal.testlibbot.repository;

import com.github.lltal.testlibbot.domain.CalculateCommandData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.wdeath.telegram.bot.starter.annotations.ParamName;

public interface CalculateCommandDataRepo extends JpaRepository<CalculateCommandData, Long> {

    @Query(value = "select * from calculation_data cd" +
            " join usr on cd.user_id = usr.id" +
            " where usr.id = :userId", nativeQuery = true)
    CalculateCommandData findDataByUserId(@Param("userId") Long userId);
}
