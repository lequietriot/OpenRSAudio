package net.openrs.cache.audio;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.openrs.cache.Cache;
import net.openrs.cache.Container;
import net.openrs.cache.ReferenceTable;
import net.openrs.cache.type.CacheIndex;

public class SoundEffectList {
	
	public static SoundEffect[] sfx1;

	public void initialize(Cache cache) {

		try {
			ReferenceTable table = cache.getReferenceTable(CacheIndex.SOUNDEFFECTS);

			sfx1 = new SoundEffect[table.capacity()];
			for (int i = 0; i < table.capacity(); i++) {
				
				if (table.getEntry(i) == null) {
					continue;
				}

				Container container = cache.read(CacheIndex.SOUNDEFFECTS, i);
				
				byte[] encoded = new byte[container.getData().limit()];				
				container.getData().get(encoded);
				
				SoundEffect sounds1 = new SoundEffect();
				sounds1.decode(ByteBuffer.wrap(encoded));
				
				sfx1[i] = sounds1;
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SoundEffect getSounds1(int id) {
		return sfx1[id];
	}
	
	public static int getSounds1Count() {
		return sfx1.length;
	}
}

