package com.coreywoodfield.pokedex.model;

import com.google.gson.annotations.Expose;

/**
 * Created by cwoodfie on 3/24/16.
 */
public class Species {
	@Expose private String name;
	private String url;
	@Expose private SpeciesInfo speciesInfo;

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public SpeciesInfo getSpeciesInfo() {
		return speciesInfo;
	}

	public void setSpeciesInfo(SpeciesInfo speciesInfo) {
		this.speciesInfo = speciesInfo;
	}

	public static class SpeciesInfo {
		@Expose private FlavorTextEntries[] flavorTextEntries;
		@Expose private Variety[] varieties;

		public String getFlavorText() {
			for (FlavorTextEntries entry : flavorTextEntries) {
				if (entry.isEnglish()) {
					return entry.getFlavorText();
				}
			}
			return "Error";
		}

		public Variety[] getVarieties() {
			return varieties;
		}

		public static class FlavorTextEntries {
			private String flavorText;
			private Language language;

			public String getFlavorText() {
				return flavorText;
			}

			public boolean isEnglish() {
				return language.isEnglish();
			}

			private static class Language {
				private String name;

				private boolean isEnglish() {
					return name.equals("en");
				}
			}
		}

		public static class Variety {
			private Pokemons.PokemonRef pokemon;

			public Pokemons.PokemonRef getPokemon() {
				return pokemon;
			}

			public String getName() {
				return pokemon.getName();
			}

			public String getId() {
				return pokemon.getId();
			}
		}
	}
}
