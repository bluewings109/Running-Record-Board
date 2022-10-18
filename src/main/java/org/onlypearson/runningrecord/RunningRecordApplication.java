package org.onlypearson.runningrecord;

import org.onlypearson.runningrecord.domain.member.repository.MemberRepository;
import org.onlypearson.runningrecord.domain.record.repository.RecordRepository;
import org.onlypearson.runningrecord.web.TestDataInit.TestMemberDataInit;
import org.onlypearson.runningrecord.web.TestDataInit.TestRecordDataInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class RunningRecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunningRecordApplication.class, args);
	}

	@Bean
	@Profile("local")
	public TestMemberDataInit testMemberDataInit(MemberRepository memberRepository) {
		return new TestMemberDataInit(memberRepository);
	}

	@Bean
	@Profile("local")
	public TestRecordDataInit testRecordDataInit(RecordRepository recordRepository) {
		return new TestRecordDataInit(recordRepository);
	}


}
