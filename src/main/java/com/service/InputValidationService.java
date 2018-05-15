package com.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Service
public class InputValidationService {

    private static final Log LOG = LogFactory.getLog(InputValidationService.class);

    /**
     * Check if the input URL is valid.
     *
     * @param url
     * @return
     */
    public boolean isUrlValid(final String url) {
        boolean isUrlValid = true;
        if (StringUtils.isEmpty(url)) {
            isUrlValid = false;
        } else {
            isUrlValid = testUrlReachability(url);
        }
        return isUrlValid;
    }

    /**
     * Check if the input character is valid.
     *
     * @param character
     * @return
     */
    public boolean isCharacterValid(final char character) {
        boolean isCharacterValid = true;
        if (character == ' ') {
            isCharacterValid = false;
        } else {
            if (!Character.isLetter(character)) {
                isCharacterValid = false;
            }
        }
        return isCharacterValid;
    }

    /**
     * Check if the url is reachable.
     *
     * @param url
     * @return
     */
    private boolean testUrlReachability(final String url) {
        boolean isUrlValid = true;
        try {
            Document doc = Jsoup.connect(url).get();
            if (doc == null) {
                LOG.error("URL did not return response" + url);
                isUrlValid = false;
            }
        } catch (IOException e) {
            LOG.error("URL is not reachable" + url);
            isUrlValid = false;
        }
        return isUrlValid;
    }
}
