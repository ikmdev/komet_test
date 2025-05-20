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
package dev.ikm.komet_test.kview.data.persistence;

import dev.ikm.tinkar.common.id.PublicId;

import java.util.UUID;

public interface Writer {

    default long[] createAdditionalLongs(PublicId publicId) {
        long[] additionalLongs = new long[(publicId.uuidCount() * 2) - 2];
        int index = 0;
        for (int i = 1; i < publicId.uuidCount(); i++) {
            UUID uuid = publicId.asUuidArray()[i];
            additionalLongs[index++] = uuid.getMostSignificantBits();
            additionalLongs[index++] = uuid.getLeastSignificantBits();
        }
        return additionalLongs.length == 0 ? null : additionalLongs;
    }
}
