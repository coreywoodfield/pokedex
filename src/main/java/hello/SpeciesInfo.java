package hello;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by cwoodfie on 3/24/16.
 */
public class SpeciesInfo {

	private static final String BASE_URL = "http://pokeapi.co/api/v2/pokemon/";
	public static final String POKE_TABLE_FORMAT = "<div class=\"poketable\">" +
			"<div>\n" +
			"<img src=%s/>\n" +
			"</div>" +
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
			"</div>\n";
	private Flavor_Text_Entries[] flavor_text_entries;
	private Variety[] varieties;

	public String getFlavorText() {
		for (Flavor_Text_Entries entry : flavor_text_entries) {
			if (entry.getLanguage().isEnglish()) {
				return entry.getFlavor_text();
			}
		}
		return "Error";
	}

	public String getAltForms() {
		/*if (varieties.length > 3) {
			String altFormsHtml = "<div class =\"notheader\">\n" +
					"<table>\n" +
					"<thead>\n" +
					"<th>Alternate Forms:</th>\n" +
					"</thead>\n";
			for (int i = 1; i < varieties.length; i++) {
				Variety variety = varieties[i];
				String format = "<tr>\n<td>\n<a href=\"http://localhost:8080/pokemon/%s\">%s</a></td>\n</tr>\n";
				altFormsHtml += String.format(format, variety.getId(), variety.getName());
			}
			altFormsHtml += "</tbody>\n" +
					"</table>\n" +
					"</div>\n";
			return altFormsHtml;
		} else */ if (varieties.length > 1) {
			String altFormsHtml = "";
			for (int i = 1; i < varieties.length; i++) {
				try {
					Variety variety = varieties[i];
					String idStr = variety.getId();
					String urlStr = BASE_URL + idStr + "/";
					URL url = new URL(urlStr);
					HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
					urlConnection.setRequestMethod("GET");
					urlConnection.setRequestProperty("Accept", "application/json");
					urlConnection.setRequestProperty("User-Agent", "java");
					urlConnection.connect();
					String result = CharStreams.toString(new InputStreamReader(urlConnection.getInputStream(), Charsets.UTF_8));
					Pokemon pokemon = new Gson().fromJson(result, Pokemon.class);
					altFormsHtml += String.format(POKE_TABLE_FORMAT,
							pokemon.getSprites().getFront_default(),
							pokemon.getName().toUpperCase(),
							pokemon.getType().toUpperCase(),
							(pokemon.getHeight() / 10.0),
							(pokemon.getWeight() / 10.0));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return altFormsHtml;
		} else {
			return "";
		}
	}

	@Override
	public String toString() {
		return "SpeciesInfo{" +
				", flavor_text_entries=" + Arrays.toString(flavor_text_entries) +
				"varieties=" + Arrays.toString(varieties) +
				'}';
	}

	public Flavor_Text_Entries[] getFlavor_text_entries() {
		return flavor_text_entries;
	}

	public Variety[] getVarieties() {
		return varieties;
	}

	public SpeciesInfo(Variety[] varieties, Flavor_Text_Entries[] flavorTextEntries) {

		this.flavor_text_entries = flavorTextEntries;
		this.varieties = varieties;
	}
}
