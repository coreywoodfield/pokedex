package com.coreywoodfield.pokedex.model;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by cwoodfie on 3/18/16.
 */
public class Pokemon {
	private Integer id;
	private String name;
	private Type[] types;
	private Double height;
	private Double weight;
	private Sprites sprites;
	private Species species;
	private List<Pokemon> altForms;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}

	public Type[] getTypes() {
		return types;
	}

	public String getType() {
		Arrays.sort(types);
		return String.join("/", Lists.transform(Arrays.asList(types), Type::getTypeStr));
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

	public List<Pokemon> getAltForms() {
		return altForms;
	}

	public void setAltForms(List<Pokemon> altForms) {
		this.altForms = altForms;
	}

	public static class Type implements Comparable<Type> {
		private int slot;
		private InnerType type;

		public int getSlot() {
			return slot;
		}

		public InnerType getType() {
			return type;
		}

		public String getTypeStr() {
			return Character.toUpperCase(type.name.charAt(0)) + type.name.substring(1);
		}

		@Override
		public int compareTo(Type o) {
			return this.slot > o.slot ? 1 : this.slot == o.slot ? 0 : -1;
		}

		public class InnerType {
			private String name;

			public String getName() {
				return name;
			}
		}
	}

	public static class Sprites {
		private String frontShiny;
		private String frontDefault;

		public String getSprite() {
			return frontDefault;
		}

		public String getFrontShiny() {
			return frontShiny;
		}

		public String getFrontDefault() {
			return frontDefault;
		}

		@Override
		public String toString() {
			return "Sprites{" +
				   "frontShiny='" + frontShiny + '\'' +
				   ", frontDefault='" + frontDefault + '\'' +
				   '}';
		}
	}
}
