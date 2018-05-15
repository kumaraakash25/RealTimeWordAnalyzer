package com.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WordProcessingService {

    /**
     * Generate summary for the word_occurances / total_words
     *
     * @param ch
     * @param wordList
     * @return
     */
    //TODO: It can be optimised by using Lucene, or Elastic search
    public BigDecimal generateSummary(char ch, List<String> wordList) {
        int totalWordCount = 0;
        int wordsWithSpecificCharacterCount = 0;
        for (String word : wordList) {
            int count = StringUtils.countOccurrencesOf(word.toLowerCase(), String.valueOf(ch));
            if (count > 0) {
                wordsWithSpecificCharacterCount = wordsWithSpecificCharacterCount + 1;
            }
            totalWordCount = totalWordCount + 1;
        }
        return returnPercentage(totalWordCount, wordsWithSpecificCharacterCount);
    }

    /**
     * Return the percentage of characters
     *
     * @param totalWordCount
     * @param wordsWithSpecificCharacterCount
     * @return
     */
    private BigDecimal returnPercentage(final int totalWordCount, final int wordsWithSpecificCharacterCount) {
        if (totalWordCount != 0 && wordsWithSpecificCharacterCount != 0) {
            BigDecimal result = new BigDecimal((float) wordsWithSpecificCharacterCount / totalWordCount).multiply(
                    new BigDecimal(100));
            return result.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        }
        return null;
    }


}
