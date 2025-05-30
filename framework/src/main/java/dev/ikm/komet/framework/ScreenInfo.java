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
package dev.ikm.komet_test.framework;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;

import java.util.concurrent.atomic.AtomicBoolean;

public class ScreenInfo {
    private static Robot robot;
    private static AtomicBoolean mouseIsPressed = new AtomicBoolean(false);
    private static AtomicBoolean mouseIsDragging = new AtomicBoolean(false);
    private static AtomicBoolean mouseWasDraged = new AtomicBoolean(false);

    public static void mouseWasDragged(boolean dragging) {
        mouseWasDraged.set(dragging);
    }

    public static boolean mouseWasDragged() {
        return mouseWasDraged.get();
    }

    public static void mouseIsDragging(boolean dragging) {
        mouseIsDragging.set(dragging);
    }

    public static boolean isMouseDragging() {
        return mouseIsDragging.get();
    }

    public static void mouseIsPressed(boolean pressed) {
        mouseIsPressed.set(pressed);
    }

    public static boolean isMousePressed() {
        return mouseIsPressed.get();
    }

    public static Point2D getMouseLocation() {
        validateState();
        return new Point2D(robot.getMouseX(), robot.getMouseY());
    }

    private static void validateState() {
        if (Platform.isFxApplicationThread()) {
            if (robot == null) {
                robot = new Robot();
            }
        } else {
            throw new IllegalStateException("Trying to access mouse info without using the applicaton thread");
        }
    }

    public static double getMouseX() {
        validateState();
        return robot.getMouseX();
    }

    public static double getMouseY() {
        validateState();
        return robot.getMouseY();
    }

    public static Point2D getMousePosition() {
        validateState();
        return robot.getMousePosition();
    }

    public static Color getPixelColor(double x, double y) {
        validateState();
        return robot.getPixelColor(x, y);
    }

    public static Color getPixelColor(Point2D location) {
        validateState();
        return robot.getPixelColor(location);
    }

    public static WritableImage getScreenCapture(WritableImage image, double x, double y, double width, double height, boolean scaleToFit) {
        validateState();
        return robot.getScreenCapture(image, x, y, width, height, scaleToFit);
    }

    public static WritableImage getScreenCapture(WritableImage image, double x, double y, double width, double height) {
        validateState();
        return robot.getScreenCapture(image, x, y, width, height);
    }

    public static WritableImage getScreenCapture(WritableImage image, Rectangle2D region) {
        validateState();
        return robot.getScreenCapture(image, region);
    }

    public static WritableImage getScreenCapture(WritableImage image, Rectangle2D region, boolean scaleToFit) {
        validateState();
        return robot.getScreenCapture(image, region, scaleToFit);
    }
}
