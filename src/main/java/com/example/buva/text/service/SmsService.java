package com.example.buva.text.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.buva.text.dto.MessageDto;
import com.example.buva.text.dto.SMSReq;
import com.example.buva.text.dto.SMSResp;
import com.example.buva.text.util.SignatureUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SmsService {

	@Value("${sms.serviceId}")
	private String serviceId;
	@Value("${sms.accessKey}")
	private String accessKey;
	@Value("${sms.secretKey}")
	private String secretKey;

	@Transactional
	public SMSResp sendSMS(String to, String content) throws JsonProcessingException, InvalidKeyException,
			IllegalStateException, UnsupportedEncodingException, NoSuchAlgorithmException {

		long elapsedTimeMillis = Instant.now().toEpochMilli();
		String timestamp = String.valueOf(elapsedTimeMillis);
		String apiUrl = "https://sens.apigw.ntruss.com/sms/v2/services/" + this.serviceId + "/messages";
		String signature = SignatureUtil.makeSignature(timestamp, serviceId, accessKey, secretKey);

		List<MessageDto> messages = new ArrayList<>();
		messages.add(new MessageDto(to, content));

		SMSReq smsReq = SMSReq.builder()
				.type("SMS")
				.contentType("COMM")
				.countryCode("82")
				.from("01073656042")
				.content("00앱에서 보내는 메세지입니다")
				.messages(messages)
				.build();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
		headers.set("x-ncp-apigw-timestamp", timestamp);
		headers.set("x-ncp-iam-access-key", this.accessKey);
		headers.set("x-ncp-apigw-signature-v2", signature);

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonBody = objectMapper.writeValueAsString(smsReq);
		HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		return restTemplate.postForObject(apiUrl, requestEntity, SMSResp.class);
	}
}
