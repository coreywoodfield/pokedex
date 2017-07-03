package com.coreywoodfield.pokedex.rest;

import com.coreywoodfield.pokedex.model.Pokemon;
import com.coreywoodfield.pokedex.model.Pokemons;
import com.coreywoodfield.pokedex.model.Species;
import com.coreywoodfield.pokedex.util.HtmlHelper;
import com.coreywoodfield.pokedex.util.JsonHelper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by cwoodfie on 3/18/16.
 */

@RestController
public class PokemonController {
	private static final String BASE_URL = "http://pokeapi.co/api/v2/pokemon/";
	private final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

	@RequestMapping(method = RequestMethod.GET)
	public String getEndpoints(@RequestHeader("Accept") String accept) {
		return getResponse(accept, null, JsonHelper::base, HtmlHelper::base);
	}

	@RequestMapping(value = "/pokemon", method = RequestMethod.GET)
	public String getPokemons(@RequestHeader("Accept") String accept) throws UnirestException {
		List<Pokemons.PokemonRef> pokemonRefs = gson.fromJson(Unirest.get(BASE_URL).queryString("limit", 721).asString().getBody(), Pokemons.class).getResults();
		return getResponse(accept, pokemonRefs, JsonHelper::toJson, HtmlHelper::toHtml);
	}

	@RequestMapping(value = "/pokemon/{id}", method = RequestMethod.GET)
	public String getPokemon(@RequestHeader("Accept") String accept, @PathVariable("id") Integer id) throws UnirestException {
		Pokemon pokemon = gson.fromJson(Unirest.get(BASE_URL + id + "/").asString().getBody(), Pokemon.class);
		Species species = pokemon.getSpecies();
		Species.SpeciesInfo speciesInfo = gson.fromJson(Unirest.get(species.getUrl()).asString().getBody(), Species.SpeciesInfo.class);
		species.setSpeciesInfo(speciesInfo);
		pokemon.setAltForms(getAltForms(speciesInfo));
		return getResponse(accept, pokemon, JsonHelper::toJson, HtmlHelper::toHtml);
	}

	private List<Pokemon> getAltForms(Species.SpeciesInfo speciesInfo) throws UnirestException {
		List<Species.SpeciesInfo.Variety> varieties = Arrays.asList(speciesInfo.getVarieties());
		if (varieties.size() > 1) {
			List<Pokemon> altForms = new ArrayList<>(varieties.size() - 1);
			for (Species.SpeciesInfo.Variety variety : varieties.subList(1, varieties.size())) {
				altForms.add(gson.fromJson(Unirest.get(BASE_URL + variety.getId() + "/").asString().getBody(), Pokemon.class));
			}
			return altForms;
		}
		return Collections.emptyList();
	}

	private <T> String getResponse(String accept, T object, ResponseConverter<T> json, ResponseConverter<T> html) {
		if (accept.equals("*/*") || accept.contains("json")) {
			return json.convert(object);
		} else if (accept.contains("html")) {
			return html.convert(object);
		} else {
			return "Unsupported media type: " + accept;
		}
	}

	private interface ResponseConverter<T> {
		String convert(T object);
	}
}
