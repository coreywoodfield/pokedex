package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;


import java.util.Arrays;
import java.util.List;

/**
 * Created by cwoodfie on 3/18/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

	private Integer id;
	private String name;
	private Types[] types;
	private Double height;
	private Double weight;
	private Sprites sprites;
	private Species species;

	public Pokemon(Integer id, String name, Types[] types, Double height, Double weight, Sprites sprites, Species species) {
		this.id = id;
		this.name = name;
		this.types = types;
		this.height = height;
		this.weight = weight;
		this.sprites = sprites;
		this.species = species;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}

	public Types[] getTypes() {
		return types;
	}

	public String getType() {
		Arrays.sort(types);
		List<String> stringList = Lists.transform(Arrays.asList(types), new Function<Types, String>() {
			@Override
			public String apply(Types types) {
				return types.getTypeStr();
			}
		});
		return StringUtils.join(stringList, "/");
	}

	public Double getHeight() {
		return height;
	}

	public Double getWeight() {
		return weight;
	}

	public Sprites getSprites() {
		return sprites;
	}

	public String getSprite() {
		return sprites.getSprite();
	}

	public Species getSpecies() {
		return species;
	}

	@Override
	public String toString() {
		return "Pokemon{" +
				"id=" + id +
				", name='" + name + '\'' +
				", types=" + Arrays.toString(types) +
				", height=" + height +
				", weight=" + weight +
				", sprites=" + sprites +
				", species=" + species +
				'}';
	}
}
