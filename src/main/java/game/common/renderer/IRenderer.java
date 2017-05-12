package game.common.renderer;

public interface IRenderer<T> {

	void draw(T t);

	void renderAt(T t, int x, int y, int w, int h, float yaw);

}
