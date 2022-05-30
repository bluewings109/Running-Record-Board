package org.onlypearson.runningrecord.web.login.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginForm {
    public LoginForm() {
    }

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

}
