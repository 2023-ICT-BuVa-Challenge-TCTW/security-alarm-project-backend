package com.example.buva.sms.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.example.buva.sms.dto.SmsInsertReq;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.buva.sms.dto.SmsApiDto;
import com.example.buva.sms.dto.SmsInsertResp;
import com.example.buva.sms.util.SignatureUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SmsService {

	private static final String serviceId = System.getenv("NCLOUD_SERVICE_ID");
	private static final String accessKey = System.getenv("NCLOUD_ACCESS_KEY");
	private static final String secretKey = System.getenv("NCLOUD_SECRET_KEY");
	private static final String phoneNumber = System.getenv("PHONE_NUMBER");

	@Transactional
	public SmsInsertResp sendSMS(String to, String content, String reserveTime) throws JsonProcessingException, InvalidKeyException,
			IllegalStateException, UnsupportedEncodingException, NoSuchAlgorithmException {

		long elapsedTimeMillis = Instant.now().toEpochMilli();
		String timestamp = String.valueOf(elapsedTimeMillis);
		String apiUrl = "https://sens.apigw.ntruss.com/sms/v2/services/" + serviceId + "/messages";
		String signature = SignatureUtil.makeSignature(timestamp, serviceId, accessKey, secretKey);

		List<SmsInsertReq.MessageDto> messages = new ArrayList<>();
		messages.add(new SmsInsertReq.MessageDto(to, content));

		SmsApiDto smsReq = SmsApiDto.builder()
				.type("SMS")
				.contentType("COMM")
				.countryCode("82")
				.from(phoneNumber)
				.content("캣챠 앱에서 보내는 긴급신고 문자입니다")
				.messages(messages)
				.reserveTime(reserveTime)
				.reserveTimeZone("Asia/Seoul")
				.build();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
		headers.set("x-ncp-apigw-timestamp", timestamp);
		headers.set("x-ncp-iam-access-key", accessKey);
		headers.set("x-ncp-apigw-signature-v2", signature);

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonBody = objectMapper.writeValueAsString(smsReq);
		HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		return restTemplate.postForObject(apiUrl, requestEntity, SmsInsertResp.class);
	}
}
