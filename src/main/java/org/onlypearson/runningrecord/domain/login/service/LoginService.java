package org.onlypearson.runningrecord.domain.login.service;

import org.onlypearson.runningrecord.domain.member.Member;

public interface LoginService {
    Member login(String loginId, String password);
}
