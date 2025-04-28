package modelo;

public interface Factory {
	void init(String pkgName);
	<T> T create(String name);
}
