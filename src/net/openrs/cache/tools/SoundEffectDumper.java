package net.openrs.cache.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import net.openrs.cache.Cache;
import net.openrs.cache.Constants;
import net.openrs.cache.FileStore;
import net.openrs.cache.audio.SoundEffect;
import net.openrs.cache.audio.SoundEffectList;

public final class SoundEffectDumper {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File sounds1Dir = new File(Constants.SOUNDS1_PATH);

		if (!sounds1Dir.exists()) {
			sounds1Dir.mkdirs();
		}
		
		try (Cache cache = new Cache(FileStore.open(Constants.CACHE_PATH))) {
			SoundEffectList list = new SoundEffectList();
			list.initialize(cache);

			for (int i = 0; i < list.getSounds1Count(); i++) {
				SoundEffect sound1 = list.getSounds1(i);
				if (sound1 == null) {
					continue;
				}
				
				AudioFormat audioFormat = new AudioFormat(sound1.method2124().sampleRate, 8, 1, true, false); //Sound Effects Format: original Sample Rate, 8-bit, Mono, Signed, Little-Endian
				AudioInputStream ais = new AudioInputStream(new ByteArrayInputStream(sound1.method2124().audioBuffer), audioFormat, sound1.method2124().audioBuffer.length);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				AudioSystem.write(ais, AudioFileFormat.Type.WAVE, bos);
				byte[] data = bos.toByteArray();

				try (DataOutputStream dos = new DataOutputStream(
						new FileOutputStream(new File(sounds1Dir, i + ".wav")))) {
					dos.write(data);
				}

				double progress = (double) (i + 1) / list.getSounds1Count() * 100;
				System.out.printf("Dumping Sound Effects (Index 4) %d out of %d %.2f%s\n", (i + 1), list.getSounds1Count(), progress, "%");
			}
		}
	}
}
