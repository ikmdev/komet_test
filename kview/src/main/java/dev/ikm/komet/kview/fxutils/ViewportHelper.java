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
package dev.ikm.komet_test.kview.fxutils;

import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViewportHelper {
    private static final Logger LOG = LoggerFactory.getLogger(ViewportHelper.class);
    private ViewportHelper() {}

    /**
     * When using a JavaFX Pane children are able to be outside of the parent layout bounds therefore the children need to be clipped.
     * @param region The region to be clipped
     * @param arc the arc angle of all corners of the rectangular region.
     */
    public static void clipChildren(Region region, double arc) {
        final Rectangle outputClip = new Rectangle();
        outputClip.setArcWidth(arc);
        outputClip.setArcHeight(arc);
        region.setClip(outputClip);
        region.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
            outputClip.setWidth(newValue.getWidth());
            outputClip.setHeight(newValue.getHeight());
        });
    }
}
