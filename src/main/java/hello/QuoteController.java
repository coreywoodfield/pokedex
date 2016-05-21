package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by cwoodfie on 3/18/16.
 */

@RestController
public class QuoteController {

	@RequestMapping(value = "/spring-quote", method = RequestMethod.GET)
	public String quote() {
		RestTemplate restTemplate = new RestTemplate();
		Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		return
		"<!DOCTYPE html>" +
				"<html>" +
				"<head>" +
				"<title>Random Spring Quote</title>" +
//				"<meta http-equiv=\"refresh\" content=\"10\"" +
				"<style>p {background-color:e7edb4;font-size:2em;font-family:Impact,Charcoal,sans-serif;}</style>" +
				"</head>" +
				"<body>" +
				"<p>" + quote.getValue().getQuote() + "</p>" +
				"</body>" +
				"</html>";
	}
}
