/*
 * Copyright (c) 2016. See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mbrlabs.mundus.editor.utils;

import com.kotcrab.vis.ui.util.OsUtils;
import com.sun.jna.Native;
import com.sun.jna.platform.unix.X11;

import java.io.File;

/** @author Kotcrab */
public class PlatformUtils {
    private static boolean x11 = false;

    static {
        try {
            Native.loadLibrary("X11", X11.class);
            x11 = true;
        } catch (UnsatisfiedLinkError e) {
            x11 = false;
        }
    }

    public static boolean isX11() {
        return x11;
    }

    public static String getJavaBinPath() {
        if (OsUtils.isWindows()) {
            File javaBin = new File(System.getProperty("java.home") + "/bin/java.exe");
            if (javaBin.exists()) {
                return "\"" + javaBin.getAbsolutePath() + "\"";
            }
        }

        return "java";
    }
}
