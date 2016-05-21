package hello;

/**
 * Created by cwoodfie on 3/22/16.
 */
public class Type {

	private String name;

	public Type(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Type{" +
				"name='" + name + '\'' +
				'}';
	}

}
