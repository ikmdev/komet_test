/*
 * Copyright © 2015 Integrated Knowledge Management (support@ikm.dev)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.ikm.komet_test.preferences;

//~--- JDK imports ------------------------------------------------------------

import dev.ikm.tinkar.common.service.TrackingCallable;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import java.math.BigInteger;
import java.nio.file.Files;

import java.security.DigestInputStream;
import java.security.MessageDigest;


/**
 * The Class ChecksumGenerator.
 */
public class ChecksumGenerator {
    /**
     * Accepts types like "MD5 or SHA1".
     *
     * @param type the type
     * @param data the data
     * @return the string
     */
    public static String calculateChecksum(String type, byte[] data) {
        try {
            final MessageDigest     md  = MessageDigest.getInstance(type);
            final DigestInputStream dis = new DigestInputStream(new ByteArrayInputStream(data), md);

            dis.read(data);
            return getStringValue(md);
        } catch (final Exception e) {
            throw new RuntimeException("Unexpected error: " + e);
        }
    }

    /**
     * Calculate checksum.
     *
     * @param type the type
     * @param data the data
     * @return the task
     */
    public static TrackingCallable<String> newChecksumTask(String type, File data) {
        final TrackingCallable<String> checkSumCalculator = new TrackingCallable<>() {
            @Override
            public String compute()
                    throws Exception {
                final long fileLength = data.length();

                updateProgress(0, fileLength);

                final MessageDigest md = MessageDigest.getInstance(type);

                try (InputStream is = Files.newInputStream(data.toPath())) {
                    final DigestInputStream dis       = new DigestInputStream(is, md);
                    final byte[]            buffer    = new byte[8192];
                    long                    loopCount = 0;
                    int                     read      = 0;

                    while (read != -1) {
                        // update every 10 MB
                        if (loopCount % 1280 == 0) {
                            updateProgress((loopCount * 8192l), fileLength);
                            updateMessage("Calculating " + type + " checksum for " + data.getName() + " - " +
                                    (loopCount * 8192l) + " / " + fileLength);
                        }

                        read = dis.read(buffer);
                        loopCount++;
                    }

                    updateProgress(fileLength, fileLength);
                    updateMessage("Done calculating " + type + " checksum for " + data.getName());
                    return getStringValue(md);
                }
            }
        };

        return checkSumCalculator;
    }

    //~--- get methods ---------------------------------------------------------

    /**
     * Gets the string value.
     *
     * @param md the md
     * @return the string value
     */
    private static String getStringValue(MessageDigest md) {
        final byte[] digest = md.digest();

        return toHex(digest);
    }

    public static String toHex(byte[] arg) {
        return String.format("%040x", new BigInteger(1, arg));
    }

}

