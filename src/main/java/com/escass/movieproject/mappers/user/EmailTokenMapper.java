package com.escass.movieproject.mappers.user;

import com.escass.movieproject.entities.user.EmailTokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmailTokenMapper {
    int insertEmailToken(EmailTokenEntity emailToken);

    EmailTokenEntity selectEmailTokenByUserEmailAndKey(
            @Param("emEmail") String userEmail,
            @Param("emKey") String key
    );

    int updateEmailToken(EmailTokenEntity emailToken);

}
