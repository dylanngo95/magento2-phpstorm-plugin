/*
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */

package com.magento.idea.magento2plugin.magento.files;

import com.intellij.lang.Language;
import com.jetbrains.php.lang.PhpLanguage;

public class UnitTestTemplate implements ModuleFileInterface {

    public static final String TEMPLATE = "Magento Unit Test Class";
    public static final String DEFAULT_DIR = "Test/Unit";

    private String fileName;

    public UnitTestTemplate(String className) {
        this.fileName = className.concat(".php");
    }

    public static UnitTestTemplate getInstance(String className) {
        return new UnitTestTemplate(className);
    }

    @Override
    public String getFileName() {
        return this.fileName;
    }

    @Override
    public String getTemplate() {
        return TEMPLATE;
    }

    @Override
    public Language getLanguage() {
        return PhpLanguage.INSTANCE;
    }
}
