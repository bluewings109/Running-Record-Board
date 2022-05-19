package org.onlypearson.runningrecord.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private Long id;
    private String loginId;
    private String password;
}
