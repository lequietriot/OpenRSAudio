package net.openrs.cache.audio;

public class RawAudioNode {
   public int sampleRate;
   public byte[] audioBuffer;
   public int startPosition;
   int endPosition;
   public boolean field1548;

   RawAudioNode(final int var1, final byte[] var2, final int var3, final int var4) {
      this.sampleRate = var1;
      this.audioBuffer = var2;
      this.startPosition = var3;
      this.endPosition = var4;
   }

   RawAudioNode(final int var1, final byte[] var2, final int var3, final int var4, final boolean var5) {
      this.sampleRate = var1;
      this.audioBuffer = var2;
      this.startPosition = var3;
      this.endPosition = var4;
      this.field1548 = var5;
   }
}
