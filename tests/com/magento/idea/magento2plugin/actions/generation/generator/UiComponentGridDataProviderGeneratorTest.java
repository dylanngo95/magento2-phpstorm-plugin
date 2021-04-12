/*
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */

package com.magento.idea.magento2plugin.actions.generation.generator;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.magento.idea.magento2plugin.actions.generation.data.GetListQueryModelData;
import com.magento.idea.magento2plugin.actions.generation.data.UiComponentDataProviderData;

public class UiComponentGridDataProviderGeneratorTest extends BaseGeneratorTestCase {

    private static final String EXPECTED_DIRECTORY = "src/app/code/Foo/Bar/Ui/Component/Listing";
    private static final String MODULE_NAME = "Foo_Bar";
    private static final String ENTITY_NAME = "Book";
    private static final String ENTITY_ID_FIELD_NAME = "entity_id";
    private static final String MODEL_NAME = "Book";
    private static final String PROVIDER_CLASS_NAME = "GridDataProvider";
    private static final String PROVIDER_PATH = "Ui/Component/Listing";
    private static final String COLLECTION_NAME = "Collection";

    /**
     * Test data provider class file generation with custom type.
     */
    public void testGenerateCustomDataProvider() {
        final PsiFile dataProviderFile = generateDataProvider(new UiComponentDataProviderData(
                PROVIDER_CLASS_NAME,
                PROVIDER_PATH
        ));
        final String filePath = this.getFixturePath("GridDataProvider.php");
        final PsiFile expectedFile = myFixture.configureByFile(filePath);

        assertGeneratedFileIsCorrect(
                expectedFile,
                EXPECTED_DIRECTORY,
                dataProviderFile
        );
    }

    /**
     * Test data provider class file generation when get list query model exists.
     */
    public void testGenerateDataProviderWithInjectedGetListQuery() {
        generateGetListQuery();
        final PsiFile dataProviderFile = generateDataProvider(new UiComponentDataProviderData(
                PROVIDER_CLASS_NAME,
                PROVIDER_PATH,
                ENTITY_NAME,
                ENTITY_ID_FIELD_NAME
        ));
        final String filePath = this.getFixturePath("GridDataProvider.php");
        final PsiFile expectedFile = myFixture.configureByFile(filePath);

        assertGeneratedFileIsCorrect(
                expectedFile,
                EXPECTED_DIRECTORY,
                dataProviderFile
        );
    }

    /**
     * Generate data provider file.
     *
     * @return PsiFile
     */
    private PsiFile generateDataProvider(final UiComponentDataProviderData data) {
        final Project project = myFixture.getProject();

        return new UiComponentDataProviderGenerator(
                data,
                MODULE_NAME,
                project
        ).generate("test");
    }

    /**
     * Generate get list query model file.
     */
    private void generateGetListQuery() {
        final Project project = myFixture.getProject();
        final GetListQueryModelData getListData = new GetListQueryModelData(
                MODULE_NAME,
                ENTITY_NAME,
                MODEL_NAME,
                COLLECTION_NAME
        );
        new GetListQueryModelGenerator(getListData, project, false).generate("test");
    }
}
