package com.poptok.android.poptok.tools;

import com.bumptech.glide.load.Key;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Random;

public class IntegerVersionSignature implements Key {
    private int currentVersion;
    Random random;

    public IntegerVersionSignature(int currentVersion) {
        this.currentVersion = currentVersion;
        random = new Random();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof IntegerVersionSignature) {
            IntegerVersionSignature other = (IntegerVersionSignature) o;
            return currentVersion == other.currentVersion;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return currentVersion;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest md) {
        md.update(ByteBuffer.allocate(Integer.SIZE).putInt(random.nextInt()).array());
    }
}