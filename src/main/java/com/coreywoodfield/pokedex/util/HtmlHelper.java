package com.coreywoodfield.pokedex.util;

import com.coreywoodfield.pokedex.model.Pokemon;
import com.coreywoodfield.pokedex.model.Pokemons;
import com.coreywoodfield.pokedex.model.Species;

import java.util.List;

/**
 * Created by coreywoodfield on 7/3/17.
 */
public class HtmlHelper {
	private static final String POKEMON_TABLE_FORMAT =
			"<!DOCTYPE html><html><head><title>Pokedex</title><style>"
			+ "#header {background-color:#ef5350;width:100%%;z-index:1;position:fixed;height:3em;top:0;left:0;right:0;}"
			+ "#table {padding:5px;margin-bottom:53px;}"
			+ "h2 {margin-left:2%%;margin-top:10px;font-family:monospace;font-size:24px;}"
			+ ".poketable {margin-top:3em;margin-left:6px;margin-right:6px;border:4px black solid;outline:7px solid #894527;display:inline-block;width:440px}"
			+ ".link {display:inline-block}"
			+ "#right {float:right}"
			+ "#left {float:left}"
			+ "#footer {clear:left;text-align:center;display:block}"
			+ "td {font-size:20px;font-family:sans-serif;}"
			+ "img {height:200px;width:200px;}"
			+ "body {background-color:#f5f5f5;}"
			+ "hr {height:5px;background-color:black;}"
			+ "div hr {margin-bottom:0px}"
			+ "div {display:inline-block}"
			+ "div p {font-family:sans-serif;white-space:normal;padding:5px;margin:auto}"
			+ "</style></head><body>"
			+ "<div id=\"header\"><h2>Pokedex: No. %s</h2></div>"
			+ "<div class=\"poketable\">"
			+ "<div><img src=%s/></div>"
			+ "<div id=\"table\"><table><tbody>"
			+ "<tr><td colspan=\"2\">%s</td></tr>"
			+ "<tr><td colspan=\"2\">%s</td></tr>"
			+ "<tr><td>HT</td><td>%sm</td></tr>"
			+ "<tr><td>WT</td><td>%skg</td></tr>"
			+ "</tbody></table></div>"
			+ "<hr><p>%s</p></div>%s<hr>"
			+ "<div id=\"left\" class=\"link\"><p><a href=\"/pokemon/%d\"><-- Previous Pokemon</a></div>"
			+ "<div id=\"right\" class=\"link\"><p><a href=\"/pokemon/%d\">Next Pokemon --></a></p></div>"
			+ "<div id=\"footer\"><p><a href=\"/pokemon\">Back to the Pokedex</a></p></div>"
			+ "</body></html>";
	private static final String POKEMONS_TABLE = "<!DOCTYPE html><html><head><title>Pokedex</title><style>"
												 + "p {background-color:e7edb4;font-size:2em;font-family:Impact,Charcoal,sans-serif;}"
												 + "table, td {border: 1px black solid}"
												 + "</style></head><body><table>"
												 + "<thead><th colspan=\"2\">Pokemons</th></thead>"
												 + "<tbody><tr><th>No.</th><th>Name</th></tr>%s</tbody>"
												 + "</table></body></html>";
	private static final String POKEMON_ROW = "<tr><td><a href=\"/pokemon/%s\">%<s</a></td><td>%s</td></tr>";

	public static String toHtml(List<Pokemons.PokemonRef> pokemons) {
		StringBuilder tableData = new StringBuilder();
		for (Pokemons.PokemonRef pokemonRef : pokemons) {
			tableData.append(String.format(POKEMON_ROW, pokemonRef.getId(), pokemonRef.getName()));
		}
		return String.format(POKEMONS_TABLE, tableData.toString());
	}

	public static String toHtml(Pokemon pokemon) {
		Species.SpeciesInfo speciesInfo = pokemon.getSpecies().getSpeciesInfo();
		Integer id = pokemon.getId();
		return String.format(POKEMON_TABLE_FORMAT,
							 String.format("%03d", id),
							 pokemon.getSprites().getSprite(),
							 pokemon.getSpecies().getName().toUpperCase(),
							 pokemon.getType().toUpperCase(),
							 (pokemon.getHeight() / 10.0),
							 (pokemon.getWeight() / 10.0),
							 speciesInfo.getFlavorText(),
							 altFormsHtml(pokemon.getAltForms()),
							 (id - 1),
							 (id + 1));
	}

	private static final String ALT_FORM = "<div class=\"poketable\"><div><img src=%s/></div>"
										   + "<div id=\"table\"><table><tbody>"
										   + "<tr><td colspan=\"2\">%s</td></tr>"
										   + "<tr><td colspan=\"2\">%s</td></tr>"
										   + "<tr><td>HT</td><td>%dm</td></tr>"
										   + "<tr><td>WT</td><td>%dkg</td></tr>"
										   + "</tbody></table></div></div>";

	private static String altFormsHtml(List<Pokemon> altForms) {
		StringBuilder altFormsHtml = new StringBuilder();
		for (Pokemon pokemon : altForms) {
			altFormsHtml.append(String.format(ALT_FORM,
											  pokemon.getSprite(),
											  pokemon.getName().toUpperCase(),
											  pokemon.getType().toUpperCase(),
											  pokemon.getHeight() / 10.0,
											  pokemon.getWeight() / 10.0));
		}
		return altFormsHtml.toString();
	}

	public static String base(Object o) {
		return "<!DOCTYPE html><html><head></head><body><a href=\"/pokemon\">pokemon</a></body></html>";
	}
}
