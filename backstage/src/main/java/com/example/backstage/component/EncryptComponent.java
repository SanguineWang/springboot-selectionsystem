package com.example.backstage.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component

public class EncryptComponent {

    @Value("${my.secretkey}")
    private String secretkey;
    @Value("${my.salt}")
    private String salt;
    @Autowired
    private TextEncryptor encryptor;
    @Autowired
    private ObjectMapper objectMapper;


    /**
     * @return single TextEncryptor
     */
    @Bean
    public TextEncryptor getEncryptor() {
        return Encryptors.text(secretkey, salt);
    }

    /**
     * @param mytoken token
     * @return identity
     */
    public String encryptToken(Mytoken mytoken) {

        try {
            String json = objectMapper.writeValueAsString(mytoken);
            return encryptor.encrypt(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "服务器端发生不可预知错误");
        }

    }

    /**
     * @param identity 身份标识
     * @return token
     */
    public Mytoken decryptToken(String identity) {

        try {
            String json = encryptor.decrypt(identity);
            return objectMapper.readValue(json, Mytoken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"鉴权失败");
        }

    }

}
