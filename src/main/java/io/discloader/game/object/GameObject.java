package io.discloader.game.object;

import io.discloader.game.common.objects.Vertex2D;
import io.discloader.game.physics.ICollider2D;
import io.discloader.game.render.texture.ITexture;

public class GameObject {

	protected ICollider2D collider2D;
	protected String name;
	protected String tag = null;

	private ITexture texture = null;
	private Vertex2D vertex2D;

	public GameObject() {
		setName("GameObject");
		setCollider2D(null);
	}

	public ICollider2D getCollider2D() {
		return collider2D;
	}

	public String getName() {
		return name;
	}

	public String getTag() {
		return tag;
	}

	public void setCollider2D(ICollider2D collider2d) {
		collider2D = collider2d;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public ITexture getTexture() {
		return texture;
	}

	public void setTexture(ITexture texture) {
		this.texture = texture;
	}

	public void onCollisionEnter2D(GameObject gameObject) {

	}

	public void onCollisionStay2D(GameObject gameObject) {

	}

	public void onCollisionExit2D(GameObject gameObject) {

	}

}
