/*
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */

package com.magento.idea.magento2plugin.actions.generation;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.magento.idea.magento2plugin.MagentoIcons;
import com.magento.idea.magento2plugin.actions.generation.dialog.NewUnitTestDialog;
import org.jetbrains.annotations.NotNull;

public class NewUnitTestAction extends AnAction {

    public static String ACTION_NAME = "Magento 2 Unit Test";
    public static String ACTION_DESCRIPTION = "Create a new Magento 2 unit test class";

    public NewUnitTestAction() {
        super(ACTION_NAME, ACTION_DESCRIPTION, MagentoIcons.MODULE);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        final DataContext context = e.getDataContext();

        final IdeView view = LangDataKeys.IDE_VIEW.getData(context);
        if (view == null) {
            return;
        }

        final Project project = CommonDataKeys.PROJECT.getData(context);
        if (project == null) {
            return;
        }

        final PsiDirectory directory = view.getOrChooseDirectory();
        if (directory == null) {
            return;
        }

        NewUnitTestDialog.open(project, directory);
    }

    @Override
    public boolean isDumbAware() {
        return false;
    }
}
