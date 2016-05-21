package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by cwoodfie on 3/18/16.
 */

@JsonIgnoreProperties
public class Value {

	private Long id;
	public String quote;

	public Value() {
	}

	public Long getId() {
		return id;
	}

	public String getQuote() {
		return quote;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	@Override
	public String toString() {
		return "Value{id=" + id + ", quote='" + quote + "'}";
	}
}
