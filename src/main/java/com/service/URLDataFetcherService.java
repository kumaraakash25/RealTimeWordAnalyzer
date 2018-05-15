package com.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class URLDataFetcherService {

    private static final Log LOG = LogFactory.getLog(URLDataFetcherService.class);

    /**
     * Get content from the URL.
     *
     * @param url
     * @return
     */
    public List<String> getTokensFromURL(final String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            final String str = doc.getAllElements().toString();
            return getTokens(str);
        } catch (IOException e) {
            LOG.error("Error while reading the data from URL");
        }
        return null;

    }

    /**
     * Read the URL result and tokenize the words in page.
     *
     * @param string
     * @return
     */
    private List<String> getTokens(final String string) {
        List<String> tokens = new ArrayList<>();
        //TODO : Regex needs to be modified as it currently tries to pick up some JS also
        final String HTML_TAG_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";
        Pattern p1 = Pattern.compile(HTML_TAG_PATTERN);
        Matcher m1 = p1.matcher(string);
        //TODO: This whole operation can be done using parallel streams thereby improving performance
        while(m1.find()){
            final String str = m1.group(0);
            System.out.println(str);
            String[] strTokens = str.split("[\\s!>'\"<://;?=._-]");
            tokens.addAll(Arrays.asList(strTokens));
        }
        tokens.removeAll(Arrays.asList(null,""));
        return tokens;
    }
}
