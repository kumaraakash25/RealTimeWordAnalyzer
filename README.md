# RealTimeWordAnalyzer
Inital commit

The given is a SpringBoot application. On running the application, the controller end points are available to be accessed
using a POST request, with the URL http://localhost:8080/getCharacterAnalysis
and input parameters as 
inputURL: http://www.google.com
searchCharacter: c
The code runs as, it first validates the input url and the character. If they are ok then the result of the
input webpage is tokenized and is made available for procesing. The service does a simple calculation to generate
the percentage and returns as response.
