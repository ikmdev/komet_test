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

public interface PreferencesService {

    static KometPreferences configurationPreferences() {
        return get().getConfigurationPreferences();
    }

    /**
     * @return a database specific preferences store which is stored within the data store folder.
     */
    KometPreferences getConfigurationPreferences();

    static PreferencesService get() {
        return PreferencesServiceFinder.INSTANCE.get();
    }

    static KometPreferences userPreferences() {
        return get().getUserPreferences();
    }

    /**
     * @return a preference store that is stored within a user profile folder of the current OS user, so it will apply
     * to any database a user opens on this computer.
     */
    KometPreferences getUserPreferences();

    static KometPreferences systemPreferences() {
        return get().getSystemPreferences();
    }

    /**
     * @return a preference store that is stored globally within an OS store, for any user that runs the application
     * on this specific computer.
     */
    KometPreferences getSystemPreferences();

    /**
     * Reload the configuration preferences from disk. For example, used when git fetches updates to the
     * configuration preferences, and the preferences should be reloaded to reflect the newly downloaded preferences.
     */
    void reloadConfigurationPreferences();

    /**
     * Remove any and all application preferences stored data store folder
     * The behavior of any handles to the system preferences that are still held after this operation is undefined.
     */
    void clearConfigurationPreferences();

    /**
     * Remove any and all preferences stored in the system store
     * The behavior of any handles to the system preferences that are still held after this operation is undefined.
     */
    void clearSystemPreferences();

    /**
     * Remove any and all preferences stored in the system user store
     * The behavior of any handles to the user preferences that are still held after this operation is undefined.
     */
    void clearUserPreferences();
}
