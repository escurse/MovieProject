package com.escass.movieproject.mappers.ticket;

import com.escass.movieproject.entities.ticket.MethodEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MethodMapper {

    MethodEntity selectPaymentMeNum(@Param("meName") String meName);
}
