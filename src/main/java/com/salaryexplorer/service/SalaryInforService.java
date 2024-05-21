package com.salaryexplorer.service;

import com.salaryexplorer.api.request.SalaryRequest;
import com.salaryexplorer.api.response.NodePupeteerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SalaryInforService {
    @Value("${node-pupeteer.url}")
    public String nodePupeteerServer;
    public String getSalaryInfo(SalaryRequest request){
        WebClient client = WebClient.create();

        return client.post()
                .uri(nodePupeteerServer)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .onStatus(HttpStatusCode::isError, ClientResponse::createException)
                .bodyToMono(String.class)
                .block();
    }
}
