package com.escass.movieproject.mappers.ticket;

import com.escass.movieproject.entities.theater.ScreenEntity;
import com.escass.movieproject.entities.ticket.PaymentEntity;
import com.escass.movieproject.entities.ticket.ReservationEntity;
import com.escass.movieproject.entities.ticket.SeatEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface ReservationMapper {

    ScreenEntity[] selectReservationByScNum(@Param("moTitle") String moTitle,
                                            @Param("ciName") String ciName,
                                            @Param("thName") String thName,
                                            @Param("scStartDate") LocalDateTime scStartDate);

    PaymentEntity[] selectPaymentByPaNum(
            @Param("meName") String meName,
            @Param("usNum") int usNum);

    SeatEntity[] selectSeatBySeNum(@Param("seName") String seName,
                                   @Param("ciName") String ciName,
                                   @Param("thName") String thName);

    int insertReservation(ReservationEntity reservation);

    Integer isSeatAlreadyReserved(@Param("seName") String seName,
                                  @Param("ciName") String ciName,
                                  @Param("thName") String thName,
                                  @Param("scStartDate") LocalDateTime scStartDate);
}
