package hello;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cwoodfie on 3/22/16.
 */
public class PokemonRef {

	private String name;
	private String url;

	public PokemonRef(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}

	public String getUrl() {
		return url;
	}

//	public String computeIdFromUrlV2() {
//		String result = "";
//		for (int i = url.length() - 1; i > 0; --i) {
//			char ch = url.charAt(i);
//			if (Character.isDigit(ch)) {
//				result = ch + result;
//			} else if (!result.equals("")) {
//				break;
//			}
//		}
//		return result;
//	}

	public String computeIdFromUrl() {
		String[] split = url.split("/");
		return split[split.length-1];
	}

//	public String computeIdFromUrlV4() {
//		String pattern = ".*/(\\d+)/?\\z";
//		Pattern p = Pattern.compile(pattern);
//		Matcher matcher = p.matcher(url);
//		if (matcher.find()) {
//			return matcher.group(1);
//		} else {
//			return null;
//		}
//	}
}
