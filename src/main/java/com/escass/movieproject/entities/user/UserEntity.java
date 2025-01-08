package com.escass.movieproject.entities.user;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "usNum")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    public static final String NAME_SINGULAR = "user";

    private int usNum;
    private String usId;
    private String usPw;
    private String usName;
    private String usNickName;
    private LocalDate usBirth;
    private String usGender;
    private String usEmail;
    private String usContact;
    private String usAddr;
    private LocalDateTime usCreatedAt;
    private LocalDateTime usUpdatedAt;
    private boolean usIsDeleted;
    private boolean usIsAdmin;
    private boolean usIsSuspended;
    private boolean usIsVerified;
    private String usSocialTypeCode;
    private String usSocialId;
}
