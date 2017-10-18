package game.client.render;

public interface IRenderer<T> {

	void draw();

	void draw(T t);

	void renderAt();

	void renderAt(T t, int x, int y);

	void renderAt(T t, int x, int y, int w, int h, float yaw);

}
