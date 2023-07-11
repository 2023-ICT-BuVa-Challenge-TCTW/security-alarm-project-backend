package com.example.buva.text.service;


import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.buva.text.dto.MessageDto;
import com.example.buva.text.dto.SMSRequest;
import com.example.buva.text.dto.SMSResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class SmService {

	@Value("${sms.serviceId}")
	private String serviceId;
	@Value("${sms.accessKey}")
	private String accessKey;
	@Value("${sms.secretKey}")
	private String secretKey;

	 public SMSResponse sendSMS(String recipientPhoneNumber, String content) throws Exception{
		String timestamp = String.valueOf(System.currentTimeMillis());
		List<MessageDto> message = new ArrayList<>();
		message.add(new MessageDto(recipientPhoneNumber, content));
        String signature = makeSignature();
        System.out.println("Signature: " + signature);

        SMSRequest smsRequest = new SMSRequest("SMS", "COMM","82", "01073656042", "여기는 내용", message);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonBody = objectMapper.writeValueAsString(smsRequest);
		
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp",timestamp);
        headers.set("x-ncp-iam-access-key", this.accessKey);
        headers.set("x-ncp-apigw-signature-v2", signature);
        
		HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
        // System.out.println(jsonBody);
        // System.out.println(this.serviceId);
		// System.out.println(this.secretKey);
		// System.out.println(this.accessKey);
		
		String apiUrl = "https://sens.apigw.ntruss.com/sms/v2/services/"+this.serviceId+"/messages";
        
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        SMSResponse smsResponse = restTemplate.postForObject(apiUrl, requestEntity, SMSResponse.class);
        return smsResponse;
    }

    public String makeSignature() throws Exception {
	String space = " ";					// one space
	String newLine = "\n";					// new line
	String method = "GET";					// method
	String url = "/photos/puppy.jpg?query1=&query2";	// url (include query string)
	String timestamp = String.valueOf(System.currentTimeMillis());			// current timestamp (epoch)
	String accessKey = this.accessKey;			// access key id (from portal or Sub Account)
	String secretKey = this.secretKey;

	String message = new StringBuilder()
		.append(method)
		.append(space)
		.append(url)
		.append(newLine)
		.append(timestamp)
		.append(newLine)
		.append(accessKey)
		.toString();

	SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
	Mac mac = Mac.getInstance("HmacSHA256");
	mac.init(signingKey);

	byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
	String encodeBase64String = Base64.encodeBase64String(rawHmac);

  return encodeBase64String;
}



}
