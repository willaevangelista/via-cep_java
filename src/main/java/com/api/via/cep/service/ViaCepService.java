package com.api.via.cep.service;

import com.api.via.cep.model.Address;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;
import java.util.regex.Pattern;

@Slf4j
@Service
public class ViaCepService {

    private static final Pattern POSTAL_CODE_PATTERN = Pattern.compile("\\d{5}-?\\d{3}");

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Inform the Postal Code: ");
        var postCodeInput = scanner.nextLine();
        String postCodeValidate;

        try {
            postCodeValidate = validateAndNormalizeCep(postCodeInput);
            log.info("Validated Postal Code: {}", postCodeValidate);
        } catch (IllegalArgumentException e) {
            log.error("Invalid Postal Code: {}", e.getMessage());
            return;
        }

        String apiURL = "https://viacep.com.br/ws/" + postCodeInput + "/json/";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiURL))
                .timeout(Duration.ofSeconds(10))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int status = response.statusCode();
        if (status != 200) {
            log.error("Error while consulting Postal Code. Status code :{}", status);
            return;
        }

        String json = response.body();
        log.info("API JSON Response: \n{}\n", json);

        Gson gson = new Gson();

        com.google.gson.JsonObject obj = gson.fromJson(json, com.google.gson.JsonObject.class);
        if (obj.has("erro") && obj.get("erro").getAsBoolean()) {
            log.error("Postal Code Not Found.");
            return;
        }

        Address address = gson.fromJson(json, Address.class);
        log.info("Address Details:");
        log.info("Postal Code: {}", address.getPostalCode());
        log.info("Street: {}", address.getStreet());
        log.info("Neighborhood: {}", address.getNeighborhood());
        log.info("State: {}", address.getState());
        log.info("State Code: {}", address.getStateCode());

        scanner.close();

    }

    private static String validateAndNormalizeCep(String postalCodeInput) {
        if(postalCodeInput == null || postalCodeInput.isEmpty()) {
            throw new IllegalArgumentException("Postal Code cannot be null");
        }
        String trimmed = postalCodeInput.trim();
        if (!POSTAL_CODE_PATTERN.matcher(postalCodeInput).matches()) {
            throw new IllegalArgumentException("Postal Code must be in the format 12345-678 or 12345678");
        }
        return trimmed.replaceAll("\\D", "");
    }
}
