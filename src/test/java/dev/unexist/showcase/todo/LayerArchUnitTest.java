/**
 * @package Quarkus-Arch-Testing-Showcase
 *
 * @file Layer Tests
 * @copyright 2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "dev.unexist.showcase.todo")
public class LayerArchUnitTest {
    private final JavaClasses classes = new ClassFileImporter().importPackages("dev.unexist.showcase.todo");

    @Test
    public void testLayeredArch() {
        layeredArchitecture()
            .layer("Adapters")
                .definedBy("..adapters..")
            .layer("Application")
                .definedBy("..application..")
            .layer("Domain")
                .definedBy("..domain..")
            .layer("Infrastructure")
                .definedBy("..infrastructure..")

            .whereLayer("Adapters")
                .mayNotBeAccessedByAnyLayer()
            .whereLayer("Application")
                .mayOnlyBeAccessedByLayers("Adapters")
            .whereLayer("Domain")
                .mayOnlyBeAccessedByLayers("Adapters", "Application")
            .whereLayer("Infrastructure")
                .mayOnlyBeAccessedByLayers("Adapters", "Application", "Infrastructure")
            .check(classes);
    }
}
