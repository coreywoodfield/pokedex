package com.coreywoodfield.pokedex.model;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by coreywoodfield on 7/3/17.
 */
public class Pokemons {
	@Expose	private List<PokemonRef> results;

	public List<PokemonRef> getResults() {
		return results;
	}

	public static class PokemonRef {
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

		public String getId() {
			String[] split = url.split("/");
			return split[split.length-1];
		}
	}
}
