package com.iTMS.iTMS.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TstService {
    @Value("$MY_DEVELOPER_TOKEN")
    private String token;

}
