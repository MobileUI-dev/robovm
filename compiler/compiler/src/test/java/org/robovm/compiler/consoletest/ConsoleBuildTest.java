/*
 * Copyright (C) 2012 RoboVM AB
 * Copyright (C) 2020 Daniel Thommes, NeverNull GmbH, <daniel.thommes@nevernull.io>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.compiler.consoletest;

import org.junit.Ignore;
import org.junit.Test;
import org.robovm.compiler.AppCompiler;
import org.robovm.compiler.Version;

import java.io.File;
import java.io.IOException;

/**
 * Test for running the console version of RoboVM compiler with different settings compiling a console app
 */
public class ConsoleBuildTest {

    @Test
    @Ignore
    public void testConsoleBuild() throws IOException {
        runTest("");
    }

    @Test
    @Ignore
    public void testConsoleBuildTouchedMain() throws IOException {
        File classFile = new File("target/test-classes/org/robovm/compiler/consoletest/main/TestMain.class");
        classFile.setLastModified(System.currentTimeMillis());
        runTest("");
    }

    @Test
    @Ignore
    public void testConsoleCleanBuild() throws IOException {
        runTest("-clean");
    }

    private void runTest(String extraArgs) throws IOException {
        String userHome = System.getProperty("user.home");
        String sdkHome = userHome + "/.robovm-sdks/robovm-" + Version.getVersion();

        String args = "-home " + sdkHome + " " +
                "-verbose " +
                "-cp target/test-classes " +
                "-config target/test-classes/org/robovm/compiler/consoletest/robovm.xml " +
                "-cache target/cache " +
                "-d target/executable " +
                "-run " +
                extraArgs;

        long startTime = System.currentTimeMillis();
        AppCompiler.main(args.split(" "));
        System.out.println("Duration: " + (float) (System.currentTimeMillis() - startTime) / 1000 + "s");
    }
}
