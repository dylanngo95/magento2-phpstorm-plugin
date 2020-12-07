/*
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */

package com.magento.idea.magento2plugin.actions.generation.generator;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.jetbrains.php.lang.psi.PhpFile;
import com.magento.idea.magento2plugin.actions.generation.data.NewUnitTestData;
import com.magento.idea.magento2plugin.actions.generation.generator.util.DirectoryGenerator;
import com.magento.idea.magento2plugin.actions.generation.generator.util.FileFromTemplateGenerator;
import com.magento.idea.magento2plugin.bundles.CommonBundle;
import com.magento.idea.magento2plugin.bundles.ValidatorBundle;
import com.magento.idea.magento2plugin.indexes.ModuleIndex;
import com.magento.idea.magento2plugin.magento.files.UnitTestTemplate;

import java.util.Properties;

public class UnitTestGenerator extends FileGenerator {

    private final FileFromTemplateGenerator fileGenerator;
    private final Project project;
    private final DirectoryGenerator dirGenerator;
    private final NewUnitTestData newUnitTestData;
    private final ValidatorBundle validatorBundle;
    private final CommonBundle commonBundle;

    public UnitTestGenerator(final Project project, final NewUnitTestData newUnitTestData) {
        super(project);
        this.project = project;
        this.fileGenerator = FileFromTemplateGenerator.getInstance(project);
        this.newUnitTestData = newUnitTestData;
        this.validatorBundle = new ValidatorBundle();
        this.commonBundle = new CommonBundle();
        this.dirGenerator = DirectoryGenerator.getInstance();
    }

    @Override
    public PsiFile generate(final String actionName) {
        final PhpFile unitTestFile = createUnitTestClass(actionName);
        if (unitTestFile == null) {
            final String errorMessage = validatorBundle.message(
                    "validator.file.cantBeCreated",
                    commonBundle.message("common.cli.create.new.unit.test.title")
            );

            throw new RuntimeException(errorMessage);
        }
        return unitTestFile;
    }

    @Override
    protected void fillAttributes(Properties attributes) {
        attributes.setProperty("NAMESPACE", this.newUnitTestData.getNamespace());
        attributes.setProperty("CLASS_NAME", this.newUnitTestData.getClassName());
        attributes.setProperty("NAMESPACE_PARENT_NAME", this.newUnitTestData.getNamespaceParentName());
    }

    private PhpFile createUnitTestClass(final String actionName) {
        final PsiFile unitTestFile = this.fileGenerator.generate(
                this.getUnitTestTemplate(),
                getAttributes(),
                this.getParentDirectory(),
                actionName
        );
        return (PhpFile) unitTestFile;
    }

    private UnitTestTemplate getUnitTestTemplate() {
        final String name = this.newUnitTestData.getClassName() + "Test";
        return new UnitTestTemplate(name);
    }

    private PsiDirectory getParentDirectory() {
        final String moduleName = this.newUnitTestData.getModuleName();
        final String[] subDirectories = this.newUnitTestData.getDirectory().split("/");
        PsiDirectory parentDirectory = ModuleIndex.getInstance(project)
                .getModuleDirectoryByModuleName(moduleName);
        for (final String subDirectory : subDirectories) {
            parentDirectory = dirGenerator.findOrCreateSubdirectory(parentDirectory, subDirectory);
        }
        return parentDirectory;
    }
}
