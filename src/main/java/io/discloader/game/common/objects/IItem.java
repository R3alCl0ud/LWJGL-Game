package io.discloader.game.common.objects;

import io.discloader.game.common.entity.Entity;
import io.discloader.game.render.Resource;

public interface IItem {
	
	public String getName();
	
	public boolean isTool();
	
	public int getReachDistance();
	
	public Entity createEntity(int x, int y, int room);
	
	public Resource getResourceLocation();
	
}
