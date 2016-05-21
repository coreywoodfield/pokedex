package hello;

/**
 * Created by cwoodfie on 3/22/16.
 */
public class Types implements Comparable<Types> {

	private int slot;
	private Type type;

	public int getSlot() {
		return slot;
	}

	public Type getType() {
		return type;
	}

	public String getTypeStr() {
		String name = type.getName();
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}

	public Types(int slot, Type type) {
		this.slot = slot;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Types{" +
				"slot=" + slot +
				", type=" + type +
				'}';
	}

	@Override
	public int compareTo(Types o) {
		if (this.slot > o.slot) {
			return 1;
		} else if (this.slot == o.slot) {
			return 0;
		} else {
			return -1;
		}
	}
}
