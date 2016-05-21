package hello;

/**
 * Created by cwoodfie on 3/24/16.
 */
public class Variety {

	private class pokemon {
		private String name;
		private String url;

		public String getUrl() {
			return url;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "pokemon{" +
					"name='" + name + '\'' +
					", url='" + url + '\'' +
					'}';
		}

		public pokemon(String name, String url) {
			this.name = name;
			this.url = url;
		}

		public String computeIdFromUrl() {
			String[] split = url.split("/");
			return split[split.length-1];
		}
	};
	private pokemon pokemon;

	public Variety(Variety.pokemon pokemon) {
		this.pokemon = pokemon;
	}

	@Override
	public String toString() {
		return "Variety{" +
				"pokemon=" + pokemon +
				'}';
	}

	public Variety.pokemon getPokemon() {
		return pokemon;
	}

	public String getName() {
		return pokemon.getName();
	}

	public String getId() {
		return pokemon.computeIdFromUrl();
	}

}
