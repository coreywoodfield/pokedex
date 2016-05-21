package hello;

/**
 * Created by cwoodfie on 3/24/16.
 */
public class Flavor_Text_Entries {

	private String flavor_text;
	private Language language;

	@Override
	public String toString() {
		return "Flavor_Text_Entries{" +
				"flavor_text='" + flavor_text + '\'' +
				", language=" + language +
				'}';
	}

	public String getFlavor_text() {
		return flavor_text;
	}

	public Language getLanguage() {
		return language;
	}

	public Flavor_Text_Entries(String flavorText, Language language) {

		this.flavor_text = flavorText;
		this.language = language;
	}
}
