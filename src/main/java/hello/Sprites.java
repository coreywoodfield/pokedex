package hello;

/**
 * Created by cwoodfie on 3/22/16.
 */
public class Sprites {

	private String front_shiny;
	private String front_default;

	public Sprites(String front_shiny, String front_default) {
		this.front_shiny = front_shiny;
		this.front_default = front_default;
	}

	public String getSprite() {
//		if (front_shiny != null) {
//			return front_shiny;
//		} else {
			return front_default;
//		}
	}

	public String getFront_shiny() {
		return front_shiny;
	}

	public String getFront_default() {
		return front_default;
	}

	@Override
	public String toString() {
		return "Sprites{" +
				"front_shiny='" + front_shiny + '\'' +
				", front_default='" + front_default + '\'' +
				'}';
	}
}
