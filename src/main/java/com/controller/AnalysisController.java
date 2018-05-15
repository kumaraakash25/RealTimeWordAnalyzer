package com.controller;

import com.service.AnalysisService;
import com.service.WordProcessingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    private static final Log LOG = LogFactory.getLog(WordProcessingService.class);

    @RequestMapping(method = RequestMethod.POST, value = "/getCharacterAnalysis")
    public ResponseEntity getCharacterAnalysis(@RequestParam("inputURL") String inputURL, @RequestParam("searchCharacter") char inputCharacter) {
        LOG.info("Input parameters are URL " + inputURL + " character " + inputCharacter);
        Object result = analysisService.process(inputURL, inputCharacter);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
