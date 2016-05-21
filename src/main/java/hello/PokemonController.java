package hello;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by cwoodfie on 3/18/16.
 */

@RestController
public class PokemonController {

	private static final Logger log = LoggerFactory.getLogger(PokemonController.class);
	private static final String BASE_URL = "http://pokeapi.co/api/v2/pokemon/";
	public static final String POKEMON_TABLE_FORMAT = "<!DOCTYPE html>" +
			"<html>\n" +
			"<head>\n" +
			"<title>Pokedex</title>\n" +
			"<style>\n" +
			"#header {background-color:#ef5350;width:100%%;z-index:1;position:fixed;height:3em;top:0;left:0;right:0;}\n" +
			"#table {padding:5px;margin-bottom:53px;}\n" +
			"h2 {margin-left:2%%;margin-top:10px;font-family:monospace;font-size:24px;}\n" +
			".poketable {margin-top:3em;margin-left:6px;margin-right:6px;border:4px black solid;outline:7px solid #894527;display:inline-block;width:440px}\n" +
			".link {display:inline-block}\n" +
			"#right {float:right}\n" +
			"#left {float:left}\n" +
			"#footer {clear:left;text-align:center;display:block}\n" +
			"td {font-size:20px;font-family:sans-serif;}\n" +
			"img {height:200px;width:200px;}\n" +
			"body {background-color:#f5f5f5;}\n" +
			"hr {height:5px;background-color:black;}\n" +
			"div hr {margin-bottom:0px}\n" +
			"div {display:inline-block}\n" +
			"div p {font-family:sans-serif;white-space:normal;padding:5px;margin:auto}\n" +
			"</style>\n" +
			"</head>\n" +
			"<body>\n" +
			"<div id=\"header\">\n" +
			"<h2>Pokedex: No. %s</h2>\n" +
			"</div>\n" +
			"<div class=\"poketable\">" +
			"<div>" +
			"<img src=%s/>\n" +
			"</div>\n" +
			"<div id=\"table\">\n" +
			"<table>\n" +
			"<tbody>\n" +
			"<tr>\n" +
			"<td colspan=\"2\">%s</td>\n" +
			"</tr>\n" +
			"<tr>\n" +
			"<td colspan=\"2\">%s</td>\n" +
			"</tr>\n" +
			"<tr>\n" +
			"<td>HT</td>\n" +
			"<td>%sm</td>\n" +
			"</tr>\n" +
			"<tr>\n" +
			"<td>WT</td>\n" +
			"<td>%skg</td>\n" +
			"</tr>\n" +
			"</table>\n" +
			"</div>\n" +
			"<hr>\n" +
			"<p>%s</p>\n" +
			"</div>\n" +
			"%s" +
			"<hr>\n" +
			"<div id=\"left\" class=\"link\">\n" +
			"<p>\n" +
			"<a href=\"http://localhost:8080/pokemon/%d\">\n" +
			"<-- Previous Pokemon\n" +
			"</a>\n" +
			"</div>\n" +
			"<div id=\"right\" class=\"link\">\n" +
			"<p>\n" +
			"<a href=\"http://localhost:8080/pokemon/%d\">\n" +
			"Next Pokemon -->\n" +
			"</a>\n" +
			"</p>\n" +
			"</div>\n" +
			"<div id=\"footer\">\n" +
			"<p>\n" +
			"<a href=\"http://localhost:8080/pokemon\">\n" +
			"Back to the Pokedex\n" +
			"</a>\n" +
			"</p>\n" +
			"</div>\n" +
			"</body>\n" +
			"</html>";
	public static final String POKEMONS_TABLE = "<!DOCTYPE html>" +
			"<html>\n" +
			"<head>\n" +
			"<title>Pokedex</title>\n" +
			"<style>\n" +
			"p {background-color:e7edb4;font-size:2em;font-family:Impact,Charcoal,sans-serif;}\n" +
			"table, td {border: 1px black solid}\n" +
			"</style>\n" +
			"</head>\n" +
			"<body>\n" +
			"<table>\n" +
			"<thead>\n" +
			"<th colspan=\"2\">Pokemons</th>\n" +
			"</thead>\n" +
			"<tbody>\n" +
			"<tr>\n" +
			"<th>No.</th>\n" +
			"<th>Name</th>\n" +
			"</tr>\n" +
			"%s\n" +
			"</tbody>\n" +
			"</table>\n" +
			"</body>\n" +
			"</html>";

	@RequestMapping(value = "/pokemon", method = RequestMethod.GET)
	public String pokemons() {
		try {
			String urlStr = BASE_URL + "?limit=721";
			URL url = new URL(urlStr);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection.setRequestProperty("User-Agent", "java");
			urlConnection.connect();

			String result = CharStreams.toString(new InputStreamReader(urlConnection.getInputStream(), Charsets.UTF_8));

			JsonParser jsonParser = new JsonParser();
			JsonArray results = jsonParser.parse(result)
					.getAsJsonObject().getAsJsonArray("results");

			String tableData = "";

			for (JsonElement element : results) {
				PokemonRef pokemonRef = new Gson().fromJson(element, PokemonRef.class);
				String idFromUrl = pokemonRef.computeIdFromUrl();
				tableData += "<tr>\n" +
						"<td><a href=\"http://localhost:8080/pokemon/" + idFromUrl + "\">" + idFromUrl + "</a></td>\n" +
						"<td>" + pokemonRef.getName() + "</td></a>\n" +
						"</tr>\n";
			}

			return String.format(POKEMONS_TABLE,tableData);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Error";
	}

	@RequestMapping(value = "/pokemon/{id}", method = RequestMethod.GET)
	public String pokemon(@PathVariable("id") Integer id) {
		try {
			String idStr = id.toString();
//			while (idStr.length() < 3) {
//				idStr = "0" + idStr;
//			}


			String urlStr = BASE_URL + idStr + "/";
			URL url = new URL(urlStr);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection.setRequestProperty("User-Agent", "java");
			urlConnection.connect();

			String result = CharStreams.toString(new InputStreamReader(urlConnection.getInputStream(), Charsets.UTF_8));
			idStr = StringUtils.leftPad(idStr, 3, '0');
			Pokemon pokemon = new Gson().fromJson(result, Pokemon.class);
			SpeciesInfo speciesInfo = pokemon.getSpecies().getSpeciesInfo();
			return String.format(POKEMON_TABLE_FORMAT,
					idStr,
					pokemon.getSprites().getFront_default(),
					pokemon.getSpecies().getName().toUpperCase(),
					pokemon.getType().toUpperCase(),
					(pokemon.getHeight() / 10.0),
					(pokemon.getWeight() / 10.0),
					speciesInfo.getFlavorText(),
					speciesInfo.getAltForms(),
					(id-1),
					(id+1));

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		log.info(pokemon);

//		return pokemon;
		return "Error";
	}



}
