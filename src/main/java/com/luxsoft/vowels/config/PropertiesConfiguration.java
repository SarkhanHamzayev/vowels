package com.luxsoft.vowels.config;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@Builder
@ConfigurationProperties("property")
@AllArgsConstructor
@NoArgsConstructor
public class PropertiesConfiguration {

    private String inputFileName;
    private String outputFileName;
    private String punctuationCharacters;
    private String vowels;
    private String averagePrecision;
    private String averageRoundingMode;


}


