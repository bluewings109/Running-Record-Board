package org.onlypearson.runningrecord.web.member.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberJoinForm {

    public MemberJoinForm() {
    }

    @NotEmpty
    @Length(min = 4, max = 15)
    private String loginId;

    @NotEmpty
    @Length(min = 6, max=20)
    private String password;
}
