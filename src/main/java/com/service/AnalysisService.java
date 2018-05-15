package com.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AnalysisService {

    @Autowired
    private InputValidationService inputValidationService;

    @Autowired
    private WordProcessingService wordProcessingService;

    @Autowired
    private URLDataFetcherService urlDataFetcherService;

    private static final Log LOG = LogFactory.getLog(AnalysisService.class);

    // TODO: Add a cachable key for improved performance
    public Object process(final String url, final char character) {
        boolean isInputValid = validateInputs(url, character);
        if (isInputValid) {
            List<String> list = urlDataFetcherService.getTokensFromURL(url);
            if (CollectionUtils.isEmpty(list)) {
                return "Error occurred while tokenizing the URL response";
            }
            return "The percentage of character in url is " + wordProcessingService.generateSummary(character, list);
        } else {
            LOG.error("Input is not valid");
            return "Input is invalid";
        }
    }

    /**
     * Checks if the input is valid
     *
     * @param url
     * @param character
     * @return
     */
    private boolean validateInputs(final String url, final char character) {
        boolean isValidURL = inputValidationService.isUrlValid(url);
        boolean isValidCharacter = inputValidationService.isCharacterValid(character);
        return isValidURL && isValidCharacter;
    }
}
