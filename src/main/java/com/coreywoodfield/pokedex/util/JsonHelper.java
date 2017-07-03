package com.coreywoodfield.pokedex.util;

import com.coreywoodfield.pokedex.model.Pokemon;
import com.coreywoodfield.pokedex.model.Pokemons;
import com.coreywoodfield.pokedex.model.Species;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * Created by coreywoodfield on 7/3/17.
 */
public class JsonHelper {
	private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
													  .registerTypeAdapter(Pokemons.PokemonRef.class, new PokemonRefSerializer())
													  .registerTypeAdapter(Species.SpeciesInfo.FlavorTextEntries[].class, new FlavorTextSerializer())
													  .registerTypeAdapter(Species.SpeciesInfo.Variety[].class, new VarietyArraySerializer())
													  .registerTypeAdapter(Species.SpeciesInfo.Variety.class, new VarietySerializer())
													  .registerTypeAdapter(Pokemon.Type[].class, new TypeSerializer())
													  .create();

	public static String toJson(List<Pokemons.PokemonRef> pokemonRefs) {
		return GSON.toJson(pokemonRefs);
	}

	public static String toJson(Pokemon pokemon) {
		return GSON.toJson(pokemon);
	}

	public static String base(Object o) {
		return "{\"version\":\"2.0\",\"links\":[{\"href\":\"/pokemon\",\"rel\":\"list\",\"method\":\"GET\"}]}";
	}

	private static class PokemonRefSerializer extends TypeAdapter<Pokemons.PokemonRef> {
		@Override
		public void write(JsonWriter jsonWriter, Pokemons.PokemonRef pokemonRef) throws IOException {
			jsonWriter.beginObject()
					  .name("id").value(pokemonRef.getId())
					  .name("name").value(pokemonRef.getName())
					  .name("links")
					  .beginArray().beginObject()
					  .name("href").value("/pokemon/" + pokemonRef.getId())
					  .name("rel").value("self")
					  .name("method").value("GET")
					  .endObject().endArray()
					  .endObject();
		}

		@Override
		public Pokemons.PokemonRef read(JsonReader jsonReader) throws IOException {
			throw new UnsupportedOperationException();
		}
	}

	private static class FlavorTextSerializer extends TypeAdapter<Species.SpeciesInfo.FlavorTextEntries[]> {
		@Override
		public void write(JsonWriter jsonWriter, Species.SpeciesInfo.FlavorTextEntries[] flavorTextEntries) throws IOException {
			for (Species.SpeciesInfo.FlavorTextEntries flavorTextEntry : flavorTextEntries) {
				if (flavorTextEntry.isEnglish()) {
					jsonWriter.value(flavorTextEntry.getFlavorText());
					return;
				}
			}
			jsonWriter.value("Unable to retrieve flavor text");
		}

		@Override
		public Species.SpeciesInfo.FlavorTextEntries[] read(JsonReader jsonReader) throws IOException {
			throw new UnsupportedOperationException();
		}
	}

	private static class VarietyArraySerializer implements JsonSerializer<Species.SpeciesInfo.Variety[]> {
		@Override
		public JsonElement serialize(Species.SpeciesInfo.Variety[] varieties, Type type, JsonSerializationContext jsonSerializationContext) {
			List<Species.SpeciesInfo.Variety> asList = Arrays.asList(varieties);
			return jsonSerializationContext.serialize(asList.subList(1, asList.size()));
		}
	}

	private static class VarietySerializer implements JsonSerializer<Species.SpeciesInfo.Variety> {
		@Override
		public JsonElement serialize(Species.SpeciesInfo.Variety variety, Type type, JsonSerializationContext jsonSerializationContext) {
			return jsonSerializationContext.serialize(variety.getPokemon());
		}
	}

	private static class TypeSerializer implements JsonSerializer<Pokemon.Type[]> {
		@Override
		public JsonElement serialize(Pokemon.Type[] types, Type type, JsonSerializationContext jsonSerializationContext) {
			JsonArray array = new JsonArray();
			for (Pokemon.Type pokeType : types) {
				array.add(pokeType.getTypeStr());
			}
			return array;
		}
	}
}
