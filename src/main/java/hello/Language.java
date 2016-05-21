package hello;

/**
 * Created by cwoodfie on 3/24/16.
 */
public class Language {

	private String name;

	public boolean isEnglish() {
		return name.equals("en");
	}

	@Override
	public String toString() {
		return "Language{" +
				"name='" + name + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}

	public Language(String name) {

		this.name = name;
	}
}
