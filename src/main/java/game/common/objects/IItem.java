package game.common.objects;

import game.common.entity.Entity;
import game.render.Resource;

public interface IItem {
	
	public String getName();
	
	public boolean isTool();
	
	public int getReachDistance();
	
	public Entity createEntity(int x, int y, int room);
	
	public Resource getResourceLocation();
	
}
