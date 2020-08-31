/*
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */

package com.magento.idea.magento2plugin.actions.generation.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.magento.idea.magento2plugin.actions.generation.NewUnitTestAction;
import com.magento.idea.magento2plugin.actions.generation.data.NewUnitTestData;
import com.magento.idea.magento2plugin.actions.generation.generator.UnitTestGenerator;
import com.magento.idea.magento2plugin.actions.generation.generator.util.NamespaceBuilder;
import com.magento.idea.magento2plugin.magento.files.UnitTestTemplate;
import com.magento.idea.magento2plugin.util.magento.GetModuleNameByDirectoryUtil;

import javax.swing.*;

public class NewUnitTestDialog  extends AbstractDialog {

    private JPanel contentPage;
    private JTextField unitTestClassNameField;
    private JTextField unitTestParentDirectoryField;
    private JButton buttonOK;
    private JButton buttonCancel;

    private final Project project;
    private final String moduleName;

    /**
     * Open new unit test.
     *
     * @param project Project
     * @param directory PsiDirection
     */
    public NewUnitTestDialog(final Project project,final PsiDirectory directory) {
        super();
        this.project = project;
        this.moduleName = GetModuleNameByDirectoryUtil.execute(directory, project);

        setContentPane(contentPage);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle(this.bundle.message("common.cli.create.new.unit.test.title"));
        this.suggestionParentDirectories();

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());
    }

    public static void open(final Project project,final PsiDirectory directory) {
        final NewUnitTestDialog dialog = new NewUnitTestDialog(project, directory);
        dialog.pack();
        dialog.centerDialog(dialog);
        dialog.setVisible(true);
    }

    private void onOK() {
        if (false) {
            return;
        }
        this.generate();
        this.setVisible(false);
    }

    private void generate() {
        try {
            this.generateUnitTestClass();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(
                    null,
                    exception.getMessage(),
                    this.bundle.message("common.unit.test.generate.error"),
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void generateUnitTestClass() {
        NewUnitTestData newUnitTestData = new NewUnitTestData(
                this.getParentDirection(),
                this.getClassName(),
                this.getNamespace(),
                this.moduleName
        );

        UnitTestGenerator generator = new UnitTestGenerator(project, newUnitTestData);
        generator.generate(NewUnitTestAction.ACTION_NAME, true);
    }

    private String getParentDirection() {
        return this.unitTestParentDirectoryField.getText().trim();
    }

    private String getClassName() {
        return this.unitTestClassNameField.getText().trim();
    }

    private String getModule() {
        return this.moduleName;
    }

    private String getNamespace() {
        final NamespaceBuilder namespaceBuilder = new NamespaceBuilder(
                this.getModule(),
                this.getClassName(),
                this.getParentDirection()
        );
        return namespaceBuilder.getNamespace();
    }

    private void suggestionParentDirectories() {
        this.unitTestParentDirectoryField.setText(UnitTestTemplate.DEFAULT_DIR);
    }
}
