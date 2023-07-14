package com.example.buva.text.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;

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

	public SMSResp sendSMS(String to, String content) throws JsonProcessingException, InvalidKeyException,
			IllegalStateException, UnsupportedEncodingException, NoSuchAlgorithmException {

		String timestamp = String.valueOf(System.currentTimeMillis());
		SignatureUtil signatureUtil = new SignatureUtil(timestamp, this.accessKey, this.secretKey);
		String apiUrl = "https://sens.apigw.ntruss.com/sms/v2/services/" + this.serviceId + "/messages";
		String signature = signatureUtil.makeSignature();

		SMSReq smsRequest = new SMSReq(
				"SMS",
				"COMM", 
				"82",
				"01073656042",
				"여기는 내용",
				new MessageDto(to, content)
				);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/json; charset=utf-8"));
		headers.set("x-ncp-apigw-timestamp", timestamp);
		headers.set("x-ncp-iam-access-key", this.accessKey);
		headers.set("x-ncp-apigw-signature-v2", signature);

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonBody = objectMapper.writeValueAsString(smsRequest);

		HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		SMSResp smsResponse = restTemplate.postForObject(apiUrl, requestEntity, SMSResp.class);
		return smsResponse;
	}

}
