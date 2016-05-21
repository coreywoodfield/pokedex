package hello;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cwoodfie on 3/24/16.
 */
public class Species {

	private String name;
	private String url;

	public Species(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return "Species{" +
				"name='" + name + '\'' +
				", url='" + url + '\'' +
				'}';
	}

	public SpeciesInfo getSpeciesInfo() {
		try {
			URL url = new URL(this.url);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection.setRequestProperty("User-Agent", "java");
			urlConnection.connect();

			String result = CharStreams.toString(new InputStreamReader(urlConnection.getInputStream(), Charsets.UTF_8));

			return new Gson().fromJson(result, SpeciesInfo.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
